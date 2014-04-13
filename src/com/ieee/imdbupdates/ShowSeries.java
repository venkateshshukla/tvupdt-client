package com.ieee.imdbupdates;

import android.app.AlarmManager;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class ShowSeries extends ListActivity  {
	Spinner spin;
	TextView tdata;
	AlarmManager am;
	Button benter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		System.out.println("hg ");
		Databasehelper dao = new Databasehelper(this);
		
	//	setContentView(R.layout.date_series);
		setListAdapter(new ListAdapter(this, dao.getTodos()));
//		spin = (Spinner) findViewById(R.id.spinner);
//		tdata = (TextView) findViewById(R.id.textView1);
//		benter = (Button) findViewById(R.id.b1);
//		benter.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//			//	remind();
//
//			}
//
//		});

	//	spin.setOnItemSelectedListener(this);
		am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

		System.out.println("show series");
//		loadSpinnerData();

	}

	

//	private void loadSpinnerData() {
//		// database handler
//		Databasehelper db = new Databasehelper(getApplicationContext());
//		System.out.println("load spinner");
//
//		// Spinner Drop down elements
//		List<String> lables = db.getAllLabels();
//		System.out.println("spinner data");
//
//		// Creating adapter for spinner
//		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
//				android.R.layout.simple_spinner_item, lables);
//
//		// Drop down layout style - list view with radio button
//		dataAdapter
//				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//		// attaching data adapter to spinner
//		spin.setAdapter(dataAdapter);
//	}
//
//	public void onItemSelected(AdapterView<?> parent, View view, int position,
//			long id) {
//		// On selecting a spinner item
//		final Databasehelper dbh = new Databasehelper(this);
//		System.out.println("freak rocks");
//		String label = parent.getItemAtPosition(position).toString();
//
//		Cursor sub = dbh.getTopic(label);
//		// TimeAlarm ta = new TimeAlarm ();
//		// ta.gtSeries();
//		System.out.println(label);
//		String gotseries = sub.getString(1);
//		System.out.println(gotseries);
//
//		String harshit ="The Next Episode of " + sub.getString(1) + " would be " + sub.getString(5) + " ( " + sub.getString(6) +" ) " + "and will be bradcasted on " +sub.getString(4);
//		tdata.setText(harshit);
//		// Showing selected spinner item
//		dbh.close();
//
//	}
//
//	public void remind() {
//		Intent intent = new Intent(this, TimeAlarm.class);
//		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,
//				intent, PendingIntent.FLAG_ONE_SHOT);
//		am.set(AlarmManager.RTC_WAKEUP,
//				System.currentTimeMillis() + (5 * 1000), pendingIntent);
//	}
//
//	public void onNothingSelected(AdapterView<?> arg0) {
//		// TODO Auto-generated method stub
//
//	}

}
