package com.ahankoob.foodassistant.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ahankoob.foodassistant.Models.food;
import com.ahankoob.foodassistant.Models.food_meal;
import com.ahankoob.foodassistant.Models.meal;
import com.ahankoob.foodassistant.R;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class foodListViewAdapter extends ArrayAdapter<food> {
	List<food> foods ;
	Context context;

	public foodListViewAdapter( Context context, int resource, List<food> foods) {
		super(context, resource, foods);
		this.foods = foods;
		this.context = context;
	}

	@Override
	public int getCount() {
		return foods.size();
	}

	@Nullable
	@Override
	public food getItem(int position) {
		return foods.get(position);
	}

	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		View view=null;
		food item = foods.get(position);
		if(convertView==null){
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.food_listview_item,parent,false);

			List<food_meal> food_meals = Select.from(food_meal.class).where(Condition.prop("FOODID").eq(item.getId())).list();
			String meals_list="";
			for(food_meal myFoodMeal : food_meals){
				meal myMeal = meal.findById(meal.class,myFoodMeal.meal_id);
				meals_list+=myMeal.name+" ";
			}

			TextView food_name = view.findViewById(R.id.food_name);
			food_name.setText(item.name);
			TextView meal_name = view.findViewById(R.id.meal_name);
			meal_name.setText(meals_list);
		}
		else
		{
			view = convertView;
		}
		return view;
	}
}
