package com.ieee.imdbupdates;


import java.util.Date;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

public class CheckService extends Service{

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		
		NotificationCompat.Builder nb = new NotificationCompat.Builder(getApplicationContext());
		Date dt = new Date();

		int hours = dt.getHours();
		int minutes = dt.getMinutes();
		int seconds = dt.getSeconds();
		String str = hours + ":" + minutes + ":" + seconds;
		System.out.print(str);
		TestAdapter tat = new TestAdapter(getApplicationContext());
		tat.createDatabase();
		tat.open();
		Cursor mcurr = tat.getTestData();
		if (mcurr != null)
			do {
				String data = mcurr.getString(4);
				if (str.equals(data))
					;
				{
					nb.setContentTitle("New Episode Released");

					nb.setContentText(mcurr.getString(1));
					nb.setSmallIcon(R.drawable.ic_launcher);
					nb.setAutoCancel(true);
					NotificationManager nm = (NotificationManager) getApplicationContext()
							.getSystemService(Context.NOTIFICATION_SERVICE);
					final Intent notificationIntent = new Intent(getApplicationContext(),
							TitleScreen.class);
					notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
							| Intent.FLAG_ACTIVITY_NEW_TASK);
					final PendingIntent contentIntent = PendingIntent
							.getActivity(getApplicationContext(), 0, notificationIntent, 0);
					nb.setContentIntent(contentIntent);
					Notification notification = nb.getNotification();
					nm.notify(0, notification);

				}

			} while (mcurr.moveToNext());
		tat.close();

		return super.onStartCommand(intent, flags, startId);

	}
	
	
	

}
