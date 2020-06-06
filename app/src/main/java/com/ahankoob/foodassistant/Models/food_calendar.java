package com.ahankoob.foodassistant.Models;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

public class food_calendar extends SugarRecord {
	@Unique
	public int id;
	public int food_id;
	public int meal_id;
	public int food_meal_id;
	public int year,mon,day;

	public food_calendar() {
	}

	public food_calendar(int id, int food_id, int meal_id, int food_meal_id, int year, int mon, int day) {
		this.id = id;
		this.food_id = food_id;
		this.meal_id = meal_id;
		this.food_meal_id = food_meal_id;
		this.year = year;
		this.mon = mon;
		this.day = day;
	}
}
