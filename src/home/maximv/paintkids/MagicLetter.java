package home.maximv.paintkids;

import home.maximv.utils.ColorPickerDialog;
import home.maximv.utils.Places;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MagicLetter extends GraphicsActivity implements ColorPickerDialog.OnColorChangedListener {

    private Paint mPaint;

    private MaskFilter mEmboss;

    private MaskFilter mBlur;

    protected static int selectMenu;

    private EraseLayout EL;

    private Draw draw;

    private float shadowRadius;

    private Animation myFadeInAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectMenu = getIntent().getIntExtra("SelectLetters", 0);
        if (this.getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        switch (selectMenu) {
        case 0:
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            setContentView(R.layout.color_panel);
            draw = (Draw) findViewById(R.id.draw);
            mPaint = draw.mPaint;
            mPaint.setAntiAlias(true);
            mPaint.setDither(true);
            mPaint.setColor(0xFFF00000);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeJoin(Paint.Join.ROUND);
            mPaint.setStrokeCap(Paint.Cap.ROUND);
            mPaint.setStrokeWidth(5);
            mEmboss = new EmbossMaskFilter(new float[] { 1, 1, 1 }, 0.4f, 6, 3.5f);
            mBlur = new BlurMaskFilter(5, BlurMaskFilter.Blur.NORMAL);
            findViewById(R.id.android_reveal).setVisibility(View.INVISIBLE);
            break;
        case 1:
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            setContentView(R.layout.color_panel);
            draw = (Draw) findViewById(R.id.draw);
            mPaint = draw.mPaint;
            mPaint.setAntiAlias(true);
            mPaint.setDither(true);
            mPaint.setColor(0xFFF00000);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeJoin(Paint.Join.ROUND);
            mPaint.setStrokeCap(Paint.Cap.ROUND);
            mPaint.setStrokeWidth(5);
            mEmboss = new EmbossMaskFilter(new float[] { 1, 1, 1 }, 0.4f, 6, 3.5f);
            mBlur = new BlurMaskFilter(5, BlurMaskFilter.Blur.NORMAL);
            findViewById(R.id.android_reveal).setVisibility(View.VISIBLE);
            break;
        case 2:
            setContentView(R.layout.fayrypicture);
            EL = (EraseLayout) findViewById(R.id.EraseLayout);
            EL.backgr = findViewById(R.id.fairybackground);
            EL.droit = findViewById(R.id.next);
            break;
        case 3:
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            draw = new Draw(this, null);
            setContentView(draw);
            mPaint = draw.mPaint;
            mPaint.setAntiAlias(true);
            mPaint.setDither(true);
            mPaint.setColor(0xFF000000);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeJoin(Paint.Join.ROUND);
            mPaint.setStrokeCap(Paint.Cap.ROUND);
            mPaint.setStrokeWidth(5);
            break;
        default:
            break;
        }
        myFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_droit);
    }

    public void onPenClick(View v) {
        mPaint.setXfermode(null);
        findViewById(R.id.android_reveal).clearAnimation();
        findViewById(R.id.android_erase).clearAnimation();
        mPaint.setAlpha(0xFF);

        switch (v.getId()) {
        case R.id.android_pen_black:
            mPaint.setColor(Color.BLACK);
            ColorAnimStop();
            v.startAnimation(myFadeInAnimation);
            break;

        case R.id.android_pen_red:
            mPaint.setColor(Color.RED);
            ColorAnimStop();
            v.startAnimation(myFadeInAnimation);
            break;

        case R.id.android_pen_yellow:
            mPaint.setColor(Color.YELLOW);
            ColorAnimStop();
            v.startAnimation(myFadeInAnimation);
            break;

        case R.id.android_pen_blue:
            mPaint.setColor(Color.BLUE);
            ColorAnimStop();
            v.startAnimation(myFadeInAnimation);
            break;

        case R.id.android_pen_brown:
            mPaint.setColor(Color.rgb(165, 42, 42));
            ColorAnimStop();
            v.startAnimation(myFadeInAnimation);
            break;

        case R.id.android_pen_green:
            mPaint.setColor(Color.GREEN);
            ColorAnimStop();
            v.startAnimation(myFadeInAnimation);
            break;

        case R.id.android_pen_rose:
            mPaint.setColor(Color.rgb(255, 105, 180));
            ColorAnimStop();
            v.startAnimation(myFadeInAnimation);
            break;

        case R.id.android_pen_violet:
            ColorAnimStop();
            mPaint.setColor(Color.rgb(138, 43, 226));
            v.startAnimation(myFadeInAnimation);
            break;

        case R.id.android_pen_emboss:
            if (mPaint.getMaskFilter() != mEmboss) {
                mPaint.setMaskFilter(mEmboss);
                v.startAnimation(myFadeInAnimation);
                findViewById(R.id.android_blur).clearAnimation();
            } else {
                mPaint.setMaskFilter(null);
                v.clearAnimation();
                findViewById(R.id.android_blur).clearAnimation();
            }
            shadowRadius = 0;
            mPaint.setShadowLayer(shadowRadius, 10, 5, Color.LTGRAY);
            findViewById(R.id.android_shadow).clearAnimation();
            findViewById(R.id.android_simple).clearAnimation();
            break;

        case R.id.android_reveal:
            mPaint.setXfermode(new PorterDuffXfermode(Mode.LIGHTEN));
            v.startAnimation(myFadeInAnimation);
            mPaint.setMaskFilter(null);
            findViewById(R.id.android_simple).clearAnimation();
            findViewById(R.id.android_pen_emboss).clearAnimation();
            findViewById(R.id.android_shadow).clearAnimation();
            findViewById(R.id.android_blur).clearAnimation();
            break;

        case R.id.android_erase:
            mPaint.setMaskFilter(null);
            mPaint.setColor(Color.WHITE);
            shadowRadius = 0;
            mPaint.setShadowLayer(shadowRadius, 10, 5, Color.LTGRAY);
            v.startAnimation(myFadeInAnimation);
            findViewById(R.id.android_simple).clearAnimation();
            findViewById(R.id.android_pen_emboss).clearAnimation();
            findViewById(R.id.android_shadow).clearAnimation();
            findViewById(R.id.android_blur).clearAnimation();
            findViewById(R.id.android_reveal).clearAnimation();
            ColorAnimStop();
            break;

        case R.id.android_blur:
            if (mPaint.getMaskFilter() != mBlur) {
                mPaint.setMaskFilter(mBlur);
                v.startAnimation(myFadeInAnimation);
                findViewById(R.id.android_pen_emboss).clearAnimation();
            } else {
                mPaint.setMaskFilter(null);
                v.clearAnimation();
                findViewById(R.id.android_pen_emboss).clearAnimation();
            }
            shadowRadius = 0;
            mPaint.setShadowLayer(shadowRadius, 10, 5, Color.LTGRAY);
            findViewById(R.id.android_simple).clearAnimation();
            findViewById(R.id.android_shadow).clearAnimation();
            break;

        case R.id.android_shadow:
            findViewById(R.id.android_pen_emboss).clearAnimation();
            findViewById(R.id.android_blur).clearAnimation();
            mPaint.setMaskFilter(null);
            if (shadowRadius != 0) {
                shadowRadius = 0;
                v.clearAnimation();
            } else {
                shadowRadius = 5;
                v.startAnimation(myFadeInAnimation);
            }
            mPaint.setShadowLayer(shadowRadius, 10, 5, Color.LTGRAY);
            findViewById(R.id.android_simple).clearAnimation();
            break;

        case R.id.android_simple:
            shadowRadius = 0;
            mPaint.setShadowLayer(shadowRadius, 10, 5, Color.LTGRAY);
            mPaint.setMaskFilter(null);
            mPaint.setColor(Color.RED);
            ColorAnimStop();
            findViewById(R.id.android_pen_red).startAnimation(myFadeInAnimation);
            v.startAnimation(myFadeInAnimation);
            findViewById(R.id.android_pen_emboss).clearAnimation();
            findViewById(R.id.android_blur).clearAnimation();
            findViewById(R.id.android_shadow).clearAnimation();
            findViewById(R.id.android_reveal).clearAnimation();
            break;

        case R.id.android_clear_all:
            Intent intent = getIntent();
            overridePendingTransition(0, 0);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            finish();
            overridePendingTransition(0, 0);
            startActivity(intent);
            break;

        case R.id.android_size:
            size();
            break;
        }
    }

    public void colorChanged(int color) {
        mPaint.setColor(color);
    }

    public void ColorAnimStop() {
        findViewById(R.id.android_pen_black).clearAnimation();
        findViewById(R.id.android_pen_blue).clearAnimation();
        findViewById(R.id.android_pen_brown).clearAnimation();
        findViewById(R.id.android_pen_green).clearAnimation();
        findViewById(R.id.android_pen_red).clearAnimation();
        findViewById(R.id.android_pen_rose).clearAnimation();
        findViewById(R.id.android_pen_violet).clearAnimation();
        findViewById(R.id.android_pen_yellow).clearAnimation();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.magicletter_menu, menu);
        menu.findItem(R.id.NEXT_PROP).setVisible(false);
        if (selectMenu < 2) {
            menu.findItem(R.id.COLOR_MENU).setVisible(true);
        } else {
            menu.findItem(R.id.COLOR_MENU).setVisible(false);
        }
        if (selectMenu == 3) {
            menu.findItem(R.id.NEXT_PROP).setVisible(true);
        }
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (selectMenu < 2) {
            mPaint.setXfermode(null);
            mPaint.setAlpha(0xFF);
        }
        switch (item.getItemId()) {
        case R.id.COLOR_MENU:
            new ColorPickerDialog(this, this, mPaint.getColor()).show();
            return true;
        case R.id.NEXT_PROP:
            Intent intent = getIntent();
            overridePendingTransition(0, 0);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            finish();
            overridePendingTransition(0, 0);
            startActivity(intent);
            break;
        case R.id.SAVE:
            takeScreenshot(true);
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void size() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.brush, (ViewGroup) findViewById(R.id.root));
        AlertDialog.Builder builder = new AlertDialog.Builder(this).setView(layout);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        SeekBar sb = (SeekBar) layout.findViewById(R.id.seekBar1);
        sb.setProgress(5);
        final Button done = (Button) layout.findViewById(R.id.select_size);
        final TextView txt = (TextView) layout.findViewById(R.id.size_value);
        txt.setText("Размер кисти по умолчанию: 5");
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(final SeekBar seekBar, final int progress, boolean fromUser) {
                txt.setText("Вы выбрали кисть: " + progress + " размера");
                mPaint.setStrokeWidth(progress);
                done.setOnClickListener(new OnClickListener() {

                    public void onClick(View v) {
                        if (progress == 0) {
                            seekBar.setProgress(1);
                        } else {
                            alertDialog.dismiss();
                        }
                    }
                });
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private File takeScreenshot(boolean showToast) {
        View v = getWindow().getDecorView();

        v.setDrawingCacheEnabled(true);
        Bitmap cachedBitmap = v.getDrawingCache();
        Bitmap copyBitmap = cachedBitmap.copy(Bitmap.Config.RGB_565, true);
        FileOutputStream output = null;
        File file = null;
        try {
            File path = Places.getScreenshotFolder();
            Calendar cal = Calendar.getInstance();

            file = new File(path,

            cal.get(Calendar.YEAR) + "_" + (1 + cal.get(Calendar.MONTH)) + "_" + cal.get(Calendar.DAY_OF_MONTH) + "_"
                    + cal.get(Calendar.HOUR_OF_DAY) + "_" + cal.get(Calendar.MINUTE) + "_" + cal.get(Calendar.SECOND)
                    + ".png");
            output = new FileOutputStream(file);
            copyBitmap.compress(CompressFormat.PNG, 100, output);
        } catch (FileNotFoundException e) {
            file = null;
            e.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        if (file != null) {
            if (showToast)
                Toast.makeText(getApplicationContext(), "Saved your creation to " + file.getAbsolutePath(),
                        Toast.LENGTH_LONG).show();
            Intent requestScan = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            requestScan.setData(Uri.fromFile(file));
            sendBroadcast(requestScan);

            return file;
        } else {
            return null;
        }
    }

    public void setBitMap(View v) {
        EL.setBitMap();
    }

    public boolean connectionAvailable() {
        boolean connected = false;
        @SuppressWarnings("static-access")
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED
                || connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            connected = true;
        }
        return connected;
    }

}
