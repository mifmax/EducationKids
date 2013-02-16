package home.maximv.paintkids;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class EducationMain extends Activity {
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.education_main);
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_splash);
        findViewById(R.id.main_splash).startAnimation(myFadeInAnimation);
        mHandler.postDelayed(new Runnable() {
            public void run() {
            	animation();
            }
        }, 3000);
    }

    private void animation(){
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_droit);
        findViewById(R.id.back).setVisibility(View.VISIBLE);
        findViewById(R.id.back).startAnimation(myFadeInAnimation);
        findViewById(R.id.exit).setVisibility(View.VISIBLE);
    }
    public void registration(View v) {
        Intent intent = new Intent(this, SelectMagicLetter.class);
        startActivity(intent);
    }

    public void exit(View v) {
        finish();
    }
}
