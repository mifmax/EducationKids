package home.maximv.courses.magicletter;

import home.maximv.educationkids.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class Draw extends View {
    //private int color;
    private Paint paint = new Paint();
    private ArrayList<Point> points = new ArrayList<Point>();
    private List<Integer> colors = new ArrayList<Integer>();
    private int ml_pics [] = {R.drawable.ml1, R.drawable.ml2, R.drawable.ml3, R.drawable.ml4, R.drawable.ml5, R.drawable.ml6,
                               R.drawable.ml7, R.drawable.ml8, R.drawable.ml9, R.drawable.ml10,R.drawable.ml11,R.drawable.ml12, 
                               R.drawable.ml13,R.drawable.ml14,R.drawable.ml15,R.drawable.ml16,R.drawable.ml17,R.drawable.ml18};
    private static Random random = new Random(); 
    private ArrayList<ArrayList<Point>> paths =new ArrayList<ArrayList<Point>>();
    private int color;
    
    static int generateRandom(int n) {
        return Math.abs(random.nextInt(n)) % n;
    }
    public Draw(Context context) {
        super(context);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setBackgroundResource(ml_pics[generateRandom(ml_pics.length)]);
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
    }

   @Override
   public void onDraw(Canvas canvas){
	   super.onDraw(canvas);
	   color=paint.getColor();
       int i=0;
	   for(ArrayList<Point> path: paths){
	       paint.setColor(colors.get(i));
           i++;
           drawPath(path, canvas);
       }
	   paint.setColor(color);
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
               colors.add(paint.getColor());
               break;
           default:
               break;
       }
       return true;
   }

 
   public void selectColor(View v) {
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
   public void selectLarch(View v) {
       Builder builder = new AlertDialog.Builder(getContext());
       builder.setTitle("Настройка размера кисти"); 
       builder.setMessage("Выберите размер кисти"); 
       LinearLayout linear=new LinearLayout(getContext()); 
       linear.setOrientation(1); 
       SeekBar seek=new SeekBar(getContext()); 
       linear.addView(seek); 
       builder.setView(linear); 

       builder.setPositiveButton("Ok",new DialogInterface.OnClickListener() 
       { 
           public void onClick(DialogInterface dialog,int id)  
           { 

           } 
       }); 
       builder.show();
   }

}