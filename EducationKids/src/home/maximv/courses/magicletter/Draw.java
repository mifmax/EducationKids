package home.maximv.courses.magicletter;

import home.maximv.educationkids.R;

import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

public class Draw extends View {

    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
    private Paint mBitmapPaint;
    private int widthDisplay;
    private int heightDisplay;
    protected Paint mPaint;
    protected int selectMenu;

    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;
    private int ml_pics [] = {R.drawable.ml1, R.drawable.ml2, R.drawable.ml3, R.drawable.ml4, R.drawable.ml5, R.drawable.ml6,
            R.drawable.ml7, R.drawable.ml8, R.drawable.ml9, R.drawable.ml10,R.drawable.ml11,R.drawable.ml12, 
            R.drawable.ml13,R.drawable.ml14,R.drawable.ml15,R.drawable.ml16,R.drawable.ml17,R.drawable.ml18};
    private static Random random = new Random(); 

	private static int generateRandom(int n) {
        return Math.abs(random.nextInt(n)) % n;
    }

    public Draw(Context context) {
        super(context);
    }

	public Draw(Context context, AttributeSet attrs) {
		super(context, attrs);

        mPaint = new Paint();
        switch (MagicLetter.selectMenu) {
        case 0:
            mBitmap = Bitmap.createBitmap(1024, 1024, Bitmap.Config.ARGB_8888);
            break;
        case 1:
            mBitmap = BitmapFactory.decodeResource(getResources(), ml_pics[generateRandom(ml_pics.length)]);
            DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
            widthDisplay = displaymetrics.widthPixels;
            heightDisplay = widthDisplay/mBitmap.getWidth()>1?mBitmap.getWidth()*widthDisplay/mBitmap.getWidth():mBitmap.getWidth()*mBitmap.getWidth()/widthDisplay;
            mBitmap = Bitmap.createScaledBitmap(mBitmap, widthDisplay,heightDisplay+130, false);
            break;
        case 2:
            break;
        case 3:
            break;
        default:
            break;
        }
        mCanvas = new Canvas(mBitmap);
        mPath = new Path();
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);
	}

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(0xFFFFFFFF);
        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
        canvas.drawPath(mPath, mPaint);
    }

    private void touch_start(float x, float y) {
        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void touch_move(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }

    private void touch_up() {
        mPath.lineTo(mX, mY);
        mCanvas.drawPath(mPath, mPaint);
        mPath.reset();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
            touch_start(x, y);
            invalidate();
            break;
        case MotionEvent.ACTION_MOVE:
            touch_move(x, y);
            invalidate();
            break;
        case MotionEvent.ACTION_UP:
            touch_up();
            invalidate();
            break;
        }
        return true;
    }

}