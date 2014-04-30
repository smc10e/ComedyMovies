package com.soundboardinc.weddingcrasherssoundboard.fragment;

import java.util.ArrayList;

import com.soundboardinc.weddingcrasherssoundboard.CharacterActivity;
import com.soundboardinc.weddingcrasherssoundboard.R;
import com.soundboardinc.weddingcrasherssoundboard.model.Celebrity;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
/*
 * Select Character fragment
 * 
 *  this fragment will use within ViewPager
 * 
 * it's have listview that contain Celebrity list 
 * */
public class HSelectFrag extends Fragment{

	private ArrayList<Celebrity> data = new ArrayList<Celebrity>();
	private ListView list_character;
	/*
	 * Default constructor 
	 * */
    public HSelectFrag() {
	}
    
    /*
	 * here we adding data into array so that listview show records
	 * */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		data.add(new Celebrity("Stu", R.drawable.ed_helms, R.drawable.stu, 
				new String[] { 
				"facedowninaditch", 
				"grossedoutbysemen", 
				"itwasroofies", 
				"nerdyhillbilly", 
				"stusings", 
				"toostupidtoinsult", 
				"whatthefuckisgoingon" 
		}));
	    data.add(new Celebrity("Alan", R.drawable.zach_galifianakis, R.drawable.alan, 
	    		new String[] { 
	    		"caesarspalace", 
	    		"countingcards", 
	    		"holocaustring", 
	    		"nicelady", 
	    		"notatthetablecarlos", 
	    		"retard", 
	    		"threebestfriends",
	    		"shouldntbehere" 
	    }));
	    
	    data.add(new Celebrity("Phil", R.drawable.bradley_cooper, R.drawable.phil, 
	    		new String[] {  
	    		"manpurse", 
	    		"nicerack", 
	    		"puttingonsomepants", 
	    		"imasickman", 
	    		"theresatigerinthere" 
	    }));
	    
	    data.add(new Celebrity("Mr. Chow", R.drawable.ken_jeong, R.drawable.mr_chow, 
	    		new String[] { 
	    		"becausehesfat", 
	    		"suckonmychinesenuts", 
	    		"toodaloo", 
	    		"luckycharm",  
	    }));
	    
	    data.add(new Celebrity("Officer Franklin", R.drawable.rob_riggle, R.drawable.officer_franklin, 
	    		new String[] { 
	    		"fatjesus", 
	    		"intheface", 
	    		"rightinthenuts", 
	    		"notupinhere",  
	    }));
	    
	    data.add(new Celebrity("Extras", R.drawable.mike_tyson, R.drawable.randoms, 
	    		new String[] { 
	    		"mikesings", 
	    		"getamap",  
	    }));
	    
	    
	}
	/*
     * it's return view/layout for info screen
     * */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout containing a title and body text.
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.hselectcharacter, container, false);
        list_character = (ListView)rootView.findViewById(R.id.list_character);
	    list_character.setAdapter(new CelebrityRowAdapter(getActivity(), R.layout.celebrity, data.toArray(new Celebrity[this.data.size()])));
	    list_character.setOnItemClickListener(new OnItemClickListener()
	    {
	      public void onItemClick(AdapterView<?> arg0, final View views, final int arg2, final long arg3)
	      {
	        startActivity(new Intent(getActivity(), CharacterActivity.class).putExtra("data", data.get(arg2)));
	      }
	    });
		return rootView;
	}

	/*
     * it's customize adapter for listview
     * 
     * that show left side Celebrity image and next his name string will show
     * */
	private class CelebrityRowAdapter extends ArrayAdapter<Celebrity>
	  {
	    private int layoutResourceId;
	    private Context mContext;
	    private Celebrity[] objects;
	    private Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Futura.ttf");

	    public CelebrityRowAdapter(Context context, int resource, Celebrity[] objects)
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
	      View localView = ((LayoutInflater)this.mContext.getSystemService("layout_inflater")).inflate(this.layoutResourceId, paramViewGroup, false);
	      TextView localTextView = (TextView)localView.findViewById(R.id.celebrity_name);
	      localTextView.setTypeface(this.typeFace);
	      localTextView.setText(this.objects[paramInt].name);
	      ((ImageView)localView.findViewById(R.id.celebrity_img)).setImageResource(this.objects[paramInt].drawable);
	      return localView;
	    }
	  }
}
