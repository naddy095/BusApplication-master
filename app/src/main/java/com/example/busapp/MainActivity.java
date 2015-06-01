package com.example.busapp;
import android.app.ActionBar;
import android.content.Context;
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

public class MainActivity extends ActionBarActivity implements View.OnClickListener {
	LinearLayout calendarPickerLayout;
    LinearLayout shortJourney;
    LinearLayout longJourney;
    TextView dateTV;
    TextView day;
    TextView month;
	final Context context=this;
    LinearLayout cityFromSelection;
    String journeyType = "";
    String area ="";
    String date = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		android.support.v7.app.ActionBar ab =getSupportActionBar();
		ab.setDisplayShowHomeEnabled(true);
		ab.setIcon(R.drawable.ic_launcher);
        setContentView(R.layout.activity_main);
        //ui attach
        dateTV=(TextView)findViewById(R.id.tvDate);
        day=(TextView)findViewById(R.id.tvDay);
        month=(TextView)findViewById(R.id.tvMonth);
        cityFromSelection = (LinearLayout)findViewById(R.id.pickAreaLL);
        calendarPickerLayout = (LinearLayout)findViewById(R.id.calendarPickerLayout);
        cityFromSelection.setOnClickListener(this);
        calendarPickerLayout.setOnClickListener(this);
        shortJourney = (LinearLayout)findViewById(R.id.shortJourneyLL);
        longJourney = (LinearLayout)findViewById(R.id.longJourneyLL);
        shortJourney.setOnClickListener(this);
        longJourney.setOnClickListener(this);

        // Init
        InitializeData();

	}

    private void InitializeData() {
        Date firstPageDate= new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(firstPageDate);
        setDateInUI(currentDate);
    }

    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
		//		MenuInflater inflater = getMenuInflater();
		//        inflater.inflate(R.menu.activity_main_actions, menu);
		//
		//        return super.onCreateOptionsMenu(menu);
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
        switch(view.getId())
        {
            case R.id.calendarPickerLayout :
                Intent in = new Intent(context,CalendarView.class);
                startActivityForResult(in, 2);
                break;
            case R.id.pickAreaLL :
                Intent intent = new Intent(context,PickArea.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.shortJourneyLL :
                shortTv = (TextView)findViewById(R.id.shortJourneyTV);
                longTv = (TextView)findViewById(R.id.longJourneyTV);
                LL = (LinearLayout)findViewById(R.id.longJourneyLL);
                LL.setBackgroundColor(Color.WHITE);
                longTv.setTextColor(Color.GRAY);
                shortTv.setTextColor(Color.WHITE);
                view.setBackgroundColor(Color.GRAY);
                Toast.makeText(context,"shortJourneyLL",Toast.LENGTH_SHORT).show();
                journeyType = "shortjourney";
                break;
            case R.id.longJourneyLL :
                shortTv = (TextView)findViewById(R.id.shortJourneyTV);
                longTv = (TextView)findViewById(R.id.longJourneyTV);
                LL = (LinearLayout)findViewById(R.id.shortJourneyLL);
                view.setBackgroundColor(Color.GRAY);
                shortTv.setTextColor(Color.GRAY);
                longTv.setTextColor(Color.WHITE);
                LL.setBackgroundColor(Color.WHITE);
                journeyType = "longjourney";
                Toast.makeText(context,"longJourneyLL",Toast.LENGTH_SHORT).show();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 1){
            String area = data.getStringExtra("area");
            ((TextView)findViewById(R.id.areaText)).setText(area);
        }else if(resultCode == 2){
            String date = data.getStringExtra("C_Date");
            setDateInUI(date);
        }
    }
}
