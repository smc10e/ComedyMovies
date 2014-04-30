package com.soundboardinc.weddingcrasherssoundboard.fragment;

import com.soundboardinc.weddingcrasherssoundboard.R;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
/*
 * Intro-screen fragment
 * 
 *  this fragment will use within ViewPager
 * 
 * */
public class IntroFragment extends Fragment{

	private Button watch_trailer;
	/*
	 * Default constructor 
	 * */
    public IntroFragment() {
		// TODO Auto-generated constructor stub
	}
    
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	/*
     * it's return view/layout for info screen
     * */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.activity_intro, container, false);
        watch_trailer = (Button) rootView.findViewById(R.id.watch_trailer);
        watch_trailer.setOnClickListener(listener);
		return rootView;
	}

	/*
	 * click event on buttons
	 * */
	private View.OnClickListener listener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			if (v == watch_trailer)
			      startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.youtube.com/watch?v=ZeUSo8voIXM")));
		}
	};

}
