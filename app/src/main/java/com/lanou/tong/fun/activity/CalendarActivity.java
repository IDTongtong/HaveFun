package com.lanou.tong.fun.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lanou.tong.fun.R;
import com.lanou.tong.fun.tool.calendarviewtools.mcalendarview.MarkStyle;
import com.lanou.tong.fun.tool.calendarviewtools.mcalendarview.listeners.OnDateClickListener;
import com.lanou.tong.fun.tool.calendarviewtools.mcalendarview.listeners.OnMonthChangeListener;
import com.lanou.tong.fun.tool.calendarviewtools.mcalendarview.mCalendarView;
import com.lanou.tong.fun.tool.calendarviewtools.mcalendarview.vo.DateData;


public class CalendarActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
//      Get instance.
        final mCalendarView calendarView = ((mCalendarView) findViewById(R.id.calendar));

//      Set up listeners.
        calendarView.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onDateClick(View view, DateData date) {
                Toast.makeText(CalendarActivity.this, String.format("%d-%d", date.getMonth(), date.getDay()), Toast.LENGTH_SHORT).show();
            }
        }).setOnMonthChangeListener(new OnMonthChangeListener() {
            @Override
            public void onMonthChange(int year, int month) {
                ((TextView) findViewById(R.id.ind)).setText(String.format("%d-%d", year, month));
//                Toast.makeText(MainActivity.this, String.format("%d-%d", year, month), Toast.LENGTH_SHORT).show();
//                calendarView.markDate(year, month, 5);
//                MarkedDates.getInstance().notifyObservers();
            }
        }).setMarkedStyle(MarkStyle.RIGHTSIDEBAR)
                .markDate(2016, 2, 1).markDate(2016, 3, 25)
                .markDate(2016, 2, 4)
                .markDate(new DateData(2016, 3, 1).setMarkStyle(new MarkStyle(MarkStyle.DOT, Color.GREEN)))
                        .hasTitle(false);


        imageView = (ImageView) findViewById(R.id.calendar_iv);
        final Intent intent = new Intent(this, ExpCalendarActivity.class);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intent);

            }
        });


//************************************************************************************************************
//        Use default view.
//        If you want to use customized cells, un-comment below line and modify `DateCellView`, `MarkCellView`.
//************************************************************************************************************

//        calendarView.setDateCell(R.layout.layout_date_cell).setMarkedCell(R.layout.layout_mark_cell);

    }

//**********************************************************
//  Generated codes, didn't modified, so you can ignore them.
//**********************************************************
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_calendar_fragment, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            startActivity(new Intent(this, ExpCalendarActivity.class));
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
