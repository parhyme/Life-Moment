package co.parhyme.lifemoment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.anetwork.android.sdk.advertising.AnetworkAdvertising;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    AlertDialog dialog;

    ProgressBar barS;
    ProgressBar barM;
    ProgressBar barH;
    ProgressBar barD;
    ProgressBar barMo;

    private int mProgressStatusS;
    private int mProgressStatusM;
    private int mProgressStatusH;
    private int mProgressStatusD;
    private int mProgressStatusMo;

    private Handler handler1 = new Handler();
    private Handler handler2 = new Handler();
    private Handler handler3 = new Handler();
    private Handler handler4 = new Handler();
    private Handler handler5 = new Handler();

    private Handler handler1a = new Handler();
    private Handler handler2a = new Handler();
    private Handler handler3a = new Handler();
    private Handler handler4a = new Handler();
    private Handler handler5a = new Handler();

    public static final String PREF_FILE = "prefs";
    public static final String CHARGE_MODE = "charge";
    public static final String IS_ENGLISH = "isEn";

    public static boolean isEng = false;
    public static boolean isChargeModeOn = false;

    CalendarTool calendarTool;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnetworkAdvertising.initialize(this,"b6134ca9-b6c5-4522-89cd-8dbfa4deda97");
        AnetworkAdvertising.createVideoAd(getBaseContext());

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        final SharedPreferences prefs = getSharedPreferences(PREF_FILE,MODE_PRIVATE);

