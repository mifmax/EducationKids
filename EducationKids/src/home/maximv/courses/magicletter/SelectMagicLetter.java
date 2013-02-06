package home.maximv.courses.magicletter;

import home.maximv.courses.MerryMathematics;
import home.maximv.educationkids.R;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

public class SelectMagicLetter extends ListActivity{
    private ArrayAdapter<String> adapter;
    private String[] SelectLetters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.education_select);
        SelectLetters = getResources().getStringArray(R.array.SelectLetters);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, SelectLetters);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(itemListener);
    }

    OnItemClickListener itemListener = new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                selectItem(v,position);
            }
    };

    public void selectItem(View v,int position) {

        if (position==adapter.getPosition(SelectLetters[0])){
            Intent intent = new Intent(this, MagicLetter.class);
            intent.putExtra("SelectLetters", 0);
            startActivity(intent);
        }
        if (position==adapter.getPosition(SelectLetters[1])){
            Intent intent = new Intent(this, MagicLetter.class);
            intent.putExtra("SelectLetters", 1);
            startActivity(intent);
        }
        if (position==adapter.getPosition(SelectLetters[2])){
            Intent intent = new Intent(this, MerryMathematics.class);
            intent.putExtra("SelectLetters", 2);
            startActivity(intent);
        }
    }
}
