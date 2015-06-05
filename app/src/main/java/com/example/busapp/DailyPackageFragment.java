package com.example.busapp;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.widget.TimePicker;
import android.widget.TimePicker;


/**
 * A simple {@link Fragment} subclass.
 */
public class DailyPackageFragment extends Fragment implements View.OnClickListener {

    LinearLayout calendarPickerLayout;
    LinearLayout timePickerLayout;
    LinearLayout shortJourney;
    LinearLayout longJourney;
    TextView dateTV;
    TextView day;
    TextView month;
    TextView hourminTV;
    private String currentTime;
    LinearLayout cityFromSelection;
    String journeyType = "";
    String area ="";
    String date = "";
    DateFormat mTimeFormat;
    private int mPickerTheme;
    View view_main;



    public DailyPackageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //return inflater.inflate(R.layout.fragment_main_activity_fragment, container, false);

        android.support.v7.app.ActionBar ab =((ActionBarActivity)getActivity()).getSupportActionBar();
        ab.setDisplayShowHomeEnabled(true);
        ab.setIcon(R.drawable.ic_launcher);
        view_main = inflater.inflate(R.layout.fragment_main_activity_fragment, container, false);
        //ui attach
        dateTV=(TextView)view_main.findViewById(R.id.tvDate);
        day=(TextView)view_main.findViewById(R.id.tvDay);
        month=(TextView)view_main.findViewById(R.id.tvMonth);
        cityFromSelection = (LinearLayout)view_main.findViewById(R.id.pickAreaLL);
        calendarPickerLayout = (LinearLayout)view_main.findViewById(R.id.calendarPickerLayout);
        timePickerLayout = (LinearLayout)view_main.findViewById(R.id.timePickerLayout);
        cityFromSelection.setOnClickListener(this);
        calendarPickerLayout.setOnClickListener(this);
        timePickerLayout.setOnClickListener(this);
        shortJourney = (LinearLayout)view_main.findViewById(R.id.shortJourneyLL);
        longJourney = (LinearLayout)view_main.findViewById(R.id.longJourneyLL);
        shortJourney.setOnClickListener(this);
        longJourney.setOnClickListener(this);

        // Init
        InitializeData();



        return view_main;
    }

    private void InitializeData() {
        Date firstPageDate= new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(firstPageDate);

        mTimeFormat = new SimpleDateFormat("HH:mm");
        mPickerTheme = R.style.AppTheme;
        hourminTV = (TextView) view_main.findViewById(R.id.tvHourMin);
        //String curTime = getCurrentTime();
        currentTime=getCurrentTime();

        setDateInUI(currentDate);
    }
    private String getCurrentTime() {

        Calendar curDate = Calendar.getInstance();
        int setMinute = 0;
        int curMint = curDate.get(Calendar.MINUTE);
        int curHour = curDate.get(Calendar.HOUR);
        if (curMint > 0 && curMint < 15) {
            setMinute = 15;
        } else if (curMint >= 15 && curMint < 30) {
            setMinute = 30;
        } else if (curMint >= 30 && curMint < 45) {
            setMinute = 45;
        } else {
            setMinute = 00;
            curHour = curHour+1;
        }
        return curHour+":"+setMinute;
    }

    private void showPickerDialog(boolean is24HrView) throws ParseException {

        Log.i("DemoActivity", "showPickerDialog Enter");
        String time = currentTime;
        Date dialogDate = mTimeFormat.parse(time);
        int hourOfDay = dialogDate.getHours();
        int minute = dialogDate.getMinutes();
        CustomTimePickerDialog dialog = new CustomTimePickerDialog(getActivity(),mTimePickerListener, hourOfDay, minute, is24HrView);
        Log.i("DemoActivity", "showPickerDialog Exit");
        dialog.show();
    }
    private OnTimeSetListener mTimePickerListener = new OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Log.i("DemoActivity", "OnTimeSetListener Enter");
            String ampm=" AM";
            Log.i("HoursOfDay",hourOfDay+"");

            if(hourOfDay>=12){
                hourOfDay=hourOfDay-12;
                ampm=" PM";
            }
            if ((hourOfDay<10)&&(minute<10)) {
                hourminTV.setText("0" + hourOfDay + ":" + "0" + minute+ampm);
            }
            else if (hourOfDay<10) {
                hourminTV.setText("0"+hourOfDay + ":" + minute+ampm);
            }else if (minute<10) {
                hourminTV.setText(hourOfDay + ":" +"0"+ minute+ampm);
            }else{
                hourminTV.setText(hourOfDay + ":" + minute+ampm);
            }
            Log.i("DemoActivity", "OnTimeSetListener Exit"+hourminTV);
        }
    };
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.main, menu);
//        return true;
//        //		MenuInflater inflater = getMenuInflater();
//        //        inflater.inflate(R.menu.activity_main_actions, menu);
//        //
//        //        return super.onCreateOptionsMenu(menu);
//    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        TextView shortTv;
        TextView longTv;
        LinearLayout LL;
        Intent intent;
        switch(view.getId())
        {
            case R.id.calendarPickerLayout :
                intent = new Intent(getActivity(),CalendarView.class);
                startActivityForResult(intent, 2);
                break;
            case R.id.pickAreaLL :
                intent = new Intent(getActivity(),PickArea.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.timePickerLayout :
                try {
                    showPickerDialog(false);
                    Log.i("TimePicker","tt");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.shortJourneyLL :
                shortTv = (TextView)view_main.findViewById(R.id.shortJourneyTV);
                longTv = (TextView)view_main.findViewById(R.id.longJourneyTV);
                LL = (LinearLayout)view_main.findViewById(R.id.longJourneyLL);
                LL.setBackgroundColor(Color.WHITE);
                longTv.setTextColor(Color.GRAY);
                shortTv.setTextColor(Color.WHITE);
                view.setBackgroundColor(Color.GRAY);
                Toast.makeText(getActivity(),"shortJourneyLL",Toast.LENGTH_SHORT).show();
                journeyType = "shortjourney";
                break;
            case R.id.longJourneyLL :
                shortTv = (TextView)view_main.findViewById(R.id.shortJourneyTV);
                longTv = (TextView)view_main.findViewById(R.id.longJourneyTV);
                LL = (LinearLayout)view_main.findViewById(R.id.shortJourneyLL);
                view.setBackgroundColor(Color.GRAY);
                shortTv.setTextColor(Color.GRAY);
                longTv.setTextColor(Color.WHITE);
                LL.setBackgroundColor(Color.WHITE);
                journeyType = "longjourney";
                Toast.makeText(getActivity(),"longJourneyLL",Toast.LENGTH_SHORT).show();
                break;

        }
    }

    public void setDateInUI(String dateInUI) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dayFormatter = new SimpleDateFormat("EEEE");
        SimpleDateFormat monthFormatter = new SimpleDateFormat("MMM");
        try
        {
            String dayToDisplay = dayFormatter.format(formatter.parse(dateInUI));
            String monthToDisplay = monthFormatter.format(formatter.parse(dateInUI));
            Log.d("dateL",dayToDisplay + "month" +monthFormatter.format(formatter.parse(dateInUI)));
            day.setText(dayToDisplay);
            month.setText(monthToDisplay);
            dateTV.setText(dateInUI.split("-")[2]);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 1){
            String area = data.getStringExtra("area");
            ((TextView)view_main.findViewById(R.id.areaText)).setText(area);
        }else if(resultCode == 2){
            String date = data.getStringExtra("C_Date");
            setDateInUI(date);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
