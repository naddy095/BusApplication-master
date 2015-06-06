package com.example.busapp;
 
import java.util.Timer;
import java.util.TimerTask;
 
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Window;
 
public class SplashScreenActivity extends Activity {
 
	// Set Duration of the Splash Screen
	long Delay = 3000;
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Remove the Title Bar
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		// Get the view from splash_screen.xml
		setContentView(R.layout.splash_screen);
 
		// Create a Timer
		Timer RunSplash = new Timer();
 
		// Task to do when the timer ends
		//TimerTask ShowSplash = new TimerTask() {
			Thread th = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
                                            /*
                                             * We are creating this new thread because we don’t
                                             * want our main thread to stop working for that
                                             * time as our android stop working and some time
                                             * application will crashes
                                             */
					e.printStackTrace();
				}
				finally {
					Intent i = new Intent(SplashScreenActivity.this,
							MainActivity.class);
					startActivity(i);
					finish();
				}

			}
		});
 
		// Start the timer
		//RunSplash.schedule(ShowSplash, Delay);
		th.start(); // start the thread
	}
}