package com.changzhoureconfig.yimeng.com.reconfigapplication.ui.views;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

import com.changzhoureconfig.yimeng.com.reconfigapplication.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期时间选择控件
 *
 * @author 大漠
 */
public class DateTimePickerDialog implements OnDateChangedListener, OnTimeChangedListener {
    private DatePicker datePicker;
    private TimePicker timePicker;
    private AlertDialog ad;
    private String dateTime;
    private String initDateTime;
    private Activity activity;
    private String returndateTime;

    /**
     * 日期时间弹出选择框构
     *
     * @param activity：调用的父activity
     */
    public DateTimePickerDialog(Activity activity) {
        this.activity = activity;
    }

    @SuppressLint("SimpleDateFormat")
    public void init(DatePicker datePicker, TimePicker timePicker, Calendar calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        initDateTime = sdf.format(calendar.getTime());
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), this);
        timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
        timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
//        timePicker.setCurrentMinute(0);
    }

    public void init(DatePicker datePicker, TimePicker timePicker) {
        Calendar calendar = Calendar.getInstance();
        init(datePicker, timePicker, calendar);
    }

    @SuppressLint("SimpleDateFormat")
    public Calendar StringToCalendar(String pattern, String datetime) {
        //String pattern="yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            Date date = sdf.parse(datetime);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            //calendar.add(Calendar.MONTH, 1);
            return calendar;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return Calendar.getInstance();
    }

    /**
     * 弹出日期时间选择框
     *
     * @param dateTimeTextEdite 需要设置的日期时间文本编辑框
     * @param type:             0为日期时间类型:yyyy-MM-dd HH:mm:ss
     *                          1为日期类型:yyyy-MM-dd
     *                          2为时间类型:HH:mm:ss
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public AlertDialog dateTimePicKDialog(final TextView dateTimeTextEdite, int type, Calendar c) {
//        Calendar c = StringToCalendar("yyyy-MM-dd HH:mm" ,dateTimeTextEdite.getText().toString());
//        Calendar c = StringToCalendar("yyyy-MM-dd HH:mm" ,calendar.toString());
//    	Calendar c = calendar.getInstance();
        switch (type) {
            case 1:
                new DatePickerDialog(activity,
                        new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker datePicker, int year, int monthOfYear,
                                                  int dayOfMonth) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(datePicker.getYear(), datePicker.getMonth(),
                                        datePicker.getDayOfMonth());
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                dateTime = sdf.format(calendar.getTime());
                                dateTimeTextEdite.setText(dateTime);
                            }
                        },
                        c.get(Calendar.YEAR),
                        c.get(Calendar.MONTH),
                        c.get(Calendar.DATE)).show();
                return null;
            case 2:
                new TimePickerDialog(activity,
                        new TimePickerDialog.OnTimeSetListener() {
                            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(Calendar.YEAR, Calendar.MONTH,
                                        Calendar.DAY_OF_MONTH, timePicker.getCurrentHour(),
                                        timePicker.getCurrentMinute());
                                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                                dateTime = sdf.format(calendar.getTime());
                                dateTimeTextEdite.setText(dateTime);
                            }
                        },
                        c.get(Calendar.HOUR_OF_DAY),
                        c.get(Calendar.MINUTE),
                        true).show();
                return null;
            default:
                LinearLayout dateTimeLayout = (LinearLayout) activity.getLayoutInflater().inflate(R.layout.z_datetime, null);
                datePicker = (DatePicker) dateTimeLayout.findViewById(R.id.datepicker);
                timePicker = (TimePicker) dateTimeLayout.findViewById(R.id.timepicker);
                timePicker.setIs24HourView(true);
                init(datePicker, timePicker, c);
//            resizePikcer(timePicker, true);//调整timepicker大小
                timePicker.setOnTimeChangedListener(this);
                ad = new AlertDialog.Builder(activity)/*.setIcon(R.drawable.datetimeicon)*/.setTitle(initDateTime).setView(dateTimeLayout).setPositiveButton("设置",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                dateTimeTextEdite.setText(dateTime);
                            }
                        }).setNegativeButton("取消",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
//                                    dateTimeTextEdite.setText("");
                            }
                        }).show();

                onDateChanged(null, 0, 0, 0);
                return ad;
        }
    }


    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        onDateChanged(null, 0, 0, 0);
    }

    @SuppressLint("SimpleDateFormat")
    public void onDateChanged(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(datePicker.getYear(), datePicker.getMonth(),
                datePicker.getDayOfMonth(), timePicker.getCurrentHour(),
                timePicker.getCurrentMinute());
        SimpleDateFormat sdf = new SimpleDateFormat("MM月-dd日");
        dateTime = sdf.format(calendar.getTime());
        ad.setTitle(dateTime);
    }

    private void resizePikcer(FrameLayout tp, boolean isTime) {
        List<NumberPicker> npList = findNumberPicker(tp, isTime);
//		for(NumberPicker np:npList){
//			resizeNumberPicker(np);
//		}
        if (isTime && npList.size() > 1) {
            npList.get(1).setVisibility(View.GONE);
        }
//		resizeNumberPicker(npList.get(0));
    }

    private List<NumberPicker> findNumberPicker(ViewGroup viewGroup, boolean isTime) {
        List<NumberPicker> npList = new ArrayList<NumberPicker>();
        View child = null;
        if (null != viewGroup) {
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                child = viewGroup.getChildAt(i);
                if (isTime && npList.size() == 1) {
                    child.setVisibility(View.GONE);
                }
                if (child instanceof NumberPicker) {
                    npList.add((NumberPicker) child);
                } else {
                    if (child instanceof LinearLayout) {
                        List<NumberPicker> result = findNumberPicker((ViewGroup) child, isTime);
                        if (result.size() > 0) {
                            return result;
                        }
                    }
                }
            }

        }
        return npList;
    }
}


