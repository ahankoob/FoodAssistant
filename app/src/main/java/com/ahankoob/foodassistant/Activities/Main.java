package com.ahankoob.foodassistant.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.ahankoob.foodassistant.Adapters.today_slider;
import com.ahankoob.foodassistant.R;
import com.ahankoob.foodassistant.classes.FontManager;
import com.ahankoob.foodassistant.classes.JDF;
import com.google.android.material.appbar.AppBarLayout;

import java.util.Calendar;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class Main extends AppCompatActivity {

	Toolbar toolbar;
	ViewPager todayPager;
	FragmentPagerAdapter adapterViewPager;
	TextView mainFoodCalcItem,mainFoodsItem,mainMealsItem,mainSettingsItem;
	private int lastPosition = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//new dbSampleData();
		setVars();
		setfonts();
		setSupportActionBar(toolbar);
		todayPager.setAdapter(adapterViewPager);
		Date mydate =  Calendar.getInstance().getTime();

		JDF calendarTool = new JDF();
		int persianCurrentDay = calendarTool.getIranianDay();
		todayPager.setCurrentItem(getMonthDays()-persianCurrentDay);



		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			((AppBarLayout)findViewById(R.id.mainAppBar)).setOutlineProvider(null);
		}
		((CardView)findViewById(R.id.mainFoodsItemCard)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(Main.this,Foods.class));
			}
		});
		((CardView)findViewById(R.id.mainMealsItemCard)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(Main.this,Meals.class));
			}
		});



	}
	public void setVars(){

		toolbar =(Toolbar) findViewById(R.id.AppToolbar);
		todayPager = (ViewPager) findViewById(R.id.TodayFoodSlider);
		adapterViewPager = new today_slider(getSupportFragmentManager(),this);

		mainFoodCalcItem = (TextView) findViewById(R.id.mainFoodCalcItem);
		mainFoodsItem = (TextView) findViewById(R.id.mainFoodsItem);
		mainMealsItem = (TextView) findViewById(R.id.mainMealsItem);
		mainSettingsItem = (TextView) findViewById(R.id.mainSettingsItem);
	}
	public void setfonts(){
		FontManager.markAsIconContainer(mainFoodCalcItem, FontManager.getLalezarFont(getAssets()));
		FontManager.markAsIconContainer(mainFoodsItem, FontManager.getLalezarFont(getAssets()));
		FontManager.markAsIconContainer(mainMealsItem, FontManager.getLalezarFont(getAssets()));
		FontManager.markAsIconContainer(mainSettingsItem, FontManager.getLalezarFont(getAssets()));

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}
	public int getMonthDays(){
		JDF calendarTool = new JDF();
		int persianCurrentMonth = calendarTool.getIranianMonth();
		int monthDays=0;
		if (persianCurrentMonth>=1 && persianCurrentMonth<=6)
			monthDays=31;
		else
			monthDays=30;
		return monthDays;
	}


}
