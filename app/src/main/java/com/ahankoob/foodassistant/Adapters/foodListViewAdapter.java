package com.ahankoob.foodassistant.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.ahankoob.foodassistant.Models.food;
import com.ahankoob.foodassistant.Models.food_meal;
import com.ahankoob.foodassistant.Models.meal;
import com.ahankoob.foodassistant.R;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class foodListViewAdapter extends RecyclerView.Adapter<foodListViewAdapter.ViewHolder> {
	List<food> foods ;
	Context context;

	public foodListViewAdapter(List<food> foods, Context context) {
		this.foods = foods;
		this.context = context;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_listview_item,parent,false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		food item = foods.get(position);
		List<food_meal> food_meals = Select.from(food_meal.class).where(Condition.prop("FOODID").eq(item.getId())).list();
		String meals_list="";
		for(food_meal myFoodMeal : food_meals){
			meal myMeal = meal.findById(meal.class,myFoodMeal.meal_id);
			meals_list+=myMeal.name+" ";
		}

		holder.food_name.setText(item.name);
		holder.meal_name.setText(meals_list);
		final PopupMenu popup = new PopupMenu(holder.popMenu.getContext(),holder.popMenu);
		popup.inflate(R.menu.food_item_popmenu);
		holder.popMenu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				popup.show();
			}
		});

	}

	@Override
	public int getItemCount() {
		return foods.size();
	}


	public class ViewHolder extends RecyclerView.ViewHolder{

		TextView food_name,meal_name;
		ImageButton popMenu;
		public ViewHolder(View itemView) {
			super(itemView);
			food_name = (TextView) itemView.findViewById(R.id.food_name);
			meal_name = (TextView) itemView.findViewById(R.id.meal_name);
			popMenu = (ImageButton) itemView.findViewById(R.id.popMenu);
		}
	}

}
