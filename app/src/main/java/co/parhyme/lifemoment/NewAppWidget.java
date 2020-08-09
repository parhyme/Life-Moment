package co.parhyme.lifemoment;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class NewAppWidget extends AppWidgetProvider {

    static CalendarTool calendarTool;
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);

        SharedPreferences preferences = context.getSharedPreferences(MainActivity.PREF_FILE,Context.MODE_PRIVATE);

        if(preferences.getBoolean(MainActivity.IS_ENGLISH,false)) {

            if (getIrMonths() == 4 | getIrMonths() == 6 | getIrMonths() == 9 | getIrMonths() == 11) {
                views.setProgressBar(R.id.progressBarDays, 30, getDays(), false);
            }else {
                views.setProgressBar(R.id.progressBarDays, 31, getDays(), false);
            }

            views.setProgressBar(R.id.progressBarMonths, 12, getMonths(), false);
            views.setProgressBar(R.id.progressBarHours, 24, getHours(), false);
            views.setProgressBar(R.id.progressBarMinutes, 60, getMinutes(), false);
            views.setProgressBar(R.id.progressBarSeconds, 60, getSecond(), false);

            views.setTextViewText(R.id.tvD, "Days: " + getDays());
            views.setTextViewText(R.id.tvMo, "Months: " + getMonths());
            views.setTextViewText(R.id.tvH, "Hours: " + getHours());
            views.setTextViewText(R.id.tvM, "Minutes: " + getMinutes());
            views.setTextViewText(R.id.tvS, "Seconds: " + getSecond());
        }else {
            if (getIrMonths() == 1 | getIrMonths() == 2 | getIrMonths() == 3 | getIrMonths() == 4 | getIrMonths() == 5 | getIrMonths() == 6) {
                views.setProgressBar(R.id.progressBarDays, 31, getIrDays(), false);
            }else {
                views.setProgressBar(R.id.progressBarDays, 30, getIrDays(), false);
            }
            views.setProgressBar(R.id.progressBarMonths, 12, getIrMonths(), false);
            views.setProgressBar(R.id.progressBarHours, 24, getHours(), false);
            views.setProgressBar(R.id.progressBarMinutes, 60, getMinutes(), false);
            views.setProgressBar(R.id.progressBarSeconds, 60, getSecond(), false);

            views.setTextViewText(R.id.tvD, "Days: " + getIrDays());
            views.setTextViewText(R.id.tvMo, "Months: " + getIrMonths());
            views.setTextViewText(R.id.tvH, "Hours: " + getHours());
            views.setTextViewText(R.id.tvM, "Minutes: " + getMinutes());
            views.setTextViewText(R.id.tvS, "Seconds: " + getSecond());


        }
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them

        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
            SharedPreferences preferences = context.getSharedPreferences(MainActivity.PREF_FILE, Context.MODE_PRIVATE);

            Intent intent = new Intent(context, NewAppWidget.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);


            if (!preferences.getBoolean(MainActivity.CHARGE_MODE,true)) {
                if(preferences.getBoolean(MainActivity.IS_ENGLISH,false)) {

                    if (getIrMonths() == 4 | getIrMonths() == 6 | getIrMonths() == 9 | getIrMonths() == 11) {
                        views.setProgressBar(R.id.progressBarDays, 30, getDays(), false);
                    }else {
                        views.setProgressBar(R.id.progressBarDays, 31, getDays(), false);
                    }

                    views.setProgressBar(R.id.progressBarMonths, 12, getMonths(), false);
                    views.setProgressBar(R.id.progressBarHours, 24, getHours(), false);
                    views.setProgressBar(R.id.progressBarMinutes, 60, getMinutes(), false);
                    views.setProgressBar(R.id.progressBarSeconds, 60, getSecond(), false);

                    views.setTextViewText(R.id.tvD, "Days: " + getDays());
                    views.setTextViewText(R.id.tvMo, "Months: " + getMonths());
                    views.setTextViewText(R.id.tvH, "Hours: " + getHours());
                    views.setTextViewText(R.id.tvM, "Minutes: " + getMinutes());
                    views.setTextViewText(R.id.tvS, "Seconds: " + getSecond());
                }else {
                    if (getIrMonths() == 1 | getIrMonths() == 2 | getIrMonths() == 3 | getIrMonths() == 4 | getIrMonths() == 5 | getIrMonths() == 6) {
                        views.setProgressBar(R.id.progressBarDays, 31, getIrDays(), false);
                    }else {
                        views.setProgressBar(R.id.progressBarDays, 30, getIrDays(), false);
                    }
                    views.setProgressBar(R.id.progressBarMonths, 12, getIrMonths(), false);
                    views.setProgressBar(R.id.progressBarHours, 24, getHours(), false);
                    views.setProgressBar(R.id.progressBarMinutes, 60, getMinutes(), false);
                    views.setProgressBar(R.id.progressBarSeconds, 60, getSecond(), false);

                    views.setTextViewText(R.id.tvD, "Days: " + getIrDays());
                    views.setTextViewText(R.id.tvMo, "Months: " + getIrMonths());
                    views.setTextViewText(R.id.tvH, "Hours: " + getHours());
                    views.setTextViewText(R.id.tvM, "Minutes: " + getMinutes());
                    views.setTextViewText(R.id.tvS, "Seconds: " + getSecond());


                }


                try {
                    pendingIntent.send();
                } catch (PendingIntent.CanceledException e) {
                    e.printStackTrace();
                }
            }


            views.setOnClickPendingIntent(R.id.activity_main, pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {

    }

    @Override
    public void onEnabled(Context context) {

        RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.new_app_widget);
        SharedPreferences preferences = context.getSharedPreferences(MainActivity.PREF_FILE,Context.MODE_PRIVATE);
        if(preferences.getBoolean(MainActivity.IS_ENGLISH,false)) {

            if (getIrMonths() == 4 | getIrMonths() == 6 | getIrMonths() == 9 | getIrMonths() == 11) {
                views.setProgressBar(R.id.progressBarDays, 30, getDays(), false);
            }else {
                views.setProgressBar(R.id.progressBarDays, 31, getDays(), false);
            }

            views.setProgressBar(R.id.progressBarMonths, 12, getMonths(), false);
            views.setProgressBar(R.id.progressBarHours, 24, getHours(), false);
            views.setProgressBar(R.id.progressBarMinutes, 60, getMinutes(), false);
            views.setProgressBar(R.id.progressBarSeconds, 60, getSecond(), false);

            views.setTextViewText(R.id.tvD, "Days: " + getDays());
            views.setTextViewText(R.id.tvMo, "Months: " + getMonths());
            views.setTextViewText(R.id.tvH, "Hours: " + getHours());
            views.setTextViewText(R.id.tvM, "Minutes: " + getMinutes());
            views.setTextViewText(R.id.tvS, "Seconds: " + getSecond());
        }else {
            if (getIrMonths() == 1 | getIrMonths() == 2 | getIrMonths() == 3 | getIrMonths() == 4 | getIrMonths() == 5 | getIrMonths() == 6) {
                views.setProgressBar(R.id.progressBarDays, 31, getIrDays(), false);
            }else {
                views.setProgressBar(R.id.progressBarDays, 30, getIrDays(), false);
            }
            views.setProgressBar(R.id.progressBarMonths, 12, getIrMonths(), false);
            views.setProgressBar(R.id.progressBarHours, 24, getHours(), false);
            views.setProgressBar(R.id.progressBarMinutes, 60, getMinutes(), false);
            views.setProgressBar(R.id.progressBarSeconds, 60, getSecond(), false);

            views.setTextViewText(R.id.tvD, "Days: " + getIrDays());
            views.setTextViewText(R.id.tvMo, "Months: " + getIrMonths());
            views.setTextViewText(R.id.tvH, "Hours: " + getHours());
            views.setTextViewText(R.id.tvM, "Minutes: " + getMinutes());
            views.setTextViewText(R.id.tvS, "Seconds: " + getSecond());


        }
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    private static int getSecond() {
        Calendar calendar = new GregorianCalendar();
        if(calendar.get(Calendar.SECOND)!=60 && calendar.get(Calendar.SECOND)!=0)
            return 60 - calendar.get(Calendar.SECOND);
        else
            return calendar.get(Calendar.SECOND);
    }

    private static int getMinutes() {
        Calendar calendar = new GregorianCalendar();
        if(calendar.get(Calendar.MINUTE)!=60 && calendar.get(Calendar.MINUTE)!=0)
            return 59 - calendar.get(Calendar.MINUTE);
        else
            return calendar.get(Calendar.MINUTE);
    }

    private static int getHours() {
        Calendar calendar = new GregorianCalendar();
        if(calendar.get(Calendar.HOUR_OF_DAY)!=24 && calendar.get(Calendar.HOUR_OF_DAY)!=0)
            return 23 - calendar.get(Calendar.HOUR_OF_DAY);
        else
            return calendar.get(Calendar.HOUR_OF_DAY);
    }

    private static int getDays() {

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

    private static int getMonths() {

        calendarTool = new CalendarTool();

        return 12 - calendarTool.getGregorianMonth();
    }

    private static int getIrDays() {

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

    private static int getIrMonths() {

        calendarTool = new CalendarTool();

        return 12 - calendarTool.getIranianMonth();
    }


}

