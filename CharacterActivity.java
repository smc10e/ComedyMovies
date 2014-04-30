package com.soundboardinc.weddingcrasherssoundboard;


import com.soundboardinc.weddingcrasherssoundboard.model.Celebrity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class CharacterActivity extends Activity
{
	  private Celebrity data;
	  private ListView tonelist;
	  private RelativeLayout stoplayout;
	  private Button stop;
	  private MediaPlayer player = null;

	  protected void onCreate(Bundle paramBundle)
	  {
	    super.onCreate(paramBundle);
	    setContentView(R.layout.activity_character);
	    data = getIntent().getParcelableExtra("data");
	    /*
	     * set back ground of screen according Celebrity selected at Select Character fragment
	     * */
	    findViewById(R.id.character_background).setBackgroundResource(this.data.background);
	    stoplayout = (RelativeLayout) findViewById(R.id.stoplayout1);
	    stop = (Button) findViewById(R.id.stop);
	    /*
	     * stopping quotes at media player
	     * */
	    stop.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				player.stop();
				player.release();
				player = null;
				/*
				 * after stopping media play stop layout become hide
				 * */
				stoplayout.setVisibility(View.GONE);
			}
		});
	    /*
	     * list of quotes for Celebrity
	     * */
	    tonelist = ((ListView)findViewById(R.id.tonelist));
	    tonelist.setAdapter(new ToneRowAdapter(this, R.layout.tones, data.mp3.toArray(new String[data.mp3.size()])));
	    /*
	     * on click on any quotes item from SlidingDrawer
	     * it's will play that quotes at media play by looping
	     * */
	    tonelist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				String d = data.mp3.get(arg2);
				System.out.println(R.raw.themeatloaf);
				stoplayout.setVisibility(View.VISIBLE);
				/*
				 * get raw mp3 file for raw folder by it's file name  using getResources().getIdentifier method
				 * */
				int mp = CharacterActivity.this.getResources().getIdentifier(d, "raw", CharacterActivity.this.getPackageName());
				if(player != null){
					player.stop();
					player.release();
					player = null;
				}
				player = MediaPlayer.create(CharacterActivity.this, mp);
				player.seekTo(0);
				player.setLooping(true);
				player.start();
			}
		});
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
	  /*
	     * it's customize adapter for listview
	     * 
	     * that show left side star image, right side mp3 file icon and next to star image file name string will show
	     * */
	private class ToneRowAdapter extends ArrayAdapter<String>
	  {
	    private int layoutResourceId;
	    private Context mContext;
	    private String[] objects;
	    private Typeface typeFace = Typeface.createFromAsset(CharacterActivity.this.getAssets(), "fonts/Futura.ttf");

	    public ToneRowAdapter(Context context, int resource, String[] objects)
	    {
	      super(context, resource, objects);
	      this.mContext = context;
	      this.layoutResourceId = resource;
	      this.objects = objects;
	    }
	    /*
	     * it's return row for listview
	     * */
	    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
	    {
	      View localView = ((LayoutInflater)mContext.getSystemService("layout_inflater")).inflate(this.layoutResourceId, paramViewGroup, false);
	      TextView localTextView = (TextView)localView.findViewById(R.id.tone_name);
	      localTextView.setTypeface(this.typeFace);
	      localTextView.setText(this.objects[paramInt]);
	      return localView;
	    }
	  }
	}