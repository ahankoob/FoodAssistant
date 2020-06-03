package com.ahankoob.foodassistant.Activities;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;

import com.ahankoob.foodassistant.R;
import com.google.android.material.appbar.AppBarLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Main extends AppCompatActivity {

	Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		toolbar =(Toolbar) findViewById(R.id.AppToolbar);
		setSupportActionBar(toolbar);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			((AppBarLayout)findViewById(R.id.mainAppBar)).setOutlineProvider(null);
		}

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}
}
