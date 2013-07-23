package com.ankitguglani.samples;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.ankitguglani.samples.R;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        Thread timer = new Thread()
        {
        	public void run()
        	{
        		try
        		{
        			sleep(5000);
        		}
        		catch (InterruptedException e)
        		{
        			e.printStackTrace();
        		}
        		finally
        		{
        			Intent samplesListIntent = new Intent("com.ankitguglani.samples.SamplesListActivity");
        			startActivity(samplesListIntent);
        		}
        	}
        };
        
        timer.start();
    }

    
    @Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.splash, menu);
        return true;
    }
    
}
