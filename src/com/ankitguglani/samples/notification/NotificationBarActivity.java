package com.ankitguglani.samples.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.ankitguglani.samples.R;
import com.parse.ParseInstallation;
import com.parse.PushService;

public class NotificationBarActivity extends Activity implements OnClickListener{

	private static final String TAG = "com.ankitguglani.samples.notification.NotificationBarActivity";
	private static final Class<NotificationBarActivity> SELF = com.ankitguglani.samples.notification.NotificationBarActivity.class;
	
	NotificationManager notificationManager;
	static final int uniqueID = 2190795;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notification_layout);
		Button stat = (Button)findViewById(R.id.notificationButton);
		stat.setOnClickListener(this);
		notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		notificationManager.cancel(uniqueID);
		
		//Parse Push Notification
		PushService.setDefaultPushCallback(this, SELF);
		ParseInstallation.getCurrentInstallation().saveInBackground();
	}

	public void onClick(View v) {
		Intent intent = new Intent(this, SELF);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
		String title = "Ankit does Android.";
		String body = "This is a test notification from Ankit.";
		Notification n = new Notification(R.drawable.notification, body, System.currentTimeMillis());
		n.setLatestEventInfo(this, title, body, pendingIntent);
		n.defaults = Notification.DEFAULT_ALL;
		notificationManager.notify(uniqueID, n);
		finish();
	}

}
