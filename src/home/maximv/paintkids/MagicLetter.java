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
import android.graphics.RadialGradient;
import android.graphics.Shader;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectMenu = getIntent().getIntExtra("SelectLetters", 0);
        switch (selectMenu) {
        case 0:
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
            break;
        case 2:
            setContentView(R.layout.fayrypicture);
            EL = (EraseLayout) findViewById(R.id.EraseLayout);
            EL.backgr = findViewById(R.id.fairybackground);
            EL.droit = findViewById(R.id.next);
            break;
        case 3:
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            draw = new Draw(this,null);
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
    }

    public void onPenClick(View v) {
        // mPaint.setXfermode(null);
        mPaint.setAlpha(0xFF);

        switch (v.getId()) {
        case R.id.android_pen_black:
            mPaint.setColor(Color.BLACK);
            break;

        case R.id.android_pen_red:
            mPaint.setColor(Color.RED);
            break;

        case R.id.android_pen_yellow:
            mPaint.setColor(Color.YELLOW);
            break;

        case R.id.android_pen_blue:
            mPaint.setColor(Color.BLUE);
            break;

        case R.id.android_pen_brown:
            mPaint.setColor(Color.rgb(165, 42, 42));
            break;

        case R.id.android_pen_green:
            mPaint.setColor(Color.GREEN);
            break;

        case R.id.android_pen_rose:
            mPaint.setColor(Color.rgb(255, 105, 180));
            break;

        case R.id.android_pen_violet:
            mPaint.setColor(Color.MAGENTA);
            break;

        case R.id.android_pen_emboss:
            mPaint.setShader(null);
            if (mPaint.getMaskFilter() != mEmboss) {
                mPaint.setMaskFilter(mEmboss);
            } else {
                mPaint.setMaskFilter(null);
            }
            break;

        case R.id.android_reveal:
            mPaint.setShader(null);
            mPaint.setXfermode(new PorterDuffXfermode(Mode.LIGHTEN));
            break;

        case R.id.android_erase:
            mPaint.setMaskFilter(null);
            mPaint.setShader(null);
            mPaint.setColor(Color.WHITE);
            break;

        case R.id.android_blur:
            mPaint.setShader(null);
            if (mPaint.getMaskFilter() != mBlur) {
                mPaint.setMaskFilter(mBlur);
            } else {
                mPaint.setMaskFilter(null);
            }
            break;

        case R.id.android_sheid:
            mPaint.setShader(new RadialGradient(8f, 80f, 90f, mPaint.getColor(), Color.WHITE, Shader.TileMode.MIRROR));
            break;

        case R.id.android_simple:
            mPaint.setShader(null);
            mPaint.setMaskFilter(null);
            mPaint.setColor(Color.BLACK);
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
