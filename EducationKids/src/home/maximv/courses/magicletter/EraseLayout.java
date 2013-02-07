package home.maximv.courses.magicletter;

import home.maximv.educationkids.R;

import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class EraseLayout extends View {

	private float mX, mY;
    private final static int PAINT_WIDTH = 24;
    private Paint mPaint;
    private Bitmap mBitmap;
    private int count=14;
    private Canvas mCanvas;
    private Paint mBitmapPaint;
    public View backgr;
    Date date = new Date();
    Calendar calendar = Calendar.getInstance();
   
    private int sl_pics [] = {R.drawable.sl1, R.drawable.sl2,R.drawable.sl3, R.drawable.sl4,R.drawable.sl5, R.drawable.sl6,R.drawable.sl7, R.drawable.sl8,R.drawable.sl9,R.drawable.sl10, R.drawable.sl11,
    		R.drawable.sl12,R.drawable.sl13, R.drawable.sl14,R.drawable.sl15, R.drawable.sl16,R.drawable.sl17, R.drawable.sl18,R.drawable.sl19,R.drawable.sl20};
    private int sl_pics_color [] = {R.drawable.sl1_color, R.drawable.sl2_color,R.drawable.sl3_color, R.drawable.sl4_color,R.drawable.sl5_color, R.drawable.sl6_color,R.drawable.sl7_color, R.drawable.sl8_color,
    		R.drawable.sl9_color, R.drawable.sl10_color,R.drawable.sl11_color,R.drawable.sl12_color, R.drawable.sl13_color,R.drawable.sl14_color, R.drawable.sl15_color,R.drawable.sl16_color, R.drawable.sl17_color, 
    		R.drawable.sl18_color,	R.drawable.sl19_color, R.drawable.sl20_color};

	        public EraseLayout(Context context, AttributeSet attrs, int defStyle) {
	                super(context, attrs, defStyle);
	                initialise();
	        }

	        public EraseLayout(Context context, AttributeSet attrs) {
	                super(context, attrs);
	                initialise();
	        }

	        public EraseLayout(Context context) {
	                super(context);
	                initialise();
	        }

	        private void initialise() {
	                mBitmapPaint = new Paint(Paint.DITHER_FLAG);
	                mPaint = new Paint();
	                mPaint.setStyle(Paint.Style.STROKE);
	                mPaint.setStrokeJoin(Paint.Join.ROUND);
	                mPaint.setStrokeCap(Paint.Cap.ROUND);
	                mPaint.setStrokeWidth(PAINT_WIDTH);
	                mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
	        }

	        private Bitmap getBitmap() {
	            if (mBitmap == null) {
	                setBitMap();
	            }
	            return mBitmap;
	        }

	        private void setBitMap(){
	            if (count>sl_pics.length-1) count=0;
                mBitmap = BitmapFactory.decodeResource(getResources(), sl_pics[count]).copy(Bitmap.Config.ARGB_8888, true);
                backgr.setBackgroundResource(sl_pics_color[count]);
                mBitmap=Bitmap.createScaledBitmap(mBitmap, getMeasuredWidth(),getMeasuredHeight(), false);
                mCanvas = new Canvas(mBitmap);
                count++;
	        }
	        @Override
	        protected void onDraw(Canvas canvas) {
               canvas.drawColor(Color.TRANSPARENT);
	        	canvas.drawBitmap(getBitmap(),0,0,mBitmapPaint);
	        }

	        @Override
	        public boolean onTouchEvent(MotionEvent event) {
	                float x = event.getX();
	                float y = event.getY();

	                if (event.getAction() == MotionEvent.ACTION_MOVE) {
	                        mCanvas.drawLine(mX, mY, x, y, mPaint);
	                        invalidate();
	                }
	                   if (event.getAction() == MotionEvent.ACTION_UP) {
	                       if (transparent()){
	                           Calendar cal = Calendar.getInstance();
	                           Toast.makeText(getContext(), "Поздравляю, ты молодец!!!", Toast.LENGTH_SHORT).show();
	                           /* Long sec = (cal.getTimeInMillis()-calendar.getTimeInMillis())*100;
	                           switch (sec.intValue()) {
                            case 0: case 1: case 2: case 3: case 4: case 5:
                                sec=5l;
                                break;
                            case 6: case 7: case 8: case 9: case 10: case 11:
                                sec=4l;
                            default:
                                sec=3l;
                                break;
                            }
	                         
	                           postDelayed(setBitMap, 2000);*/
	                    	   setBitMap();
	                       }
	                   }
	                mX = x;
	                mY = y;
	                return true;
	        }
            private Runnable setBitMap = new Runnable() {
                public void run() {
                    setBitMap();
                }
            };

	        private boolean transparent(){
	            int count=0;
	            for(int x=0;x<getMeasuredWidth();){
	                x=x+20;
	                for(int y=0;y<getMeasuredHeight();){ 
	                    y=y+20;
	                    if(mBitmap.getPixel(x>=getMeasuredWidth()?getMeasuredWidth()-1:x, y>=getMeasuredHeight()?getMeasuredHeight()-1:y) != Color.TRANSPARENT){ 
	                        count++;
	                    }
	                }
	            }
                if (count<6) return true;
                else return false;
	            //	        boolean transparent = (color & 0xff000000) == 0x0;
	        }
	}
