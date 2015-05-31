package com.example.busapp;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.net.MailTo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CalendarView extends ActionBarActivity {

	public GregorianCalendar month, itemmonth;// calendar instances.
	final Context context=this;
	public CalendarAdapter adapter;// adapter instance
	public Handler handler;// for grabbing some event values for showing the dot
	// marker.
	public ArrayList<String> items; // container to store calendar items which
	// needs showing the event marker

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendar);
		Locale.setDefault( Locale.US );
		month = (GregorianCalendar) GregorianCalendar.getInstance();
		itemmonth = (GregorianCalendar) month.clone();

		items = new ArrayList<String>();
		adapter = new CalendarAdapter(this, month);

		GridView gridview = (GridView) findViewById(R.id.gridview);
		gridview.setAdapter(adapter);

		handler = new Handler();
		handler.post(calendarUpdater);

		TextView title = (TextView) findViewById(R.id.title);
		title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));

		Log.i("Date",android.text.format.DateFormat.format("MMMM yyyy", month)+"");

		RelativeLayout previous = (RelativeLayout) findViewById(R.id.previous);

		previous.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setPreviousMonth();
				refreshCalendar();
			}
		});

		RelativeLayout next = (RelativeLayout) findViewById(R.id.next);
		next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setNextMonth();
				refreshCalendar();

			}
		});

		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

				((CalendarAdapter) parent.getAdapter()).setSelected(v);
				String selectedGridDate = CalendarAdapter.dayString
						.get(position);
				String[] separatedTime = selectedGridDate.split("-");
				String gridvalueString = separatedTime[2].replaceFirst("^0*","");// taking last part of date. ie; 2 from 2012-12-02.
				int gridvalue = Integer.parseInt(gridvalueString);
				// navigate to next or previous month on clicking offdays.
				if ((gridvalue > 10) && (position < 8)) {
					setPreviousMonth();
					refreshCalendar();
				} else if ((gridvalue < 7) && (position > 28)) {
					setNextMonth();
					refreshCalendar();
				}
				((CalendarAdapter) parent.getAdapter()).setSelected(v);

				Boolean tmp=	showToast(selectedGridDate);
				
				if (tmp==true) {
					Intent intent =new Intent(context,MainActivity.class);
					intent.putExtra("C_Date",selectedGridDate);
					startActivity(intent);
				}
				
				Log.i("pp", selectedGridDate);
			}
		});
	}

	protected void setNextMonth() {
		if (month.get(GregorianCalendar.MONTH) == month
				.getActualMaximum(GregorianCalendar.MONTH)) {
			month.set((month.get(GregorianCalendar.YEAR) + 1),
					month.getActualMinimum(GregorianCalendar.MONTH), 1);
		} else {
			month.set(GregorianCalendar.MONTH,
					month.get(GregorianCalendar.MONTH) + 1);
		}

	}

	protected void setPreviousMonth() {
		if (month.get(GregorianCalendar.MONTH) == month
				.getActualMinimum(GregorianCalendar.MONTH)) {
			month.set((month.get(GregorianCalendar.YEAR) - 1),
					month.getActualMaximum(GregorianCalendar.MONTH), 1);
		} else {
			month.set(GregorianCalendar.MONTH,
					month.get(GregorianCalendar.MONTH) - 1);
		}

	}

	protected Boolean showToast(String string) {

		Boolean temp =true;
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String todaysDate = sdf.format(date);
		Date parsedTdate;
		Date clickedDate;
		try {
			parsedTdate = sdf.parse(todaysDate);
			clickedDate = sdf.parse(string);
			if(clickedDate.compareTo(parsedTdate)<0){
				Toast.makeText(context, "Entered date is before Todays date", Toast.LENGTH_SHORT).show();
				temp=false;
			}/*else{
				Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
			}*/
		}catch (ParseException e) {
			e.printStackTrace();
		}
		return temp;
	}

	public void refreshCalendar() {
		TextView title = (TextView) findViewById(R.id.title);

		adapter.refreshDays();
		adapter.notifyDataSetChanged();
		handler.post(calendarUpdater); // generate some calendar items

		title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));
		Log.i("Date",android.text.format.DateFormat.format("MMMM yyyy dd", month)+"");
	}

	public Runnable calendarUpdater = new Runnable() {

		@Override
		public void run() {
			items.clear();

			// Print dates of the current week
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd",Locale.US);
			String itemvalue;
			for (int i = 0; i < 7; i++) {
				itemvalue = df.format(itemmonth.getTime());
				itemmonth.add(GregorianCalendar.DATE, 1);
				items.add("2012-09-12");
				items.add("2012-10-07");
				items.add("2012-10-15");
				items.add("2012-10-20");
				items.add("2012-11-30");
				items.add("2012-11-28");
			}

			adapter.setItems(items);
			adapter.notifyDataSetChanged();
		}
	};
}
