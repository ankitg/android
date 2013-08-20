package com.ankitguglani.samples;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;
import android.app.Application;

public class SamplesApp extends Application {
	
	public static final String APPLICATION_ID = "2Ksoal5MdqO9Kt5dtOCN7b2LhUnNlS1mvETnyDoJ";
	public static final String CLIENT_ID = "3MOHhqPdSIaWCbZZB8Ibu231x1MMM5EktGXgMlpW";
	
	@Override
	public void onCreate() {
		super.onCreate();
		// Add your initialization code here
		Parse.initialize(this, APPLICATION_ID, CLIENT_ID);

		ParseUser.enableAutomaticUser();
		ParseACL defaultACL = new ParseACL();
	    
		// If you would like all objects to be private by default, remove this line.
		defaultACL.setPublicReadAccess(true);
		ParseACL.setDefaultACL(defaultACL, true);		
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
	}
}