//        final TextView OrTV = (TextView) findViewById(R.id.textView);
        final TextView tvS = (TextView) findViewById(R.id.tvS);
        final TextView tvM = (TextView) findViewById(R.id.tvM);
        final TextView tvH = (TextView) findViewById(R.id.tvH);
        final TextView tvD = (TextView) findViewById(R.id.tvD);
        final TextView tvMo = (TextView) findViewById(R.id.tvMo);



        final Typeface typeface2 = Typeface.createFromAsset(getBaseContext().getAssets(),"fonts/arid.ttf");
        final Typeface typeface = Typeface.createFromAsset(getBaseContext().getAssets(),"fonts/msyi.ttf");
        tvS.setTypeface(typeface);
        tvM.setTypeface(typeface);
        tvH.setTypeface(typeface);
        tvD.setTypeface(typeface);
        tvMo.setTypeface(typeface);


        ToggleButton tb = (ToggleButton) findViewById(R.id.toggleButton);

        final Drawable color = getDrawable(R.drawable.cost_prgs);
        final Drawable color2 = getDrawable(R.drawable.cost_prgs2);
        final Drawable color3 = getDrawable(R.drawable.cost_prgs3);
        final Drawable colorM = getDrawable(R.drawable.cost_prgs);
        final Drawable color2M = getDrawable(R.drawable.cost_prgs2);
        final Drawable color3M = getDrawable(R.drawable.cost_prgs3);
        final Drawable colorH = getDrawable(R.drawable.cost_prgs);
        final Drawable color2H = getDrawable(R.drawable.cost_prgs2);
        final Drawable color3H = getDrawable(R.drawable.cost_prgs3);
        final Drawable colorD = getDrawable(R.drawable.cost_prgs);
        final Drawable color2D = getDrawable(R.drawable.cost_prgs2);
        final Drawable color3D = getDrawable(R.drawable.cost_prgs3);
        final Drawable colorMo = getDrawable(R.drawable.cost_prgs);
        final Drawable color2Mo = getDrawable(R.drawable.cost_prgs2);
        final Drawable color3Mo = getDrawable(R.drawable.cost_prgs3);

        barS = (ProgressBar) findViewById(R.id.progressBarSeconds);

        barM = (ProgressBar) findViewById(R.id.progressBarMinutes);


        barH = (ProgressBar) findViewById(R.id.progressBarHours);

        barD = (ProgressBar) findViewById(R.id.progressBarDays);

        barMo = (ProgressBar) findViewById(R.id.progressBarMonths);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });


        final Switch sw = (Switch) findViewById(R.id.switch1);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                prefs.edit().putBoolean(CHARGE_MODE,b).apply();
                isChargeModeOn = prefs.getBoolean(CHARGE_MODE,false);
                if(!isEng) {
                    Toast.makeText(getBaseContext(),"اگر حالت ذخیره فعال نشد ویجت را حذف و دوباره نصب کنید ؛پس از دو بار عوض کردن سوییچ ، برای همیشه درست خواهد شد",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getBaseContext(),"If Saving Mode don't become on, delete the widget and install it again.After two times changing the switch , it will always be working.",Toast.LENGTH_LONG).show();
                }


            }
        });

        sw.toggle();

        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                AnetworkAdvertising.showVideoAd(getBaseContext());

                prefs.edit().putBoolean(IS_ENGLISH,b).apply();
                isEng = prefs.getBoolean(IS_ENGLISH,false);
                if(!isEng){
                    tvS.setTypeface(typeface2);
                    tvM.setTypeface(typeface2);
                    tvH.setTypeface(typeface2);
                    tvD.setTypeface(typeface2);
                    tvMo.setTypeface(typeface2);
                    tvS.setText("در حال بار گذاری...");
                    tvM.setText("در حال بار گذاری...");
                    tvH.setText("در حال بار گذاری...");
                    tvD.setText("در حال بار گذاری...");
                    tvMo.setText("در حال بار گذاری...");
                    sw.setTypeface(typeface2);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                        sw.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                    }
                    sw.setText("حالت ذخیره");
                }else {
                    tvS.setTypeface(typeface);
                    tvM.setTypeface(typeface);
                    tvH.setTypeface(typeface);
                    tvD.setTypeface(typeface);
                    tvMo.setTypeface(typeface);
                    tvS.setText("Loading...");
                    tvM.setText("Loading...");
                    tvH.setText("Loading...");
                    tvD.setText("Loading...");
                    tvMo.setText("Loading...");
                    sw.setTypeface(typeface);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                        sw.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                    }
                    sw.setText("Saving Mode");
                }
            }
        });

        isEng = prefs.getBoolean(IS_ENGLISH,false);
        isChargeModeOn = prefs.getBoolean(CHARGE_MODE,false);

        if(!isEng){
            tvS.setTypeface(typeface2);
            tvM.setTypeface(typeface2);
            tvH.setTypeface(typeface2);
            tvD.setTypeface(typeface2);
            tvMo.setTypeface(typeface2);
            sw.setTypeface(typeface2);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                sw.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
            }
            sw.setText("حالت ذخیره");
        }else {
            sw.setTypeface(typeface);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                sw.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
            }
            sw.setText("Saving Mode");
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                while ( mProgressStatusS < 60) {
                    mProgressStatusS = getSecond();
                    handler1.post(new Runnable() {
                        @Override
                        public void run() {
                            barS.setProgress(mProgressStatusS);
                            if(isEng) {
                                tvS.setText("Seconds: " + mProgressStatusS);
                            }else {
                                tvS.setText("ثانیه ها : " + mProgressStatusS);
                            }
                        }
                    });
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while ( mProgressStatusS < 60) {
                    mProgressStatusS = getSecond();
                    handler1a.post(new Runnable() {
                        @Override
                        public void run() {
                            if(mProgressStatusS>=25)
                                barS.setProgressDrawable(color);
                            if(mProgressStatusS<25)
                                barS.setProgressDrawable(color2);
                            if(mProgressStatusS<20)
                                barS.setProgressDrawable(color3);
                        }
                    });
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mProgressStatusM < 61) {
                    mProgressStatusM = getMinutes();
                    handler2.post(new Runnable() {
                        @Override
                        public void run() {
                            barM.setProgress(mProgressStatusM);
                            if(isEng) {
                                tvM.setText("Minutes: " + mProgressStatusM);
                            }else {
                                tvM.setText("دقیقه ها : " + mProgressStatusM);
                            }

                        }
                    });
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();//according

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mProgressStatusM < 61) {
                    mProgressStatusM = getMinutes();
                    handler2a.post(new Runnable() {
                        @Override
                        public void run() {
                            if(mProgressStatusM>=25)
                                barM.setProgressDrawable(colorM);
                            if(mProgressStatusM<25)
                                barM.setProgressDrawable(color2M);
                            if(mProgressStatusM<20)
                                barM.setProgressDrawable(color3M);
                        }
                    });
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mProgressStatusH < 25) {
                    mProgressStatusH = getHours();
                    handler3.post(new Runnable() {
                        @Override
                        public void run() {
                            barH.setProgress(mProgressStatusH);
                            if(isEng) {
                                tvH.setText("Hours: " + mProgressStatusH);
                            }else {
                                tvH.setText("ساعت ها : " + mProgressStatusH);
                            }
                        }
                    });
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mProgressStatusH < 25) {
                    mProgressStatusH = getHours();
                    handler3a.post(new Runnable() {
                        @Override
                        public void run() {
                            if(mProgressStatusH>=8)
                                barH.setProgressDrawable(colorH);
                            if(mProgressStatusH<8)
                                barH.setProgressDrawable(color2H);
                            if(mProgressStatusH<5)
                                barH.setProgressDrawable(color3H);
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mProgressStatusD < 32) {
                    if(isEng) {
                        mProgressStatusD = getDays();
                    }
                    else {
                        mProgressStatusD = getIrDays();
                    }
                    handler4.post(new Runnable() {
                        @Override
                        public void run() {
                            barD.setProgress(mProgressStatusD);
                            if(isEng) {
                                tvD.setText("Days: " + mProgressStatusD);
                            }else {
                                tvD.setText("روز ها : " + mProgressStatusD);
                            }
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mProgressStatusD < 32) {
                    if(isEng) {
                        mProgressStatusD = getDays();
                    }
                    else {
                        mProgressStatusD = getIrDays();
                    }
                    handler4a.post(new Runnable() {
                        @Override
                        public void run() {
                            if(mProgressStatusD>=10)
                                barD.setProgressDrawable(colorD);
                            if(mProgressStatusD<10)
                                barD.setProgressDrawable(color2D);
                            if(mProgressStatusD<5)
                                barD.setProgressDrawable(color3D);
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mProgressStatusMo < 13) {
                    if(isEng) {
                        mProgressStatusMo = getMonths();
                    }
                    else {
                        mProgressStatusMo = getIrMonths();
                    }
                    handler5.post(new Runnable() {
                        @Override
                        public void run() {
                            barMo.setProgress(mProgressStatusMo);
                            if(isEng) {
                                tvMo.setText("Months: " + mProgressStatusMo);
                            }else {
                                tvMo.setText("ماه ها : " + mProgressStatusMo);
                            }
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mProgressStatusMo < 13) {
                    if(isEng) {
                        mProgressStatusMo = getMonths();
                    }
                    else {
                        mProgressStatusMo = getIrMonths();
                    }
                    handler5a.post(new Runnable() {
                        @Override
                        public void run() {
                            if(mProgressStatusMo>=5)
                                barMo.setProgressDrawable(colorMo);
                            if(mProgressStatusMo<5)
                                barMo.setProgressDrawable(color2Mo);
                            if(mProgressStatusMo<3)
                                barMo.setProgressDrawable(color3Mo);
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (mProgressStatusMo < 62) {
//                    handler5a.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            OrTV.setText
//                                    (getMonths()+"/"+getDays()+" "+getHours()+":"+getMinutes()+":"+getSecond());
//                        }
//                    });
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();

    }

    String url = "http://myket.ir/app/co.parhyme.lifemoment";

    private void showDialog() {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("ارتباط با توسعه دهنده")
                .setMessage("Telegram:   @parhymeco\nEmail:   parhymeco@gmail.com")
                .setPositiveButton("صفحه برنامه", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        commentPage();
                    }
                })
                .setIcon(android.R.drawable.ic_menu_info_details)
                .show();
    }

    private void commentPage() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    private int getSecond() {
        Calendar calendar = new java.util.GregorianCalendar();
        if(calendar.get(Calendar.SECOND)!=60 && calendar.get(Calendar.SECOND)!=0)
            return 60 - calendar.get(Calendar.SECOND);
        else
            return calendar.get(Calendar.SECOND);
    }

    private int getMinutes() {
        Calendar calendar = new java.util.GregorianCalendar();
        if(calendar.get(Calendar.MINUTE)!=60 && calendar.get(Calendar.MINUTE)!=0)
            return 59 - calendar.get(Calendar.MINUTE);
        else
            return calendar.get(Calendar.MINUTE);
    }

    private int getHours() {
        Calendar calendar = new java.util.GregorianCalendar();
        if(calendar.get(Calendar.HOUR_OF_DAY)!=24 && calendar.get(Calendar.HOUR_OF_DAY)!=0)
            return 23 - calendar.get(Calendar.HOUR_OF_DAY);
        else
            return calendar.get(Calendar.HOUR_OF_DAY);
    }

    private int getDays() {

        calendarTool = new CalendarTool();
        if(calendarTool.getGregorianMonth()==2 && calendarTool.getGregorianYear()%4==0) {
            return 29 - calendarTool.getGregorianDay();
        }
        else if(calendarTool.getGregorianMonth()==2 && calendarTool.getGregorianYear()%4!=0){
            return 28 - calendarTool.getGregorianDay();
        }
        else if(calendarTool.getGregorianMonth()==4|calendarTool.getGregorianMonth()==6|calendarTool.getGregorianMonth()==9|calendarTool.getGregorianMonth()==11){
            return 30 -  calendarTool.getGregorianDay();
        }
        else{
            return 31 - calendarTool.getGregorianDay();
        }
    }

    private int getMonths() {

        calendarTool = new CalendarTool();

        return 12 - calendarTool.getGregorianMonth();
    }

    private int getIrDays() {

        calendarTool = new CalendarTool();
        if(!calendarTool.IsLeap(calendarTool.getIranianYear()) && calendarTool.getIranianMonth()==12) {
            return 29- calendarTool.getIranianDay();
        }
        else if(calendarTool.getIranianMonth()==1|calendarTool.getIranianMonth()==2|calendarTool.getIranianMonth()==3|calendarTool.getIranianMonth()==4|calendarTool.getIranianMonth()==5|calendarTool.getIranianMonth()==6){
            return 31 -  calendarTool.getIranianDay();
        }
        else{
            return 30 - calendarTool.getIranianDay();
        }
    }

    private int getIrMonths() {

        calendarTool = new CalendarTool();

        return 12 - calendarTool.getIranianMonth();
    }




}

