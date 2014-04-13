package com.ieee.imdbupdates;

import java.util.Date;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.sax.StartElementListener;
import android.support.v4.app.NotificationCompat;

public class TimeAlarm extends BroadcastReceiver {

	// NotificationManager nm;

	@Override
	public void onReceive(Context context, Intent intent) {
		Intent inti = new Intent(context,CheckService.class);
		context.startService(inti);
		
		
//		NotificationCompat.Builder nb = new NotificationCompat.Builder(context);
//		Date dt = new Date();
//
//		int hours = dt.getHours();
//		int minutes = dt.getMinutes();
//		int seconds = dt.getSeconds();
//		String str = hours + ":" + minutes + ":" + seconds;
//		System.out.print(str);
//		TestAdapter tat = new TestAdapter(context);
//		tat.createDatabase();
//		tat.open();
//		Cursor mcurr = tat.getTestData();
//		if (mcurr != null)
//			do {
//				String data = mcurr.getString(4);
//				if (str.equals(data))
//					;
//				{
//					nb.setContentTitle("New Episode Released");
//
//					nb.setContentText(mcurr.getString(1));
//					nb.setSmallIcon(R.drawable.ic_launcher);
//					nb.setAutoCancel(true);
//					NotificationManager nm = (NotificationManager) context
//							.getSystemService(Context.NOTIFICATION_SERVICE);
//					final Intent notificationIntent = new Intent(context,
//							TitleScreen.class);
//					notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
//							| Intent.FLAG_ACTIVITY_NEW_TASK);
//					final PendingIntent contentIntent = PendingIntent
//							.getActivity(context, 0, notificationIntent, 0);
//					nb.setContentIntent(contentIntent);
//					Notification notification = nb.getNotification();
//					nm.notify(0, notification);
//
//				}
//
//			} while (mcurr.moveToNext());
//		tat.close();

	}

	void gtSeries() {
		// TODO Auto-generated method stub

	}
}
