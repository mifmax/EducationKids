package home.maximv.courses.magicletter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;

public class Draw extends View {
   private Paint paint = new Paint();
   private List<Point> points = new ArrayList<Point>(); 
   public Draw(Context context) {
        super(context);
        setFocusable(true);
        setFocusableInTouchMode(true);
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas){
        //super.onDraw(canvas);
        
        paint.setColor(Color.BLACK);
        for (Point point : points) {
            canvas.drawCircle(point.x, point.y, 2, paint); 
        }
    }

   @Override
   public boolean onTouchEvent(MotionEvent event) {
       
       Point point = new Point();
       point.x = (int) event.getX();
       point.y = (int) event.getY();
       points.add(point); 
      
       invalidate();
    return true;
   }
}

/*private static final String TAG = "DrawView";
public DrawView(Context context) {
super(context);
setFocusable(true);
setFocusableInTouchMode(true);
this.setOnTouchListener(this);
paint.setColor(Color.WHITE);
paint.setAntiAlias(true); }
*/
/*@Override
public void onDraw(Canvas canvas) {
for (Point point : points) {
canvas.drawCircle(point.x, point.y, 45, paint);
//Log.d(TAG, "Painting: "+point);
}
}*/
/*public boolean onTouch(View view, MotionEvent event) {
// if(event.getAction() != MotionEvent.ACTION_DOWN)
//return super.onTouchEvent(event);
Point point = new Point();
point.x = event.getX();
point.y = event.getY();
points.add(point);
invalidate();
return true;
}}class Point {
float x, y;
@Override
public String toString() {
return x + ", " + y;
}*/

