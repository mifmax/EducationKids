package home.maximv.paintkids;

import java.util.Random;

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

    private Canvas mCanvas;

    private Paint mBitmapPaint;

    public View backgr;

    private static Random random = new Random(); 

    private static int generateRandom(int n) {
        return Math.abs(random.nextInt(n)) % n;
    }

    public EraseLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize();
    }

    public EraseLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public EraseLayout(Context context) {
        super(context);
        initialize();
    }

    private void initialize() {

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

    private void setBitMap() {
        int ran =generateRandom(getResources().getInteger(R.integer.count_erasable_pic));
        int pic = getResources().getIdentifier("sl"+ran, "drawable", "home.maximv.paintkids");
        int pic_color = getResources().getIdentifier("sl"+ran+"_color", "drawable", "home.maximv.paintkids");

        mBitmap = BitmapFactory.decodeResource(getResources(), pic).copy(Bitmap.Config.ARGB_8888, true);
        backgr.setBackgroundResource(pic_color);
        mBitmap = Bitmap.createScaledBitmap(mBitmap, getMeasuredWidth(), getMeasuredHeight(), false);
        mCanvas = new Canvas(mBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.TRANSPARENT);
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
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (transparent()) {
                Toast.makeText(getContext(), "Поздравляю, ты молодец!!!", Toast.LENGTH_SHORT).show();
                setBitMap();
            }
        }
        mX = x;
        mY = y;
        return true;
    }

    private boolean transparent() {
        int count = 0;
        for (int x = 0; x < getMeasuredWidth();) {
            x = x + 20;
            for (int y = 0; y < getMeasuredHeight();) {
                y = y + 20;
                if (mBitmap.getPixel(x >= getMeasuredWidth() ? getMeasuredWidth() - 1 : x,
                        y >= getMeasuredHeight() ? getMeasuredHeight() - 1 : y) != Color.TRANSPARENT) {
                    count++;
                }
            }
        }
        if (count < 6)
            return true;
        else
            return false;
    }
}
