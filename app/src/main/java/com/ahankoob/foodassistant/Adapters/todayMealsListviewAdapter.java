package com.ahankoob.foodassistant.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ahankoob.foodassistant.Models.food;
import com.ahankoob.foodassistant.Models.food_calendar;
import com.ahankoob.foodassistant.Models.meal;
import com.ahankoob.foodassistant.R;
import com.ahankoob.foodassistant.classes.FontManager;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class todayMealsListviewAdapter extends ArrayAdapter {
	Context context;
	List<food_calendar> calendar;

	public todayMealsListviewAdapter(Context context, int resource, List<food_calendar> calendar) {
		super(context, resource, calendar);
		this.context = context;
		this.calendar = calendar;
	}

	@Override
	public int getCount() {
		return calendar.size();
	}

	@Nullable
	@Override
	public Object getItem(int position) {
		return calendar.get(position);
	}

	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		View view;
		if(convertView==null) {
			view = ((LayoutInflater) parent.getContext().getSystemService(context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.main_today_meals_listview_item, parent, false);
			food_calendar foodCalendar = calendar.get(position);

			meal myMeal = meal.findById(meal.class, foodCalendar.meal_id);
			food myFood = food.findById(food.class, foodCalendar.food_id);
			TextView todayMealTitle = (TextView) view.findViewById(R.id.todayMealTitle);
			TextView todayMeal = (TextView) view.findViewById(R.id.todayMeal);

			todayMealTitle.setText(myMeal.name);
			todayMeal.setText(myFood.name);

			FontManager.markAsIconContainer(todayMealTitle, FontManager.getDastnevisFont(parent.getContext().getAssets()));
			FontManager.markAsIconContainer(todayMeal, FontManager.getLalezarFont(parent.getContext().getAssets()));

		}
		else{
			view = convertView;
		}


		return view;
	}
}

