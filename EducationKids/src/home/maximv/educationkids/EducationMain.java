package home.maximv.educationkids;

import home.maximv.db.service.DbLearnerService;
import home.maximv.db.vo.Learner;
import home.maximv.utils.SpeechRecognition;
import home.maximv.utils.WikiRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class EducationMain extends Activity  {

    private SharedPreferences sPref;

    private EditText login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.education_main);
        PackageManager pm = getPackageManager();
        ImageButton speakButton = (ImageButton) findViewById(R.id.speakButton);
        List<ResolveInfo> activities = pm
                .queryIntentActivities(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
        if (activities.size() != 0) {
            speakButton.setImageResource(R.drawable.mic_on);
        } else {
            speakButton.setEnabled(false);
            speakButton.setImageResource(R.drawable.mic_off);
        }
        // new SpeechToText("Здравствуйте, представьтесь пожалуйста!").start();
    }

    @Override
    public void onStart() {
        super.onStart();
        DbLearnerService lernerService = new DbLearnerService(this);
        Learner learner = lernerService.getLearner(true, this);
        learner.setActive(false);
        lernerService.updateLearner(learner, this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.education_main, menu);
        return true;
    }

    public void saveLogin(View v) {
        sPref = getSharedPreferences("logins", MODE_PRIVATE);
        Editor ed = sPref.edit();
        login = (EditText) findViewById(R.id.nameKids);
        try {
            DbLearnerService lernerService = new DbLearnerService(this);
            Learner learner = new Learner();
            learner.setFirstName("Максим");
            learner.setLogin("maximv");
            learner.setMiddleName("Иосифович");
            learner.setLastName("Вераховский");
            learner.setEmail("mifmax@tut.by");
            learner.setSex("м");
            learner.setBornYear("1984");
            lernerService.setlearner(learner, this);

            ed.putString(login.getText().toString(), login.getText().toString());
            ed.commit();
        } catch (Exception e) {
            Log.d("ERROR", "Ошибка при регистрации");
        }
        Log.d("SUCCESS", "Регистрация прошла успешно");
    }

    public void registration(View v) throws ParserConfigurationException, SAXException, IOException {
        DbLearnerService lernerService = new DbLearnerService(this);
        login = (EditText) findViewById(R.id.nameKids);
        try {
            WikiRequest wikiRequest = new WikiRequest(); 
           String answer =  wikiRequest.sendWikiRequest("Мастер и маргарита");
           Toast.makeText(this,answer , Toast.LENGTH_LONG).show();

        	getResponse("кто такой крокодил");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        Learner learner = lernerService.getLearner(login.getText().toString(), this);
        learner.setActive(true);
        lernerService.updateLearner(learner, this);
        if (learner.getLogin() != null) {
            Toast.makeText(this, learner.getFirstName() + ", вы успешно вошли в систему! ", Toast.LENGTH_SHORT).show();
            successRegistration();
        } else {
            Toast.makeText(this, "Ваше имя не найдено в базе данных,зарегистрируйтесь пожалуйста!", Toast.LENGTH_SHORT)
                    .show();
            saveLogin(v);
        }
    }

    public void successRegistration() {
        Intent intent = new Intent(this, EducationSelect.class);
        startActivity(intent);
    }

    public void recognize(View v) {
        SpeechRecognition.run(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SpeechRecognition.VOICE_RECOGNITION_REQUEST_CODE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String resString = "";
            for (String s : matches) {
                resString += s;
            }
            resString.trim();
            EditText rtext = (EditText) findViewById(R.id.nameKids);
            rtext.setText(resString);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public String getResponse(String quest){
        boolean isQuestion=false;
        String newString="";
        String answer="";
        String[] q = quest.split(" ");
        String[] a = getResources().getStringArray(R.array.questions);
        for (int i = 0; i < q.length; i++) {
            for (String questions : a) {
                if (questions.equalsIgnoreCase(q[i])){
                    isQuestion=true;
                    q[i]="";
                }
            }
        }
        if (isQuestion){
            for (String questions : q) {
                newString=newString+questions+" ";   
            }
        }
        //if () есть в базе данных
        //else идем на wiki
        newString.trim();
        try {
            WikiRequest wikiRequest = new WikiRequest(); 
           answer =  wikiRequest.sendWikiRequest(newString);
           Toast.makeText(this,answer , Toast.LENGTH_LONG).show();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return answer;

    }    

}
