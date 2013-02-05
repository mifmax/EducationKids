
package home.maximv.courses.magicletter;

import home.maximv.educationkids.R;
import home.maximv.utils.ColorPickerDialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MagicLetter extends GraphicsActivity implements
		ColorPickerDialog.OnColorChangedListener {
    private Paint mPaint;
    private MaskFilter mEmboss;
    private MaskFilter mBlur;
    private final int buttonid=200;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		Draw draw = new Draw(this);

	/*	ImageView pen_black = new ImageView(this);
        pen_black.setId(buttonid+1);
        pen_black.setImageResource(R.drawable.pen_black);
        pen_black.setOnClickListener(onChangeColorClick);
        
        ImageView pen_red = new ImageView(this);
	    pen_red.setId(buttonid+2);
	    pen_red.setImageResource(R.drawable.pen_red);
	    pen_red.setOnClickListener(onChangeColorClick);
	    
	    ImageView pen_yellow = new ImageView(this);
	    pen_yellow.setId(buttonid+3);
	    pen_yellow.setImageResource(R.drawable.pen_yellow);
	    pen_yellow.setOnClickListener(onChangeColorClick);

        
        ImageView pen_blue = new ImageView(this);
        pen_blue.setId(buttonid+4);
        pen_blue.setImageResource(R.drawable.pen_blue);
        pen_blue.setOnClickListener(onChangeColorClick);
        
        ImageView pen_brown = new ImageView(this);
        pen_brown.setId(buttonid+5);
        pen_brown.setImageResource(R.drawable.pen_brown);
        pen_brown.setOnClickListener(onChangeColorClick);
        
        ImageView pen_green = new ImageView(this);
        pen_green.setId(buttonid+6);
        pen_green.setImageResource(R.drawable.pen_green);
        pen_green.setOnClickListener(onChangeColorClick);
        
        ImageView pen_rose = new ImageView(this);
        pen_rose.setId(buttonid+7);
        pen_rose.setImageResource(R.drawable.pen_rose);
        pen_rose.setOnClickListener(onChangeColorClick);
        
        ImageView pen_violet = new ImageView(this);
        pen_violet.setId(buttonid+8);
        pen_violet.setImageResource(R.drawable.pen_violet);
        pen_violet.setOnClickListener(onChangeColorClick);

        ImageView pen_emboss = new ImageView(this);
        pen_emboss.setId(buttonid+9);
        pen_emboss.setImageResource(R.drawable.emboss);
        pen_emboss.setOnClickListener(onChangeColorClick);

        ImageView erase = new ImageView(this);
        erase.setId(buttonid+10);
        erase.setImageResource(R.drawable.erase);
        erase.setOnClickListener(onChangeColorClick);

        ImageView blur = new ImageView(this);
        blur.setId(buttonid+11);
        blur.setImageResource(R.drawable.blur);
        blur.setOnClickListener(onChangeColorClick);

        ImageView sheid = new ImageView(this);
        sheid.setId(buttonid+12);
        sheid.setImageResource(R.drawable.sheid);
        sheid.setOnClickListener(onChangeColorClick);

        ImageView simple = new ImageView(this);
        simple.setId(buttonid+13);
        simple.setImageResource(R.drawable.simple);
        simple.setOnClickListener(onChangeColorClick);

        ImageView clear_all = new ImageView(this);
        clear_all.setId(buttonid+14);
        clear_all.setImageResource(R.drawable.clear_all);
        clear_all.setOnClickListener(onChangeColorClick);

        ImageView size = new ImageView(this);
        size.setId(buttonid+15);
        size.setImageResource(R.drawable.size);
        size.setOnClickListener(onChangeColorClick);

        
	    LinearLayout llchild = new LinearLayout(this);
	    llchild.setGravity(Gravity.TOP); 
	    llchild.setOrientation(LinearLayout.HORIZONTAL); 
        llchild.addView(pen_black);
        llchild.addView(pen_red);
        llchild.addView(pen_yellow);
        llchild.addView(pen_blue);
        llchild.addView(pen_brown);
        llchild.addView(pen_green);
        llchild.addView(pen_rose);
        llchild.addView(pen_violet);
        llchild.addView(pen_emboss);
        llchild.addView(blur);
        llchild.addView(erase);
   */
	    LinearLayout ll = new LinearLayout(this);
	    ll.setOrientation(LinearLayout.VERTICAL); 
	    ll.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT)); 
	    ll.setGravity(Gravity.TOP);
        getLayoutInflater().inflate(R.layout.color_panel, ll);
        
       // ll.addView(draw); 
        LinearLayout llchild = (LinearLayout) getLayoutInflater().inflate(R.layout.color_panel_top, ll);
        llchild.addView(draw,1);
       // ll.addView(llchild); 
        setContentView(ll); 

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
	}
	
    public void onPenClick(View v) {
            mPaint.setXfermode(null);
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

            case R.id.android_erase:
                mPaint.setShader(null);
               // mPaint.setColor(Color.WHITE);
                mPaint.setXfermode(new PorterDuffXfermode(Mode.LIGHTEN));
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
                mPaint.setShader(new RadialGradient(8f, 80f, 90f, mPaint.getColor(),Color.WHITE, Shader.TileMode.MIRROR));
                break;
     
            }  
    }
	
	OnClickListener onChangeColorClick = new OnClickListener() {
	    @Override
	    public void onClick(View v) {
            mPaint.setXfermode(null);
            mPaint.setAlpha(0xFF);

	        switch (v.getId()) {
            case buttonid+1:
                mPaint.setColor(Color.BLACK);
                break;

            case buttonid+2:
                mPaint.setColor(Color.RED);
                break;

            case buttonid+3:
                mPaint.setColor(Color.YELLOW);
                break;

            case buttonid+4:
                mPaint.setColor(Color.BLUE);
                break;

            case buttonid+5:
                mPaint.setColor(Color.rgb(165, 42, 42));
                break;

            case buttonid+6:
                mPaint.setColor(Color.GREEN);
                break;

            case buttonid+7:
                mPaint.setColor(Color.rgb(255, 105, 180));
                break;

            case buttonid+8:
                mPaint.setColor(Color.MAGENTA);
                break;

            case buttonid+9:
                mPaint.setShader(null);
                if (mPaint.getMaskFilter() != mEmboss) {
                    mPaint.setMaskFilter(mEmboss);
                } else {
                    mPaint.setMaskFilter(null);
                }
                break;

            case buttonid+10:
                mPaint.setShader(null);
                mPaint.setColor(Color.WHITE);
                mPaint.setXfermode(new PorterDuffXfermode(Mode.LIGHTEN));
                break;

            case buttonid+11:
                mPaint.setShader(null);
                if (mPaint.getMaskFilter() != mBlur) {
                    mPaint.setMaskFilter(mBlur);
                } else {
                    mPaint.setMaskFilter(null);
                }
                break;
            case buttonid+12:
                mPaint.setShader(new RadialGradient(8f, 80f, 90f, mPaint.getColor(),Color.WHITE, Shader.TileMode.MIRROR));
                break;
     
	        }
	    }
	};
	
	public void colorChanged(int color) {
		mPaint.setColor(color);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.magicletter_menu, menu);
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		super.onPrepareOptionsMenu(menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		mPaint.setXfermode(null);
		mPaint.setAlpha(0xFF);

		switch (item.getItemId()) {
		case R.id.COLOR_MENU_ID:
			new ColorPickerDialog(this, this, mPaint.getColor()).show();
			return true;
		case R.id.EMBOSS_MENU_ID:
			if (mPaint.getMaskFilter() != mEmboss) {
				mPaint.setMaskFilter(mEmboss);
			} else {
				mPaint.setMaskFilter(null);
			}
			return true;
		case R.id.BLUR_MENU_ID:
			if (mPaint.getMaskFilter() != mBlur) {
				mPaint.setMaskFilter(mBlur);
			} else {
				mPaint.setMaskFilter(null);
			}
			return true;
		case R.id.SIZE_MENU_ID:
			LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View layout = inflater.inflate(R.layout.brush,
					(ViewGroup) findViewById(R.id.root));
			AlertDialog.Builder builder = new AlertDialog.Builder(this)
					.setView(layout);
			final AlertDialog alertDialog = builder.create();
			alertDialog.show();
			SeekBar sb = (SeekBar) layout.findViewById(R.id.seekBar1);
			sb.setProgress(5);
			final Button done = (Button) layout.findViewById(R.id.select_size);
			final TextView txt = (TextView) layout
					.findViewById(R.id.size_value);
			txt.setText("Размер кисти по умолчанию: 5");
			sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
				public void onProgressChanged(SeekBar seekBar,
						final int progress, boolean fromUser) {
					// Do something here with new value
					txt.setText("Вы выбрали кисть: " + progress+" размера");
					mPaint.setStrokeWidth(progress);
					done.setOnClickListener(new OnClickListener() {

						public void onClick(View v) {
							if (progress == 0) {
								Toast.makeText(
										getApplicationContext(),
										"Please select atleast 1 as brush size.",
										Toast.LENGTH_SHORT).show();
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
			return true;
		case R.id.ERASE_MENU_ID:
            mPaint.setMaskFilter(null);
			mPaint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
			return true;
		case R.id.CLEAR_ALL:
			Intent intent = getIntent();
			overridePendingTransition(0, 0);
			intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			finish();
			overridePendingTransition(0, 0);
			startActivity(intent);
			return true;
		case R.id.SAVE:
			takeScreenshot(true);
			break;
		case R.id.SHARE:
			File screenshotPath = takeScreenshot(false);
			Intent i = new Intent();
			i.setAction(Intent.ACTION_SEND);
			i.setType("image/png");
			i.putExtra(Intent.EXTRA_SUBJECT,
					getString(home.maximv.educationkids.R.string.share_title_template));
			i.putExtra(Intent.EXTRA_TEXT,
					getString(home.maximv.educationkids.R.string.share_text_template));
			i.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(screenshotPath));
			try {
				startActivity(Intent.createChooser(i,
						getString(home.maximv.educationkids.R.string.toolbox_share_title)));
			} catch (android.content.ActivityNotFoundException ex) {
				Toast.makeText(this.getApplicationContext(),
				        home.maximv.educationkids.R.string.no_way_to_share,
						Toast.LENGTH_LONG).show();
			}
			break;
		case R.id.ABOUT:
			try {
				AlertDialog.Builder alert = new AlertDialog.Builder(this);

				alert.setTitle("Acrylic Paint - Info");

				String msg1 = "- Acrylic Paint is a coloring tool for kids. Pick your colors and start painting.";
				String msg2 = "- You can save your creation or directly share it from your device.";

				alert.setMessage(msg1 + "\n" + msg2);

				alert.setPositiveButton("Rate It.",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {

								Intent intent = new Intent(Intent.ACTION_VIEW);
								intent.setData(Uri
										.parse("market://details?id=anupam.acrylic"));
								startActivity(intent);

							}
						});

				alert.setNegativeButton("No, Thanks.",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
							}
						});

				alert.show();
			} catch (Exception e) {
			}

		}
		return super.onOptionsItemSelected(item);
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

			cal.get(Calendar.YEAR) + "_" + (1 + cal.get(Calendar.MONTH)) + "_"
					+ cal.get(Calendar.DAY_OF_MONTH) + "_"
					+ cal.get(Calendar.HOUR_OF_DAY) + "_"
					+ cal.get(Calendar.MINUTE) + "_" + cal.get(Calendar.SECOND)
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
				Toast.makeText(getApplicationContext(),
						"Saved your creation to " + file.getAbsolutePath(),
						Toast.LENGTH_LONG).show();
			// sending a broadcast to the media scanner so it will scan the new
			// screenshot.
			Intent requestScan = new Intent(
					Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
			requestScan.setData(Uri.fromFile(file));
			sendBroadcast(requestScan);

			return file;
		} else {
			return null;
		}
	}

	public boolean connectionAvailable() {
		boolean connected = false;
		@SuppressWarnings("static-access")
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
		if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
				.getState() == NetworkInfo.State.CONNECTED
				|| connectivityManager.getNetworkInfo(
						ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
			connected = true;
		}
		return connected;
	}
}
