package com.ahankoob.foodassistant.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;

import com.ahankoob.foodassistant.Activities.Foods;
import com.ahankoob.foodassistant.Adapters.foodItemMenuDialogAdapter;
import com.ahankoob.foodassistant.Models.food;
import com.ahankoob.foodassistant.Models.food_meal;
import com.ahankoob.foodassistant.Models.meal;
import com.ahankoob.foodassistant.R;

import java.util.List;

import androidx.annotation.NonNull;

public class FoodItemMenuDialog extends Dialog {

	Context context;
	List<meal> meals;
	int food_id;
	public FoodItemMenuDialog(@NonNull Context context) {
		super(context);
		this.context = context;
		this.meals = meal.listAll(meal.class);

		food_id=0;

	}
	public FoodItemMenuDialog( Context context,int food_id) {
		super(context);
		this.context = context;
		this.food_id=food_id;
		this.meals = meal.listAll(meal.class);

	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.food_item_menu_dialog);
		final ListView mealsListView = (findViewById(R.id.mealsListView));
		final TextView food_id_editText = findViewById(R.id.food_id);
		final TextView food_name= findViewById(R.id.food_name);


		food myFood = null;
		if(food_id!=0){
			myFood = food.findById(food.class, food_id);
			food_id_editText.setText(String.valueOf( myFood.getId().intValue()));
			food_name.setText(myFood.name);
		}
		else{
			myFood = new food(0,0,"");
		}

		final foodItemMenuDialogAdapter adapter = new foodItemMenuDialogAdapter(context, R.layout.food_item_menu_dialog_item, meals,myFood);

		mealsListView.setAdapter(adapter);
		mealsListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		mealsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				CheckedTextView checkBox = view.findViewById(R.id.checkbox);
				if(checkBox.isChecked()){
					checkBox.setChecked(false);
					adapter.removeItemSelected(i);
				}
				else
				{
					checkBox.setChecked(true);
					adapter.addItemSelected(i);
				}



			}
		}) ;

		findViewById(R.id.btn_dialog_close).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				FoodItemMenuDialog.this.hide();
			}
		});
		final food finalMyFood = myFood;
		findViewById(R.id.btn_dialog_save).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finalMyFood.name = food_name.getText().toString();
				finalMyFood.save();

				food_meal.deleteAll(food_meal.class,"FOODID=?",food_id_editText.getText().toString().trim());
				for (int i = 0; i < mealsListView.getAdapter().getCount(); i++) {
					if (((foodItemMenuDialogAdapter)mealsListView.getAdapter()).isSelectedItem(i)) {
						View ItemView = mealsListView.getAdapter().getView(i, null, mealsListView);

						CheckedTextView checkbox = ItemView.findViewById(R.id.checkbox);
						TextView meal_id = ItemView.findViewById(R.id.meal_id);
						new food_meal(0,finalMyFood.getId().intValue(),Integer.parseInt(meal_id.getText().toString())).save();
					}
				}
				((Foods)context).adapter.setData(food.listAll(food.class,"ID DESC"));
				((Foods)context).adapter.notifyDataSetChanged();
				FoodItemMenuDialog.this.hide();
			}
		});


	}
}
