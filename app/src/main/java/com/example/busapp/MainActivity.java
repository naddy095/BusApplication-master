package com.example.busapp;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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


public class MainActivity extends ActionBarActivity implements View.OnClickListener {
	LinearLayout datePicker;
    LinearLayout shortJourney;
    LinearLayout longJourney;
	final Context context=this;
    LinearLayout cityFromSelection;
    //pawan
    //pawan
    String journeyType = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		android.support.v7.app.ActionBar ab =getSupportActionBar();
		ab.setDisplayShowHomeEnabled(true);
		ab.setIcon(R.drawable.ic_launcher);
		setContentView(R.layout.activity_main);
        cityFromSelection = (LinearLayout)findViewById(R.id.pickAreaLL);
        cityFromSelection.setOnClickListener(this);
		datePicker = (LinearLayout)findViewById(R.id.datePickerLayout);

        //journey tabs
        shortJourney = (LinearLayout)findViewById(R.id.shortJourneyLL);
        longJourney = (LinearLayout)findViewById(R.id.longJourneyLL);
        shortJourney.setOnClickListener(this);
        longJourney.setOnClickListener(this);
        Intent intent = getIntent();
        String selectedArea = intent.getStringExtra("area");
        if(selectedArea !=null && !selectedArea.isEmpty()){
            ((TextView)findViewById(R.id.areaText)).setText(selectedArea);
        }
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
            case R.id.pickAreaLL :
                Intent intent = new Intent(context,PickArea.class);
                startActivity(intent);
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

   /* @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch(view.getId())
        {
            case R.id.fromLayout :
                Toast.makeText(context,"From Clicked",Toast.LENGTH_SHORT).show();
                break;
            case R.id.toLayout :
                Toast.makeText(context,"To Clicked",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }*/
}
