package home.maximv.educationkids;

import home.maximv.voice.Voice;

import java.util.List;

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
        List<ResolveInfo> activities = pm.queryIntentActivities(
        new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
        if (activities.size() != 0) {
            speakButton.setImageResource(R.drawable.mic_on);
        } else {
        speakButton.setEnabled(false);
        speakButton.setImageResource(R.drawable.mic_off);
        }
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
    }
    public void successRegistration() {
        Intent intent = new Intent(this, EducationSelect.class);
        startActivity(intent);

    }
    
    public void recognize(View v) {
            new Voice().recognize(v);
    }
}
