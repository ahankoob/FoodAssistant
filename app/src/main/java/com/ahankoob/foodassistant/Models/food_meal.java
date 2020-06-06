package com.ahankoob.foodassistant.Models;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

public class food_meal extends SugarRecord {
	@Unique
	public int id;
	public int food_id;
	public int meal_id;

	public food_meal() {
	}

	public food_meal(int id, int food_id, int meal_id) {
		this.id = id;
		this.food_id = food_id;
		this.meal_id = meal_id;
	}
}
