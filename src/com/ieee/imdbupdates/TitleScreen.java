package com.ieee.imdbupdates;

import java.net.URI;
import java.util.Calendar;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.android.gcm.GCMRegistrar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class TitleScreen extends Activity {
	Databasehelper mDbHelper;
	private int SUBJECT_ID_INCREMENT = 9999, value = 0;
	public static int hour = 0, minute = 0, currentHour = 0, currentMinute = 0,
			currentSecond = 0;
	public static int setSeconds, id_alarm;
	String sender_id = "857835215860";
	private TableLayout tl;
	private CheckBox chk;
	AlarmManager am;
	private Button btn1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.print("n cat");
		setContentView(R.layout.title_screen);
		SharedPreferences myPref = getSharedPreferences("PrefData", MODE_PRIVATE );
		
		tl = (TableLayout) findViewById(R.id.series_table);
		System.out.print("t1 formd");
	//	if (tl == null)
//		{
		System.out.print("add series");
		addSeries();
	//	}
		System.out.print("outsid add series");
		SharedPreferences.Editor myPrefEditor = myPref.edit();   
        myPrefEditor.putInt("key", value);
        myPrefEditor.commit();
        int as=myPref.getInt("key", 0);
        if(as==0)
        
        	remind();
        
		final TitleScreen titlescreen = this;
		btn1 = (Button) findViewById(R.id.bsend);
		btn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// sendObject();
				Intent i = new Intent(titlescreen, ShowSeries.class);

				startActivity(i);

			}

			private void sendObject() {
				// TODO Auto-generated method stub

				try {
					JSONObject obj = new JSONObject();
					obj.put("reg_id",
							GCMRegistrar.getRegistrationId(TitleScreen.this));
					// String a=Arrays.toString(bits);
					JSONArray jsonarr = new JSONArray();
					TestAdapter tdt = new TestAdapter(titlescreen);
					tdt.createDatabase();
					tdt.open();
					Cursor sub = tdt.getTestData();
					if (sub != null)
						do {
							int ticked = Integer.parseInt(sub.getString(3));
							jsonarr.put(ticked);

						} while (sub.moveToNext());

					obj.put("series", jsonarr);

					URI website = new URI("http://tvupdt.appspot.com/tick");
					HttpClient client = new DefaultHttpClient();
					HttpPost postreq = new HttpPost(website);

					StringEntity se = new StringEntity(obj.toString());

					postreq.setEntity(se);
					HttpResponse resp = client.execute(postreq);
					String response = EntityUtils.toString(resp.getEntity());
					Log.i("HTTP Response", response);

				}

				catch (Exception e) {
					e.printStackTrace();
				}

			}

		});

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		String Reg_ID = GCMRegistrar.getRegistrationId(this);
		if (Reg_ID == "") {
			GCMRegistrar.register(this, sender_id);
		}
		System.out.print(Reg_ID);
	}

	private void addSeries() {
		// TODO Auto-generated method stub
		System.out.println("in add series");
		TestAdapter myDbHelper = new TestAdapter(this);
		final Databasehelper db = new Databasehelper(this);
		myDbHelper.createDatabase();
		myDbHelper.open();
		Cursor sub = myDbHelper.getTestData();
		Display display = getWindowManager().getDefaultDisplay();
		final Context con = getApplicationContext();
		if (sub != null)
			do {

				TableRow tr = new TableRow(this);
				tr.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
						LayoutParams.WRAP_CONTENT));
				tr.setGravity(Gravity.CENTER | Gravity.BOTTOM);
				System.out.println("iabt to pass integer");
				final int index = Integer.parseInt(sub.getString(0));

				chk = new CheckBox(this);

				chk.setId(index + SUBJECT_ID_INCREMENT);
				final String str = sub.getString(1);
				chk.setText(sub.getString(1));
				chk.setTextColor(Color.BLACK);
				final TitleScreen mainActivity = this;
				int chckd = Integer.parseInt(sub.getString(3));

				if (chckd == 1) {
					chk.setChecked(true);
				} else {
					chk.setChecked(false);
				}

				chk.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub

						if (isChecked) {

							db.updateData(1, index);

							Toast toast_check = Toast.makeText(con, str
									+ " added to favourites",
									Toast.LENGTH_SHORT);
							toast_check.setGravity(Gravity.CENTER
									| Gravity.CENTER, 0, 0);
							toast_check.show();
						} else {

							db.updateData(0, index);
						}
					}
				});

				tr.addView(chk); // Adding checkbox
				tl.addView(tr);

			} while (sub.moveToNext());
		myDbHelper.close();
		db.close();
	}

	private void If(boolean b) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menu_items, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.star:
			// Single menu item is selected do something
			// Ex: launching new activity/screen or show alert message
			Intent i = new Intent(TitleScreen.this, ShowSeries.class);

			startActivity(i);
			return true;

		case R.id.pref:
			Intent in = new Intent(TitleScreen.this, MenuPref.class);

			startActivity(in);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void remind() {

		am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		final Calendar c = Calendar.getInstance();
		currentHour = c.get(Calendar.HOUR_OF_DAY);
		currentMinute = c.get(Calendar.MINUTE);
		currentSecond = c.get(Calendar.SECOND);
		setSeconds = (24 * 60 + 0) * 60 * 1000;
		int currentSeconds = ((currentHour * 60 + currentMinute) * 60 + currentSecond) * 1000;
		int finalSeconds = setSeconds - currentSeconds;
		Intent intent = new Intent(this, TimeAlarm.class);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,
				intent, PendingIntent.FLAG_ONE_SHOT);
		am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
				+ finalSeconds, pendingIntent);
	}

}
