package fi.iki.elonen;

import java.io.IOException;

import android.util.Log;

public class ServerRunner {
	
	static final String TAG = "com.nitinmalik.arduino_rc";
	
    public static void run(Class serverClass) {
        try {
            executeInstance((NanoHTTPD) serverClass.newInstance());
        } catch (Exception e) {
            Log.d(TAG, "Server Runner exception in run(): " +e.toString());
        }
    }

    public static void executeInstance(NanoHTTPD server) {
        try {
            server.start();
        } catch (IOException ioe) {
        	Log.d(TAG, "Couldn't start server:\n" + ioe);
            return;
        }

        Log.d(TAG, "Server started, Hit Enter to stop.\n");

        try {
            System.in.read();
        } catch (Throwable ignored) {
        }

        server.stop();
        Log.d(TAG, "Server stopped.\n");
    }
}
