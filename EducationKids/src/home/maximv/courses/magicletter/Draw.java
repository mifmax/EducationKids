package home.maximv.courses.magicletter;

import home.maximv.educationkids.R;

import java.util.ArrayList;

import android.R.anim;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
/*public class Draw extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//setContentView(home.maximv.courses.magicletter.Draw);
	}
	// идентификаторы для пунктов меню
	private static final int IDM_OPEN = 101;
	private static final int IDM_SAVE = 102;
	public boolean onCreateOptionsMenu(Menu menu)
	{
	    // добавляем пункты меню
		menu.add(Menu.NONE, IDM_OPEN, Menu.NONE, "Открыть");
		menu.add(Menu.NONE, IDM_SAVE, Menu.NONE, "Сохранить");
		return true;
	}
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId()){
		case IDM_OPEN:
			
		    return true;
		case IDM_SAVE:
		    return true;
		}	
		return true	;
	}*/


public class Draw extends View implements OnCreateContextMenuListener{

    private Paint paint = new Paint();
    private ArrayList<Point> points = new ArrayList<Point>();
    private ArrayList<ArrayList<Point>> paths =new ArrayList<ArrayList<Point>>();
    private Context mContext;
    public Draw(Context context) {
        super(context);
        setFocusable(true);
        setFocusableInTouchMode(true);
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        mContext = context;
       /* ContextMenu menu = new ContextMenu(); 
        menu.add(Menu.NONE, IDM_OPEN, Menu.NONE, "Открыть");
    	menu.add(Menu.NONE, IDM_SAVE, Menu.NONE, "Сохранить");
        createContextMenu(menu);*/
        View pic =  findViewById(R.drawable.bg1);
        setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
            	selectColor();
            return true;
            }
        });
        
    }

   @Override
   public void onDraw(Canvas canvas){
	    super.onDraw(canvas);
       this.setBackgroundResource(R.drawable.bg1);
       //this.setBackgroundColor(Color.WHITE);
       paint.setColor(Color.GREEN);
       for(ArrayList<Point> path: paths){
           drawPath(path, canvas);
       }
       drawPath(points, canvas);
    }

   private void drawPath(ArrayList<Point> path, Canvas canvas){
       if (path.size() > 0) {
           Point point0 = path.get(0);
           for (int i = 1; i < path.size(); i++) {
               Point pointNext = path.get(i);
               canvas.drawLine(point0.x, point0.y, pointNext.x, pointNext.y, paint);
               point0 = pointNext;
           }
       }
   }
   @Override
   public boolean onTouchEvent(MotionEvent event){
       switch (event.getActionMasked()) {
           case MotionEvent.ACTION_MOVE:
               points.add(new Point((int)event.getX(), (int)event.getY()));
               invalidate();
               break;
           case MotionEvent.ACTION_UP:
               paths.add(points);
               points = new ArrayList<Point>();
               break;
           default:
               break;
       }
       return true;
   }
public void selectColor() {
	Builder builder = new AlertDialog.Builder(getContext());
	String[] items = {"Красный", "Зелёный", "Синий", "Голубой", "Чёрный", "Белый", "Жёлый", "Розовый"};
	final AlertDialog dialog = builder.setTitle("Выберите цвет кисти").setItems(items, new DialogInterface.OnClickListener() {
	    public void onClick(DialogInterface dialog, int which) {
	        switch (which) {
	            case 0:				// Красный
	              paint.setColor(Color.RED);
	              break;
	            case 1:				// Зелёный
	              paint.setColor(Color.GREEN);
	              break;
	            case 2:				// Синий
	              paint.setColor(Color.BLUE);
	              break;
	            case 3:				// Голубой
	              paint.setColor(0xFF99CCFF);
	              break;
	            case 4:				// Чёрный
	              paint.setColor(Color.BLACK);
	              break;
	            case 5:				// Белый
	              paint.setColor(Color.WHITE);
	              break;
	            case 6:				// Жёлый
	              paint.setColor(Color.YELLOW);
	              break;
	            case 7:				// Розовый
	              paint.setColor(0xFFFFCC99);
	              break;
	        }
	    }
	}).create();
	dialog.show();
	
}

@Override
public void onCreateContextMenu(ContextMenu menu, View v,
		ContextMenuInfo menuinfo) {
	selectColor();
	
}
//}

}