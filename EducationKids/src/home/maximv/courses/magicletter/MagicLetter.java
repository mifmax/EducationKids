package home.maximv.courses.magicletter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import home.maximv.courses.magicletter.Draw;
public class MagicLetter extends Activity {
    Draw d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        d = new Draw(this);
        setContentView(d);
    
    }
    // идентификаторы для пунктов меню
    private static final int IDM_COLOR = 101;
    private static final int IDM_LARCH = 201;
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // добавляем пункты меню
        menu.add(Menu.NONE, IDM_COLOR, Menu.NONE, "Выбор цвета");
        menu.add(Menu.NONE, IDM_LARCH, Menu.NONE, "Выбор размера кисти");
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId()==IDM_COLOR){
                d.selectColor(d);
        }
        if (item.getItemId()==IDM_LARCH){
            d.selectLarch(d);
    }   
        return true ;
    }
}
