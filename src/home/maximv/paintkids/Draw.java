package home.maximv.paintkids;

import home.maximv.paintkids.R;

import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
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
            int ran = generateRandom(getResources().getInteger(R.integer.count_coloring_pic));
            int pic = getResources().getIdentifier("ml" + ran, "drawable", "home.maximv.paintkids");
            ran = ran==0?1:ran;
            Log.d("PICT", "ml"+ran);
            mBitmap = BitmapFactory.decodeResource(getResources(), pic);
            DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
            widthDisplay = displaymetrics.widthPixels;
            heightDisplay = widthDisplay / mBitmap.getWidth() > 1 ? mBitmap.getWidth() * widthDisplay
                    / mBitmap.getWidth() : mBitmap.getWidth() * mBitmap.getWidth() / widthDisplay;
            mBitmap = Bitmap.createScaledBitmap(mBitmap, widthDisplay, heightDisplay + 130, false);
            break;
        case 3:
            int rand = generateRandom(getResources().getInteger(R.integer.count_prop_pic));
            rand = rand==0?1:rand;
            int pict = getResources().getIdentifier("pr" + rand, "drawable", "home.maximv.paintkids");
            Log.d("PICT", "pr"+rand);
            mBitmap = BitmapFactory.decodeResource(getResources(), pict);
            DisplayMetrics displaymetric = getResources().getDisplayMetrics();
            widthDisplay = displaymetric.widthPixels;
            heightDisplay = displaymetric.heightPixels;
            mBitmap = Bitmap.createScaledBitmap(mBitmap, widthDisplay, heightDisplay, false);
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
