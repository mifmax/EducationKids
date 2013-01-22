package home.maximv.educationkids;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class EducationMain extends Activity {
    
    private SharedPreferences sPref;
    private EditText login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.education_main);

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
}
