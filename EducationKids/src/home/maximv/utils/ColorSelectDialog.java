package home.maximv.utils;

import home.maximv.educationkids.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class ColorSelectDialog extends Activity {
  
  String[] data = {"black", "red", "white", "brown", "rose", "green", "blue", "yellow", "orange", "j", "k"};
  
  GridView gvMain;
  ArrayAdapter<String> adapter;

  
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectcolor);
        adapter = new ArrayAdapter<String>(this, R.layout.itemcolorselect, R.id.tvText, data);
        gvMain = (GridView) findViewById(R.id.colorSelectTable);
        gvMain.setAdapter(adapter);
        adjustGridView();
    }


    private void adjustGridView() {
        gvMain.setNumColumns(GridView.AUTO_FIT);
        gvMain.setColumnWidth(80);
        gvMain.setVerticalSpacing(5);
        gvMain.setHorizontalSpacing(5);
      }
}