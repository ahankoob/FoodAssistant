package com.ahankoob.foodassistant.Activities;

import android.os.Bundle;
import android.widget.ListView;

import com.ahankoob.foodassistant.Adapters.foodListViewAdapter;
import com.ahankoob.foodassistant.Models.food;
import com.ahankoob.foodassistant.R;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Foods extends AppCompatActivity {
	ListView food_listview;
	Toolbar toolbar;
	List<food> foods;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_foods);
		setVars();
		foods = food.listAll(food.class);
		foodListViewAdapter adapter = new foodListViewAdapter(this,food_listview.getId(),foods);
		food_listview.setAdapter(adapter);


	}
	public void setVars(){

		toolbar =(Toolbar) findViewById(R.id.AppToolbar);
		food_listview =(ListView) findViewById(R.id.food_listview);


	}
}
