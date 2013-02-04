package home.maximv.courses.magicletter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class EraseLayout {

	private float mX, mY;
    private final static int COATING_COLOR = Color.DKGRAY;
    private final static int PAINT_WIDTH = 24;
    private Paint mPaint;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Paint mBitmapPaint;

	public class LotteryLayer extends View {

	        public LotteryLayer(Context context, AttributeSet attrs, int defStyle) {
	                super(context, attrs, defStyle);
	                initialise();
	        }

	        public LotteryLayer(Context context, AttributeSet attrs) {
	                super(context, attrs);
	                initialise();
	        }

	        public LotteryLayer(Context context) {
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
	                        mBitmap = Bitmap.createBitmap(getMeasuredWidth(),
	                                        getMeasuredHeight(), Bitmap.Config.ARGB_8888);
	                        mCanvas = new Canvas(mBitmap);
	                        mCanvas.drawColor(COATING_COLOR);
	                }
	                return mBitmap;
	        }

	        @Override
	        protected void onDraw(Canvas canvas) {
	                canvas.drawBitmap(getBitmap(), 0, 0, mBitmapPaint);
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
}