package com.soundboardinc.weddingcrasherssoundboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
/*
 * splash screen it' will showing for 2 second and than redirected to landingscren
 * 
 * */
public class H_SplashScreen extends Activity
{
	  private final int SPLASH_DISPLAY_LENGHT = 2000;

	  protected void onCreate(Bundle paramBundle)
	  {
	    super.onCreate(paramBundle);
	    setContentView(R.layout.activity_h__splash_screen);
	    new Handler().postDelayed(new Runnable()
	    {
	      public void run()
	      {
	    	  startActivity(new Intent(H_SplashScreen.this, HPageView.class));
	    	  /*
	    	   * it's will deleted this activity history so when click back on landing screen(Pageview_layout), it will go at android home screen
	    	   * */
	    	  finish();
	      }
	    }
	    , SPLASH_DISPLAY_LENGHT);
	  }
	}
