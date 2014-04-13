package com.ieee.imdbupdates;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class MenuPref extends Activity {
	private TimePicker timePicker1;
	private Button bpick1;
	private TextView tdisplay;

	private int hour = 00;
	private int minute = 00;

	static final int TIME_DIALOG_ID = 999;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		bpick1 = (Button) findViewById(R.id.bset);
		setContentView(R.layout.pref);
		setTimepickerTime();
		addListenerOnButton();
	}

	
	  private void setTimepickerTime() { // TODO Auto-generated method stub
		 tdisplay = (TextView) findViewById(R.id.tvTime);
		 String freak = showPreferences("PrefDemo");
		 tdisplay.setText(freak); }
	 
	private String showPreferences(String key) {
		// TODO Auto-generated method stub
		 SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
	     String savedPref = sharedPreferences.getString(key, "12:00");
	     return(savedPref);
	       
	}


	public void addListenerOnButton() {

		bpick1 = (Button) findViewById(R.id.bset);

		bpick1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				showDialog(TIME_DIALOG_ID);

			}

		});

	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case TIME_DIALOG_ID:
			// set time picker as current time
			return new TimePickerDialog(this, timePickerListener, hour, minute,
					false);

		}
		return null;
	}

	private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int selectedHour,
				int selectedMinute) {
			hour = selectedHour;
			minute = selectedMinute;
			

			// set current time into textview
			 tdisplay.setText(new StringBuilder().append(pad(hour))
			 .append(":").append(pad(minute)));
			 
			 SavePreferences("PrefDemo",tdisplay.getText().toString());

		/*	// set current time into timepicker
			timePicker1.setCurrentHour(hour);
			timePicker1.setCurrentMinute(minute);
*/
		}

		private void SavePreferences(String key, String value) {
			// TODO Auto-generated method stub
			
			SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
	        SharedPreferences.Editor editor = sharedPreferences.edit();
	        System.out.print("preferences saved");
	        editor.putString(key, value);
	        editor.commit();
			
		}
	};

	private static String pad(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);
	}

}
