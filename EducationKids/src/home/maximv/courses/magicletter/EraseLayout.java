package home.maximv.courses.magicletter;

import home.maximv.educationkids.R;
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

public class EraseLayout extends View {

	private float mX, mY;
    private final static int COATING_COLOR = Color.DKGRAY;
    private final static int PAINT_WIDTH = 24;
    private Paint mPaint;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Paint mBitmapPaint;

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
;
	                mPaint = new Paint();
	                mPaint.setStyle(Paint.Style.STROKE);
	                mPaint.setStrokeJoin(Paint.Join.ROUND);
	                mPaint.setStrokeCap(Paint.Cap.ROUND);
	                mPaint.setStrokeWidth(PAINT_WIDTH);
	                mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
	        }

	        private Bitmap getBitmap() {
	                if (mBitmap == null) {
	                	/*  	                    fos = new FileOutputStream(file);
	                    Bitmap saveBitmap = Bitmap.createBitmap(mBitmap);
	                    Canvas c = new Canvas(saveBitmap);
	                    c.drawColor(0xFFFFFFFF);
	                    c.drawBitmap(mBitmap,0,0,null);
	                    saveBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
	                    saveBitmap.recycle();
                      mBitmap = Bitmap.createBitmap(getMeasuredWidth(),
                                getMeasuredHeight(), Bitmap.Config.ARGB_8888);
*/
//	                	mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sl1);

                        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sl1).copy(Bitmap.Config.ARGB_8888, true);
                        mBitmap=Bitmap.createScaledBitmap(mBitmap, getMeasuredWidth(),getMeasuredHeight(), false);

                        mCanvas = new Canvas(mBitmap);

                   //     mCanvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
	                        //drawColor(c);
                       // mBitmap.eraseColor(Color.TRANSPARENT);
	                }
	                return mBitmap;
	        }

	        @Override
	        protected void onDraw(Canvas canvas) {
               // canvas.drawColor(Color.WHITE, PorterDuff.Mode.CLEAR);
	        	//canvas.drawBitmap(getBitmap(), 0, 0, mBitmapPaint);
	        //	canvas.drawColor(0xFFFFFFFF);
	        	canvas.drawBitmap(getBitmap(),0,0,null);
	        }

	        @Override
	        public boolean onTouchEvent(MotionEvent event) {
	                float x = event.getX();
	                float y = event.getY();

	                if (event.getAction() == MotionEvent.ACTION_MOVE) {
	                        mCanvas.drawLine(mX, mY, x, y, mPaint);
	                        invalidate();
	                }
	                mX = x;
	                mY = y;
	                return true;
	        }
	}
