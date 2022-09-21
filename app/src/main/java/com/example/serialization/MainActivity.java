package com.example.serialization;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends Activity {
	private static final String TAG = "Serialization_preferences";
	private static final String PREF_FILE_NAME="PrefFile";
	private static final String PASSWORD="Password";
	private static final String DEFAULT_PWD = "Default";
	
	EditText editTextPwd;
	TextView TextViewPrefLoc;
	SharedPreferences settings;

	private String myString;	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		editTextPwd = (EditText)findViewById(R.id.EditTextPwd);
		TextViewPrefLoc = (TextView)findViewById(R.id.TextViewPrefLoc);

		//SHAREDPREFERENCES - PERMANENT STORAGE
		// get a handle to PREF_FILE_NAME, create if necessary, only this
		// process has access can have MODE_WORLD_READABLE and MODE_WORLD_WRITEABLE. See docs
		if (settings == null)
			settings = getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE);

		//note location of preference file if possible
		File f = getDatabasePath(PREF_FILE_NAME +".xml");

		if (f != null)
			TextViewPrefLoc.setText("Pref loc="+ f.getAbsolutePath());

		//populate with what is there
		doGetPref(null);
	}
	
	public void doSavePref(View v) {
		// can only make changes with editor
		SharedPreferences.Editor editor = settings.edit();

		// slap something in it, strings, bools nts, check the docs
		String myString = editTextPwd.getText().toString();
		editor.putString(PASSWORD, myString);

		// Commit the edits! You dont call this it aint saved!
		editor.commit();
	}

	public void doGetPref(View view) {
		//get from pref file
		String savedPwd = settings.getString(PASSWORD, DEFAULT_PWD);
		editTextPwd.setText(savedPwd);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
