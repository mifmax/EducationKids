package home.maximv.courses.magicletter;

import home.maximv.educationkids.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.BlurMaskFilter.Blur;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.view.MotionEvent;
import android.view.View;

   public class EraseLayout2 extends View{
        Bitmap bgr;
        Bitmap overlayDefault;
        Bitmap overlay;
        Paint pTouch;
        int X = -100;
        int Y = -100;
        Canvas c2;

        public EraseLayout2(Context context) {
            super(context);

            bgr = BitmapFactory.decodeResource(getResources(),R.drawable.sl1);
            overlayDefault = BitmapFactory.decodeResource(getResources(),R.drawable.sl1_color);
            overlay = BitmapFactory.decodeResource(getResources(),R.drawable.sl1_color).copy(Config.ARGB_8888, true);  
            c2 = new Canvas(overlay);

            pTouch = new Paint(Paint.ANTI_ALIAS_FLAG);         
            pTouch.setXfermode(new PorterDuffXfermode(Mode.CLEAR)); 
            pTouch.setColor(Color.TRANSPARENT);
            pTouch.setMaskFilter(new BlurMaskFilter(15, Blur.NORMAL));

        }

        @Override
        public boolean onTouchEvent(MotionEvent ev) {

            switch (ev.getAction()) {

                case MotionEvent.ACTION_DOWN: {

                    X = (int) ev.getX();
                    Y = (int) ev.getY();
                    invalidate();

                    break;
                }

                case MotionEvent.ACTION_MOVE: {

                        X = (int) ev.getX();
                        Y = (int) ev.getY();
                        invalidate();
                        break;

                }           

                case MotionEvent.ACTION_UP:

                    break;

            }
            return true;
        }


        @Override
        public void onDraw(Canvas canvas){
            super.onDraw(canvas);

            //draw background
            canvas.drawBitmap(bgr, 0, 0, pTouch);
            //copy the default overlay into temporary overlay and punch a hole in it                          
            c2.drawBitmap(overlayDefault, 0, 0, null); //exclude this line to show all as you draw
            c2.drawCircle(X, Y, 80, pTouch);
            //draw the overlay over the background  
            canvas.drawBitmap(overlay, 0, 0, null);

        }

       /* if (myPaintFlag) {
            canvas.drawBitmap(bgr, new Matrix(), null);
            canvas.drawBitmap(overlayDefault, new Matrix(), mPaint);
        }
        else {
            canvas.drawBitmap(bmp1, new Matrix(), mPaint);
            canvas.drawBitmap(bmp2, new Matrix(), null);
        }   */
   
    }


