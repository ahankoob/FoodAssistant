package com.ahankoob.foodassistant.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ahankoob.foodassistant.Models.foodCalendar;
import com.ahankoob.foodassistant.Models.todaySliderModel;
import com.ahankoob.foodassistant.R;
import com.ahankoob.foodassistant.classes.CalendarTool;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;

public class todaySliderFragment extends Fragment {
	private int pageNumber;
	ArrayList<todaySliderModel> list;

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
		list = new ArrayList<todaySliderModel>();

		CalendarTool calendarTool = new CalendarTool();
		int persianCurrentMonth = calendarTool.getIranianMonth();
		int monthDays=0;
		if (persianCurrentMonth>=1 && persianCurrentMonth<=6)
			monthDays=31;
		else
			monthDays=30;
		for (int i =monthDays;i>=1;i--){
			list.add(new todaySliderModel(i,new foodCalendar(i,1,1,calendarTool.getIranianYear(),persianCurrentMonth,i)));
		}

		pageNumber = getArguments().getInt("pageNumber", persianCurrentMonth);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.main_today_slider_item, container, false);
		TextView todayDate = view.findViewById(R.id.todayDate);
		if(pageNumber<=(list.size()-1)) {
			todaySliderModel model = (todaySliderModel)list.get(pageNumber);
			todayDate.setText(model.getCalendar().getYear()+"/"+model.getCalendar().getMon()+"/"+model.getCalendar().getDay());
		}
		return view;
	}

}


