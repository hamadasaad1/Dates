package com.ibnsaad.date;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.squareup.timessquare.CalendarPickerView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SelectDateActivity extends AppCompatActivity {

    private CalendarPickerView datePicker;
    AlertDialog builder1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_date);


//        datePicker=  findViewById(R.id.calendar_view);
       // selectDate();
       // showDialog();


    }


    private void selectDate(){

        Date date=new Date(System.currentTimeMillis());
        Calendar calender =Calendar.getInstance();

        calender.add(Calendar.YEAR,1);

        datePicker.init(date,calender.getTime())
                .inMode(CalendarPickerView.SelectionMode.RANGE)

                .withSelectedDate(date);
        List<Date> list=datePicker.getSelectedDates();
        String x=""+list.get(0);
        String y=""+list.get(list.size()-1);
        Toast.makeText(this, x+y,
                Toast.LENGTH_SHORT).show();

        datePicker.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {

                String selectDate= DateFormat.getDateInstance(DateFormat.FULL)
                        .format(date);

                Calendar calendar=Calendar.getInstance();
                calendar.setTime(date);


            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });
    }


    private void showCalendarInDialog(String title, int layoutResId) {
        datePicker = (CalendarPickerView) getLayoutInflater().inflate(layoutResId, null, false);
        builder1 = new android.app.AlertDialog.Builder(this) //
                .setTitle(title)
                .setView(datePicker)
                .setNeutralButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create();
        builder1.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override public void onShow(DialogInterface dialogInterface) {
                Log.d("Show>>", "onShow: fix the dimens!");
                datePicker.fixDialogDimens();
            }
        });
        builder1.show();
    }

    public void showDialog(View view) {

        showCalendarInDialog("Select Your Data",R.layout.date_select_layout);

        final Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);

        final Calendar lastYear = Calendar.getInstance();
        lastYear.add(Calendar.YEAR, -1);

        datePicker.init(lastYear.getTime(), nextYear.getTime()).withSelectedDate(new Date());

    }
}
