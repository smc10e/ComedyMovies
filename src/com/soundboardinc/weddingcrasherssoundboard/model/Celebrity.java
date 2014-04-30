package com.soundboardinc.weddingcrasherssoundboard.model;

import java.util.ArrayList;
import java.util.Arrays;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/*
 * Celebrity class 
 * this contain variable and inform, those will passing between fragment/activities
 * data transformation will be done by Parcelable objects
 * 
 * doc:- http://developer.android.com/reference/android/os/Parcelable.html
 * */
public class Celebrity implements Parcelable {

	private String TAG = getClass().getSimpleName();
	public int background;
	public int drawable;
	public ArrayList<String> mp3;
	public String name;

	public Celebrity(Parcel paramParcel) {
		Log.w(this.TAG, "Offer(Parcel in): time to put back parcel data");
		this.name = paramParcel.readString();
		this.drawable = paramParcel.readInt();
		this.background = paramParcel.readInt();
		this.mp3 = new ArrayList<String>();
		paramParcel.readStringList(this.mp3);
	}

	public Celebrity(String name, int drawable, int background, String[] mp3) {
		this.name = name;
		this.drawable = drawable;
		this.background = background;
		this.mp3 = new ArrayList<String>(Arrays.asList(mp3));
	}

	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel dest, int flag) {
		Log.w(this.TAG, "writeToParcel..." + flag);
		dest.writeString(this.name);
		dest.writeInt(this.drawable);
		dest.writeInt(this.background);
		dest.writeStringList(this.mp3);
	}
	
	public static final Parcelable.Creator<Celebrity> CREATOR = new Parcelable.Creator<Celebrity>()
	  {
	    public Celebrity createFromParcel(Parcel paramAnonymousParcel)
	    {
	      return new Celebrity(paramAnonymousParcel);
	    }

	    public Celebrity[] newArray(int paramAnonymousInt)
	    {
	      return new Celebrity[paramAnonymousInt];
	    }
	  };
}
