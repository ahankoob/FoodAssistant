package com.ahankoob.foodassistant.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.ahankoob.foodassistant.Models.food;
import com.ahankoob.foodassistant.Models.food_meal;
import com.ahankoob.foodassistant.Models.meal;
import com.ahankoob.foodassistant.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class foodItemMenuDialogAdapter extends ArrayAdapter<meal> {
	Context context;
	List<meal> meals;
	food MyFood;
	public List<Integer> SelectedItems = new ArrayList<>();
	public foodItemMenuDialogAdapter(Context context, int resource, List<meal> meals,@Nullable food myFood) {
		super(context, resource, meals);
		this.context = context;
		this.meals = meals;

		this.MyFood = myFood;
		int i = -1;
		SelectedItems.add(i);
	}
	public void addItemSelected(int index){
		SelectedItems.add(index);

	}
	public void removeItemSelected(int index){
		try {
			for (int i : SelectedItems)
				if (i == index)
					SelectedItems.remove((Integer) i);
		}catch (Exception e){};
	}
	public boolean isSelectedItem(int index){
		for(int item:SelectedItems)
			if(item==index)
				return true;
		return false;

	}
	public String getSelecterItems(){
		String output="";
		for (int i:SelectedItems)
			output+=String.valueOf(i)+" , ";
		return  output;
	}



	@Override
	public int getCount() {
		return meals.size();
	}

	@Nullable
	@Override
	public meal getItem(int position) {
		return meals.get(position);
	}

	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		meal item = meals.get(position);
		View view = null;
		if (convertView == null) {
			view = ((LayoutInflater) getContext().getSystemService(context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.food_item_menu_dialog_item, parent, false);

			CheckedTextView checkbox = view.findViewById(R.id.checkbox);
			TextView mealID = view.findViewById(R.id.meal_id);

			checkbox.setText(item.name);
			mealID.setText(String.valueOf(item.getId().intValue()));
			if (MyFood != null) {
				List<food_meal> myFoodMeals = food_meal.find(food_meal.class, "FOODID=?", String.valueOf(MyFood.getId()));
				int index = 0;
				for (food_meal obj : myFoodMeals) {
					if (obj.meal_id == item.getId()) {
						checkbox.setChecked(true);
						addItemSelected(Integer.valueOf(index));
					}
					index++;
				}
			}
			convertView = view;

		}
		else
		{
			view = (View) convertView.getTag();
			Toast.makeText(context, "1", Toast.LENGTH_SHORT).show();
			TextView text = view.findViewById(R.id.text);

			CheckBox checkbox = view.findViewById(R.id.checkbox);
			TextView mealID = view.findViewById(R.id.meal_id);

			text.setText(item.name);
			mealID.setText(String.valueOf(item.getId().intValue()));

		}
		return view;
	}

}
