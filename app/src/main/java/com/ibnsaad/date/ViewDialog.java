package com.ibnsaad.date;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import android.view.Gravity;
import android.view.View;
import android.view.Window;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.timessquare.CalendarPickerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ViewDialog {

    private CalendarPickerView datePicker;






    public ViewDialog(Context context) {
        this.context = context;
    }

    private Context context;

    public void showDialog(TextView textView) {


        final Dialog dialog = new Dialog(context,R.style.Theme_Dialog);
       // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.date_select_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));



        Window window = dialog.getWindow();
        window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        window.setGravity(Gravity.CENTER);

        datePicker=  dialog.findViewById(R.id.calendar_view_layout);


         selectDate(textView);



        Button dialogButton = dialog.findViewById(R.id.bt_save);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dialog.dismiss();

            }
        });


        dialog.show();

    }
    private void selectDate(final TextView textView){

        Date date=new Date(System.currentTimeMillis());
        Calendar calender =Calendar.getInstance();

        calender.add(Calendar.YEAR,1);

        datePicker.init(date,calender.getTime())
                .inMode(CalendarPickerView.SelectionMode.RANGE)

                .withSelectedDate(date);


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        textView.setText(formatter.format(datePicker.getSelectedDate()));

        datePicker.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {


               textView.setText(DateFormat.getDateInstance(DateFormat.FULL)
                       .format(date));

                List<Date> list=datePicker.getSelectedDates();


                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

                textView.setText(formatter.format(list.get(0))+
                        " To "+formatter.format(list.get(list.size()-1)));

            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });
    }
}
