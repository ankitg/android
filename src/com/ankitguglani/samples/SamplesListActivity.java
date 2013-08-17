package com.ankitguglani.samples;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class SamplesListActivity extends ListActivity {

	private static final String TAG = "com.ankitguglani.samples.SamplesListActivity";
	private static final Class<SamplesListActivity> SELF = com.ankitguglani.samples.SamplesListActivity.class;
	
	int samplesCount = 0;
	
	List<AppListItem> AppList = new ArrayList<AppListItem>();	
	
//	String display[] = {"Splash Screen","Notes App","Photo App"};
//	String classes[] = {"com.ankitguglani.samples.SplashActivity",
//						"com.ankitguglani.samples.notes.NotesListActivity",
//						"com.ankitguglani.samples.camera.PictureActivity"};
	
	AppListItem splash = new AppListItem(++samplesCount,"Splash Screen","com.ankitguglani.samples.SplashActivity", R.drawable.splash_icon, true);
	AppListItem notes = new AppListItem(++samplesCount,"Notes App","com.ankitguglani.samples.notes.NotesListActivity", R.drawable.notes, true);
	AppListItem photos = new AppListItem(++samplesCount,"Photo App","com.ankitguglani.samples.camera.PictureActivity", R.drawable.camera, true);
	AppListItem notification = new AppListItem(++samplesCount, "Notification.", "com.ankitguglani.samples.notification.NotificationBarActivity", R.drawable.notification_icon, true);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setListAdapter(new ArrayAdapter<String>(SamplesListActivity.this, android.R.layout.simple_list_item_1, display));
		
		setContentView(R.layout.application_list);
		AppList.add(splash);
		AppList.add(notes);
		AppList.add(photos);
		AppList.add(notification);
		
		// Check for additionally installed applications.
		final PackageManager pm = getPackageManager();
		List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

		for (ApplicationInfo packageInfo : packages) {
			if(packageInfo.packageName.contains("com.codewithchris")) // Code with Chris
			{
				AppList.add(new AppListItem(++samplesCount, "Code with Chris", "com.ankitguglani.samples.codewithchris", R.drawable.codewithchris, false));
			}
			if(packageInfo.packageName.contains("com.ankitguglani.samples.nfc")) // NFC Text read Sample
			{
				AppList.add(new AppListItem(++samplesCount, "NFC Sample", "com.ankitguglani.samples.nfc", R.drawable.nfc, false));
			}
		}
		
		setListAdapter(new ApplicationListAdapter());
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		try 
		{
			Intent sampleAppIntent = new Intent();
			sampleAppIntent.setAction(AppList.get(position).getActionName().toString());
			startActivity(sampleAppIntent);
		} 
		catch (Exception e) 
		{
			Log.e(TAG, "Class not found exception: " + e);
		}
	}
	
	public class ApplicationListAdapter extends ArrayAdapter<AppListItem> {

		ApplicationListAdapter()
		{
			super(SamplesListActivity.this, R.layout.application_list_row, R.id.appTitle, AppList);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = getLayoutInflater();
			View row = inflater.inflate(R.layout.application_list_row, parent, false);
			
			TextView label = (TextView)row.findViewById(R.id.appTitle);
			label.setText(AppList.get(position).getDisplayName());

			ImageView icon = (ImageView)row.findViewById(R.id.appIconImageView);
			icon.setImageResource(AppList.get(position).getImageResourceId());
			
			return row;
		}
		
	}
}
