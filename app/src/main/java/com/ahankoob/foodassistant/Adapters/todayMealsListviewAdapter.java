package com.ahankoob.foodassistant.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.ahankoob.foodassistant.Models.todaySliderModel;
import com.ahankoob.foodassistant.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class todayMealsListviewAdapter extends ArrayAdapter {
	Context context;
	ArrayList<todaySliderModel> sliderModels;
	public todayMealsListviewAdapter( Context context, int resource, ArrayList<todaySliderModel> objects) {
		super(context, resource, objects);
		this.context = context;
		this.sliderModels = objects;
	}

	@Override
	public int getCount() {
		return sliderModels.size();
	}

	@Nullable
	@Override
	public Object getItem(int position) {
		return sliderModels.get(position);
	}

	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		View view = ((LayoutInflater)parent.getContext().getSystemService(context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.main_today_meals_listview_item,parent,false);
		return super.getView(position, convertView, parent);
	}
}
