package com.ahankoob.foodassistant.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import com.ahankoob.foodassistant.Activities.Meals;
import com.ahankoob.foodassistant.Models.meal;
import com.ahankoob.foodassistant.R;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

public class MealItemMenuDialog extends Dialog {

	Context context;
	int meal_id;
	public MealItemMenuDialog(@NonNull Context context) {
		super(context);
		this.context = context;

		meal_id=0;

	}
	public MealItemMenuDialog(Context context, int meal_id) {
		super(context);
		this.context = context;
		this.meal_id=meal_id;

	}
	@RequiresApi(api = Build.VERSION_CODES.M)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.meal_item_menu_dialog);
		final TextView meal_id_editText = findViewById(R.id.meal_id);
		final TextView meal_name= findViewById(R.id.meal_name);
		final TimePicker timePicker= findViewById(R.id.timePicker);


		meal myMeal = null;
		if(meal_id!=0){
			myMeal = meal.findById(meal.class, meal_id);
			meal_id_editText.setText(String.valueOf( myMeal.getId().intValue()));
			timePicker.setHour(myMeal.hour);
			timePicker.setMinute(myMeal.min);
			meal_name.setText(myMeal.name);
		}
		else{
			myMeal = new meal(0,meal_name.getText().toString(),timePicker.getHour(),timePicker.getMinute());
		}

		findViewById(R.id.btn_dialog_close).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				MealItemMenuDialog.this.hide();
			}
		});
		final meal finalMyMeal = myMeal;
		findViewById(R.id.btn_dialog_save).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finalMyMeal.name = meal_name.getText().toString();
				finalMyMeal.hour = timePicker.getHour();
				finalMyMeal.min = timePicker.getMinute();
				finalMyMeal.save();

				((Meals)context).adapter.setData(meal.listAll(meal.class,"ID DESC"));
				((Meals)context).adapter.notifyDataSetChanged();
				MealItemMenuDialog.this.hide();
			}
		});


	}
}
