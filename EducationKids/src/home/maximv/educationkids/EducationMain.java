package home.maximv.educationkids;

import home.maximv.dbservices.DbService;
import home.maximv.utils.SpeechRecognition;
import home.maximv.utils.SpeechToText;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    DbService sqh;
    SQLiteDatabase sqdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.education_main);
        PackageManager pm = getPackageManager();
        ImageButton speakButton = (ImageButton) findViewById(R.id.speakButton);
        List<ResolveInfo> activities = pm.queryIntentActivities(
        new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
        if (activities.size() != 0) {
            speakButton.setImageResource(R.drawable.mic_on);
        } else {
        speakButton.setEnabled(false);
        speakButton.setImageResource(R.drawable.mic_off);
        }
        new SpeechToText("Здравствуйте, представьтесь пожалуйста!").start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.education_main, menu);
        return true;
    }

    public void saveLogin(View v) {
        sPref = getSharedPreferences("logins", MODE_PRIVATE);
        Editor ed = sPref.edit();
        login = (EditText) findViewById(R.id.nameKids);
        try {
            ed.putString(login.getText().toString(), login.getText().toString());
            ed.commit();
        } catch (Exception e) {
            Log.d("ERROR", "Ошибка при регистрации");
        }
        Log.d("SUCCESS", "Регистрация прошла успешно");
    }

    public void registration(View v) {
        sPref = getSharedPreferences("logins",MODE_PRIVATE);
        login = (EditText) findViewById(R.id.nameKids);

        String name = sPref.getString(login.getText().toString(), "");
        if (name != "") {
            Toast.makeText(this, name+", вы успешно вошли в систему! ", Toast.LENGTH_SHORT).show();
            successRegistration();
        }else {
            Toast.makeText(this, "Ваше имя не найдено в базе данных", Toast.LENGTH_SHORT).show();
        }

        // Инициализируем наш класс-обёртку
        sqh = new DbService(this);

        // База нам нужна для записи и чтения
        sqdb = sqh.getWritableDatabase();
        
        
        ContentValues values = new ContentValues();
        values.put(DbService.FIRST_NAME, "Максим");
        values.put(DbService.MIDDLE_NAME, "I");
       values.put(DbService.LAST_NAME, "Verakhouski");

        // вызываем метод вставки
        sqdb.insert(DbService.TABLE_NAME, DbService.FIRST_NAME, values);
        Cursor cursor = sqdb.rawQuery("select * from learner", null);
        while (cursor.moveToNext()) {
            // GET COLUMN INDICES + VALUES OF THOSE COLUMNS
            int id = cursor.getInt(cursor.getColumnIndex(DbService.UID));
            String name1 = cursor.getString(cursor
                    .getColumnIndex(DbService.FIRST_NAME));
            Log.i("LOG_TAG", "ROW " + id + " HAS NAME " + name1);
        }
        cursor.close();
        DbService.closeCon(sqh, sqdb);
    }

    public void successRegistration() {
        Intent intent = new Intent(this, EducationSelect.class);
        startActivity(intent);
    }

    public void recognize(View v) {
    	SpeechRecognition.run(this);
            //new Voice().recognize(v);
    }

    /**
     * Handle the results from the recognition activity.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SpeechRecognition.VOICE_RECOGNITION_REQUEST_CODE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String resString = "";
            for (String s : matches)
            {
                resString += s;
            }
            resString.trim();
            EditText rtext = (EditText) findViewById(R.id.nameKids);
            rtext.setText(resString);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
