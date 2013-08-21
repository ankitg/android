package com.ankitguglani.samples.webserver;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.apache.http.conn.util.InetAddressUtils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import com.ankitguglani.samples.R;

import fi.iki.elonen.HelloServer;

public class HostIPActivity extends Activity {

	private static final String TAG = "com.ankitguglani.samples.webserver.HostIPActivity";
	private static final Class<HostIPActivity> SELF = com.ankitguglani.samples.webserver.HostIPActivity.class;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.host_ip_layout);
		
		TextView hostiptextview = (TextView)findViewById(R.id.hostIPTextView);
		hostiptextview.setText(getLocalIpAddress());
		startWebServer();
	}

	public String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    //if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress() && inetAddress.isSiteLocalAddress() ) {
                    if (!inetAddress.isLoopbackAddress() && InetAddressUtils.isIPv4Address(inetAddress.getHostAddress()) ) {
                        String ipAddr = inetAddress.getHostAddress();
                        return ipAddr;
                    }
                }
            }
        } catch (SocketException e) {
            Log.d(TAG, e.toString());
        }
        return null;
    } 

	public void startWebServer()
	{
        HelloServer webserver = new HelloServer();
        
        try {
        	webserver.start();
		} catch (Exception e) {
			Log.d(TAG, "Webserver start:" + e.toString());
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.host_i, menu);
		return true;
	}

}
