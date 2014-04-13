package com.ieee.imdbupdates;

import java.net.URI;
import java.text.DateFormat;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;

@SuppressLint("NewApi")
public class GCMIntentService extends GCMBaseIntentService{

	private static final int NotifyId = 15081947;
	Intent intent;
	SharedPreferences prefs;
	Boolean isInternetPresent = false;
    
    // Connection detector class
ConnectionDetector cd;
	
	@Override
	protected void onError(Context arg0, String error) 
	{
		Log.e("Error Received",error);
	}

	@Override
	protected void onMessage(Context arg0, Intent i) 
	{
		String tvid = i.getStringExtra("tvid");
		Log.i("Message Received", tvid);
		String rtitle = i.getStringExtra("title");
		Log.i("Message Received", rtitle);
		String status = i.getStringExtra("status");
		Log.i("Message Received", status);
		String rely = i.getStringExtra("rely");
		Log.i("Message Received", rely);
		String epname = i.getStringExtra("epname");
		Log.i("Message Received", epname);
		String epinfo = i.getStringExtra("epinfo");
		Log.i("Message Received", epinfo);
		String epdate = i.getStringExtra("epdate");
		Log.i("Message Received", epdate);
		Databasehelper dbh= new Databasehelper(this);
		dbh.update(tvid, rtitle, status, rely, epname, epinfo, epdate);

		NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		intent = new Intent(this ,ShowSeries.class);
		PendingIntent pi =  PendingIntent.getActivity(getApplicationContext(), 0, intent , 0);
		String body = tvid;
		String title = "Message Received";
		Notification n = new Notification(R.drawable.ic_launcher,body,System.currentTimeMillis());
		n.setLatestEventInfo(getApplicationContext(), title, body, pi);
		n.defaults = Notification.DEFAULT_ALL;
		n.flags = Notification.FLAG_AUTO_CANCEL;
		nm.notify(NotifyId, n);
		
	}

	@Override
	protected void onRegistered(Context arg0, String RegID) 
	{
		Log.i("RegID Received",RegID);
		System.out.println("onregister");

		NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		intent = new Intent(this ,ShowSeries.class);
		PendingIntent pi =  PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
		String body = RegID;
		String title = "Registration ID Received";
		Notification n = new Notification(R.drawable.ic_launcher,body,System.currentTimeMillis());
		n.setLatestEventInfo(getApplicationContext(), title, body, pi);
		n.defaults = Notification.DEFAULT_ALL;
		n.flags = Notification.FLAG_AUTO_CANCEL;
		nm.notify(NotifyId, n);
		
		JSONObject obj = new JSONObject();
		cd = new ConnectionDetector(getApplicationContext());
		System.out.println("json created");
		isInternetPresent = cd.isConnectingToInternet();
		System.out.println("connection");
	/*	if (isInternetPresent) {
             // Internet Connection is Present
             // make HTTP requests
     */      
        
		try 
		{
			obj.put("regid", RegID);
			URI website = new URI("http://tvupdt.appspot.com/register");
			HttpClient client = new DefaultHttpClient();
			HttpPost postreq = new HttpPost(website);
			
			StringEntity se = new StringEntity(obj.toString());
			
			postreq.setEntity(se);
			HttpResponse resp = client.execute(postreq);
			String response = EntityUtils.toString(resp.getEntity());
			Log.i("HTTP Response", response);
		} 
		catch (Exception e) 
		{
			System.out.print("catch ");
			e.printStackTrace();
		}
	//	}
		
	}

	@Override
	protected void onUnregistered(Context arg0, String arg1) 
	{
		Log.i("Unregistered","Unregistered");
	}
	
}

