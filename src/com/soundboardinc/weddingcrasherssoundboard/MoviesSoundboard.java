package com.soundboardinc.weddingcrasherssoundboard;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MoviesSoundboard extends Activity {

	private Button wedding;
	private Button hangover;
	private MediaPlayer player;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movies_soundboard);
		
		if(player == null){
			player = MediaPlayer.create(this, R.raw.paparazzi);
			player.seekTo(0);
			player.setVolume(0.1f,0.1f);
			player.setLooping(true);
			player.start();
		}
		
		
		wedding= (Button) findViewById(R.id.weddingbutton);
		hangover= (Button) findViewById(R.id.hangoverbutton);
		
		wedding.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MoviesSoundboard.this, SplashScreen.class));
			}
		});
		
		hangover.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MoviesSoundboard.this, H_SplashScreen.class));
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.movies_soundboard, menu);
		return true;
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		if(player != null){
			player.stop();
			player.release();
			player = null;
		}
	}
	
	

}
