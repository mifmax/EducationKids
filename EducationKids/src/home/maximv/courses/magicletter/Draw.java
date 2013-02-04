package home.maximv.courses.magicletter;

import home.maximv.educationkids.R;

import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class Draw extends View {

    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
    private Paint mBitmapPaint;
    protected Paint mPaint;
    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;
    private int ml_pics [] = {R.drawable.ml1, R.drawable.ml2, R.drawable.ml3, R.drawable.ml4, R.drawable.ml5, R.drawable.ml6,
            R.drawable.ml7, R.drawable.ml8, R.drawable.ml9, R.drawable.ml10,R.drawable.ml11,R.drawable.ml12, 
            R.drawable.ml13,R.drawable.ml14,R.drawable.ml15,R.drawable.ml16,R.drawable.ml17,R.drawable.ml18};
    private static Random random = new Random(); 
    
    static int generateRandom(int n) {
        return Math.abs(random.nextInt(n)) % n;
    }

    public Draw(Context context) {
        super(context);
        mPaint = new Paint();
        mBitmap = Bitmap.createBitmap(1024, 1024, Bitmap.Config.ARGB_8888);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ml3);
        mCanvas = new Canvas(mBitmap);
        mPath = new Path();
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        this.setBackgroundResource(R.drawable.ml1);
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