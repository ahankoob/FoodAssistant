package com.ahankoob.foodassistant.Activities;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;

import com.ahankoob.foodassistant.Adapters.today_slider;
import com.ahankoob.foodassistant.R;
import com.ahankoob.foodassistant.classes.CalendarTool;
import com.google.android.material.appbar.AppBarLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class Main extends AppCompatActivity {

	Toolbar toolbar;
	ViewPager todayPager;
	FragmentPagerAdapter adapterViewPager;
	private int lastPosition = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setVars();
		setSupportActionBar(toolbar);
		todayPager.setAdapter(adapterViewPager);
		CalendarTool calendarTool = new CalendarTool();
		int persianCurrentDay = calendarTool.getIranianDay();
		todayPager.setCurrentItem(persianCurrentDay+1);
		todayPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				if (lastPosition > position) {
					System.out.println("Left");
				}else if (lastPosition < position) {
					System.out.println("Right");
				}
				lastPosition = position;
			}

			@Override
			public void onPageSelected(int position) {

			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			((AppBarLayout)findViewById(R.id.mainAppBar)).setOutlineProvider(null);
		}




	}
	public void setVars(){

		toolbar =(Toolbar) findViewById(R.id.AppToolbar);
		todayPager = (ViewPager) findViewById(R.id.TodayFoodSlider);
		adapterViewPager = new today_slider(getSupportFragmentManager(),this);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}
}
