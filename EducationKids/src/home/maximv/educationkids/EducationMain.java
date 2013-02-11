package home.maximv.educationkids;

import home.maximv.db.service.DbLearnerService;
import home.maximv.db.vo.Learner;
import home.maximv.utils.SpeechRecognition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

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

public class EducationMain extends Activity {

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

    public void registration(View v) {
        DbLearnerService lernerService = new DbLearnerService(this);
        login = (EditText) findViewById(R.id.nameKids);
        sendWikiRequest();
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

    protected HttpResponse sendWikiRequest() {
        HttpResponse response = null;
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI(
                    "ru.wikipedia.org/w/api.php?action=opensearch&search=%EC%E0%F1%F2%E5%F0%20%E8%20%EC%E0%F0%E3%E0%F0%E8%F2%E0&prop=info&format=json&inprop=url"));
           response = client.execute(request);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return response;
    }
    public static String convertStreamToString(InputStream inputStream) throws IOException {
        if (inputStream != null) {
            Writer writer = new StringWriter();

            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"),1024);
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                inputStream.close();
            }
            return writer.toString();
        } else {
            return "";
        }
    }
}
