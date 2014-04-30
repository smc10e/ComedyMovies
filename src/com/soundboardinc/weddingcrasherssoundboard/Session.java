package com.soundboardinc.weddingcrasherssoundboard;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


/**
 * @author claudius.christian
 * 
 * Session :- store data regarding app those are used in app
 *
 */
public class Session {

	private SharedPreferences sharedPref;
	private Editor editor;
	private String SHARED = "App_Preferences";
	public String App_mute = "mute";

	public Session(Context context) {
		sharedPref = context.getSharedPreferences(SHARED, Context.MODE_PRIVATE);
	}
	
	
	public String Get_key(String key){
		return sharedPref.getString(key, null);
	}
	
	public void Set_key(String key, String value){
		editor = sharedPref.edit();
		editor.putString(key, value);
		editor.commit();
	}
}
