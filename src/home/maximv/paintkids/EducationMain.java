package home.maximv.paintkids;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EducationMain extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.education_main);
    }

    public void registration(View v) {
        Intent intent = new Intent(this, SelectMagicLetter.class);
        startActivity(intent);
    }
}
