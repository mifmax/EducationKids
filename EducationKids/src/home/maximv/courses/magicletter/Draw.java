package home.maximv.courses.magicletter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;

public class Draw extends View {

    private Paint paint = new Paint();
    private ArrayList<Point> points = new ArrayList<Point>();
    private ArrayList<ArrayList<Point>> paths =new ArrayList<ArrayList<Point>>();

    public Draw(Context context) {
        super(context);
        setFocusable(true);
        setFocusableInTouchMode(true);
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
    }

   @Override
   public void onDraw(Canvas canvas){
       super.onDraw(canvas);
       //this.setBackgroundResource(R.drawable.picture_frame);
       this.setBackgroundColor(Color.WHITE);
       paint.setColor(Color.BLACK);
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

}