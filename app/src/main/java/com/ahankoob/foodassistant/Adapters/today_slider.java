package com.ahankoob.foodassistant.Adapters;

import android.content.Context;

import com.ahankoob.foodassistant.Fragments.todaySliderFragment;
import com.ahankoob.foodassistant.classes.CalendarTool;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class today_slider extends FragmentPagerAdapter {
	Context context;

	public today_slider(@NonNull FragmentManager fragmentManager,Context context) {
		super(fragmentManager);
		this.context = context;
	}


	@NonNull
	@Override
	public Fragment getItem(int position) {
		Fragment frm = todaySliderFragment.newInstance(position,context);
		return frm;
	}

	@Override
	public int getCount() {
		CalendarTool calendarTool = new CalendarTool();
		int persianCurrentMonth = calendarTool.getIranianMonth();
		int monthDays=0;
		if (persianCurrentMonth>=1 && persianCurrentMonth<=6)
			monthDays=31;
		else
			monthDays=30;
		return monthDays;
	}


}

