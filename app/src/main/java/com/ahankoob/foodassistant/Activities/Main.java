package com.ahankoob.foodassistant.Activities;

import android.os.Build;
import android.os.Bundle;

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
}
