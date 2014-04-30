package com.soundboardinc.weddingcrasherssoundboard;



import java.util.ArrayList;
import java.util.List;

import com.soundboardinc.weddingcrasherssoundboard.fragment.IntroFragment;
import com.soundboardinc.weddingcrasherssoundboard.fragment.SelectCharacterFragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

public class Pageview_layout extends FragmentActivity{
	
	
	private ViewPager mPager;
	private PagerAdapter mPagerAdapter;
	private ToggleButton toggleButton1;
	private Session appsession;
	private MediaPlayer player;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.viewpager_layout);
		appsession = new Session(this);
		
		mPager = (ViewPager) findViewById(R.id.pager);
		List<Fragment> ls = new ArrayList<Fragment>();
		ls.add(new IntroFragment());
		ls.add(new SelectCharacterFragment());
		mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), ls);
        mPager.setAdapter(mPagerAdapter);
        
        /*
         * mute toggle button handling to mute/play background sound
         * */
        toggleButton1 = (ToggleButton) findViewById(R.id.toggleButton1);
        toggleButton1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				/*
				 * check mute button click value and store at sharedpreferences, it will store app data
				 * */
				appsession.Set_key(appsession.App_mute, String.valueOf(isChecked));
				if(isChecked){
					if(player != null){
						player.stop();
						player.release();
						player = null;
					}
				}else{
					player = MediaPlayer.create(Pageview_layout.this, R.raw.faith);
					player.seekTo(0);
					player.setLooping(true);
					player.start();
				}
				System.out.println(isChecked);
			}
		});
	}

	/*
	 * when this screen disappear from mobile, then it's will stop background sound
	 * */
	@Override
	protected void onPause() {
		super.onPause();
		if(player != null){
			player.stop();
			player.release();
			player = null;
		}
	}

	/*
	 * when this screen appear at mobile screen, then it's will start playing background sound accounting store value at sharedpreferences
	 * */
	@Override
	protected void onResume() {
		super.onResume();
		
		if(appsession.Get_key(appsession.App_mute) == null || !Boolean.parseBoolean(appsession.Get_key(appsession.App_mute))){
			player = MediaPlayer.create(this, R.raw.faith);
			player.seekTo(0);
			player.setLooping(true);
			player.start();
		}else{
			toggleButton1.setChecked(Boolean.parseBoolean(appsession.Get_key(appsession.App_mute)));
		}
	}
	/*
	 * this adapter contain fragment, so it's easily swipe screen by slide left-right or right-left
	 * */

	private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
		private List<Fragment> frag;
        public ScreenSlidePagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.frag = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return frag.get(position);
        }

        @Override
        public int getCount() {
            return frag.size();
        }
    }

}
