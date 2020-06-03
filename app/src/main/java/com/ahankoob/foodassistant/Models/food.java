package com.ahankoob.foodassistant.Models;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;


public class food extends SugarRecord{
	@Unique
	int id;
	int meal_id;
	String name;

	public food() {
	}

	public food(int id, int meal_id, String name) {
		this.id = id;
		this.meal_id = meal_id;
		this.name = name;
	}
}
