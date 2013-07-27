package com.ankitguglani.samples;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class SamplesListActivity extends ListActivity {

	List<AppListItem> AppList = new ArrayList<AppListItem>();	
	
//	String display[] = {"Splash Screen","Notes App","Photo App"};
//	String classes[] = {"com.ankitguglani.samples.SplashActivity",
//						"com.ankitguglani.samples.notes.NotesListActivity",
//						"com.ankitguglani.samples.camera.PictureActivity"};
	
	AppListItem splash = new AppListItem(0,"Splash Screen","com.ankitguglani.samples.SplashActivity", R.drawable.ic_launcher);
	AppListItem notes = new AppListItem(1,"Notes App","com.ankitguglani.samples.notes.NotesListActivity", R.drawable.ic_launcher);
	AppListItem photos = new AppListItem(2,"Photo App","com.ankitguglani.samples.camera.PictureActivity", R.drawable.camera);
	AppListItem notification = new AppListItem(3, "Notification.", "com.ankitguglani.samples.notification.NotificationBarActivity", R.drawable.ic_launcher);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setListAdapter(new ArrayAdapter<String>(SamplesListActivity.this, android.R.layout.simple_list_item_1, display));
		
		setContentView(R.layout.application_list);
		AppList.add(splash);
		AppList.add(notes);
		AppList.add(photos);
		AppList.add(notification);
		setListAdapter(new ApplicationListAdapter());
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		try {
//			Class sampleAppClass = Class.forName(classes[position]);
			Class sampleAppClass = Class.forName(AppList.get(position).getClassName().toString());
			Intent sampleAppIntent = new Intent(SamplesListActivity.this, sampleAppClass);
			startActivity(sampleAppIntent);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
