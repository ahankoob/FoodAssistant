package com.ahankoob.foodassistant.Adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.TimePicker;

import com.ahankoob.foodassistant.Dialogs.MealItemMenuDialog;
import com.ahankoob.foodassistant.Models.meal;
import com.ahankoob.foodassistant.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

public class mealsListViewAdapter extends RecyclerView.Adapter<mealsListViewAdapter.ViewHolder> {
	List<meal> meals ;
	Context context;

	public mealsListViewAdapter(List<meal> meals, Context context) {
		this.meals = meals;
		this.context = context;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_listview_item,parent,false);
		return new ViewHolder(view);
	}

	@RequiresApi(api = Build.VERSION_CODES.M)
	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		final meal item = meals.get(position);

		holder.meal_name.setText(item.name);
		holder.timepicker.setHour(item.hour);
		holder.timepicker.setMinute(item.min);
		final PopupMenu popup = new PopupMenu(holder.popMenu.getContext(),holder.popMenu);
		popup.inflate(R.menu.meal_item_popmenu);
		holder.popMenu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				popup.show();
			}
		});
		popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem menuItem) {
				if (menuItem.getItemId()==R.id.meal_edit){
					MealItemMenuDialog dialog = new MealItemMenuDialog(context,item.getId().intValue());
					dialog.show();
				}
				else if (menuItem.getItemId()==R.id.meal_remove){
					meal.delete(meal.findById(meal.class,item.getId()));
					mealsListViewAdapter.this.setData(meal.listAll(meal.class,"ID DESC"));
					mealsListViewAdapter.this.notifyDataSetChanged();
				}
				return false;
			}
		});

	}
	public void setData(List<meal> meals){
		this.meals = meals;
		notifyDataSetChanged();
	}
	@Override
	public int getItemCount() {
		return meals.size();
	}


	public class ViewHolder extends RecyclerView.ViewHolder{

		TextView meal_name;
		TimePicker timepicker;
		ImageButton popMenu;
		public ViewHolder(View itemView) {
			super(itemView);
			meal_name = (TextView) itemView.findViewById(R.id.meal_name);
			timepicker = itemView.findViewById(R.id.timePicker);
			popMenu = (ImageButton) itemView.findViewById(R.id.popMenu);
		}
	}

}
