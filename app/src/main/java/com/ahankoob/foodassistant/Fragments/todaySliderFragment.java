package com.ahankoob.foodassistant.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ahankoob.foodassistant.Models.food_calendar;
import com.ahankoob.foodassistant.Models.todaySliderModel;
import com.ahankoob.foodassistant.R;
import com.ahankoob.foodassistant.classes.CalendarTool;
import com.ahankoob.foodassistant.classes.FontManager;
import com.ahankoob.foodassistant.classes.tempToday;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;

public class todaySliderFragment extends Fragment {
	private int pageNumber;
	ArrayList<tempToday> list;
	ArrayList<todaySliderModel> sliderModels;

	public static todaySliderFragment newInstance(int page, Context context) {

		todaySliderFragment todaySliderFragment = new todaySliderFragment();
		Bundle args = new Bundle();
		args.putInt("pageNumber", page);
		todaySliderFragment.setArguments(args);
		Toast.makeText(context,String.valueOf(page), Toast.LENGTH_SHORT).show();
		return todaySliderFragment;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		list = new ArrayList<tempToday>();
		sliderModels = new ArrayList();

		CalendarTool calendarTool = new CalendarTool();
		int persianCurrentMonth = calendarTool.getIranianMonth();
		int monthDays=0;
		if (persianCurrentMonth>=1 && persianCurrentMonth<=6)
			monthDays=31;
		else
			monthDays=30;
		for (int i =monthDays;i>=1;i--){
			list.add(new tempToday(i,1,1,calendarTool.getIranianYear(),persianCurrentMonth,i));
			List<food_calendar> foodCalendars = food_calendar.find(food_calendar.class,"year = ? and mon = ? and day = ?",String.valueOf( calendarTool.getIranianYear()),
					String.valueOf( calendarTool.getIranianMonth())
					,String.valueOf( calendarTool.getIranianDay()));
			sliderModels.add(new todaySliderModel(i,foodCalendars));


		}

		pageNumber = getArguments().getInt("pageNumber", persianCurrentMonth);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.main_today_slider_item, container, false);
		TextView todayDate = view.findViewById(R.id.todayDate);
		TextView todayDateTitle = view.findViewById(R.id.todayDateTitle);
		FontManager.markAsIconContainer(todayDate, FontManager.getDastnevisFont(container.getContext().getAssets()));
		FontManager.markAsIconContainer(todayDateTitle, FontManager.getDastnevisFont(container.getContext().getAssets()));

		if(pageNumber<=(list.size()-1)) {
			tempToday model = (tempToday) list.get(pageNumber);
			todayDate.setText(model.getYear()+"/"+model.getMon()+"/"+model.getDay());
		}
		return view;
	}

}


