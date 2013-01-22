package home.maximv.educationkids;

import home.maximv.courses.MerryMathematics;
import home.maximv.courses.Orientation;
import home.maximv.courses.Seasons;
import home.maximv.courses.magicletter.MagicLetter;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

public class EducationSelect extends ListActivity{
    private ArrayAdapter<String> adapter;
    private String[] courses;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.education_select);
        courses = getResources().getStringArray(R.array.courses);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, courses);
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
        
        if (position==adapter.getPosition(courses[0])){
            Intent intent = new Intent(this, MagicLetter.class);
            startActivity(intent);
        }
        if (position==adapter.getPosition(courses[1])){
            Intent intent = new Intent(this, Seasons.class);
            startActivity(intent);
        }
        if (position==adapter.getPosition(courses[2])){
            Intent intent = new Intent(this, MerryMathematics.class);
            startActivity(intent);
        }
        if (position==adapter.getPosition(courses[3])){
            Intent intent = new Intent(this, Orientation.class);
            startActivity(intent);
        }
    }
}
