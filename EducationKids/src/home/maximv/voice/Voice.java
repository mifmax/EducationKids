package home.maximv.voice;

import home.maximv.educationkids.R;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.EditText;

public class Voice extends Activity {
    private static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;
   
    /**
    * Handle the click on the start recognition button.
    */
    public void recognize(View v) {
        if (v.getId() == R.id.speakButton) {
            startVoiceRecognitionActivity();
        }
    }
     
    /**
    * Fire an intent to start the speech recognition activity.
    */
    public void startVoiceRecognitionActivity() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speech recognition demo");
        startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE);
    }
     
    /**
     * Handle the results from the recognition activity.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == VOICE_RECOGNITION_REQUEST_CODE && resultCode == RESULT_OK) {
            // Fill the list view with the strings the recognizer thought it could have heard
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String resString = "";
            for (String s : matches)
            {
                resString += s + "\t";
            }
            EditText rtext = (EditText) findViewById(R.id.nameKids);
            rtext.setText(resString);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
