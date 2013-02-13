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
                next_Anim();
            }
        }, 3000);
    }

    private void next_Anim() {
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_next);
        findViewById(R.id.main_splash).startAnimation(myFadeInAnimation);
        mHandler.postDelayed(new Runnable() {
            public void run() {
                registration(null);
            }
        }, 3500);
    }

    public void registration(View v) {
        Intent intent = new Intent(this, SelectMagicLetter.class);
        startActivity(intent);
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_droit);
        findViewById(R.id.back).setVisibility(View.VISIBLE);
        findViewById(R.id.back).startAnimation(myFadeInAnimation);
        findViewById(R.id.exit).setVisibility(View.VISIBLE);
    }

    public void exit(View v) {
        finish();
    }
}
