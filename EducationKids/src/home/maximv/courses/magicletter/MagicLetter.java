package home.maximv.courses.magicletter;

import android.app.Activity;
import android.os.Bundle;

public class MagicLetter extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Draw d = new Draw(this);
        setContentView(d);
    }

}
