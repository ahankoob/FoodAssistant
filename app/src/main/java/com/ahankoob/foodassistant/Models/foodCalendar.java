package com.ahankoob.foodassistant.Models;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

public class foodCalendar extends SugarRecord {
	@Unique
	int id;
	int food_id;
	int meal_id;
	int year,mon,day;

	public foodCalendar() {
	}

	public foodCalendar(int id, int food_id, int meal_id, int year, int mon, int day) {
		this.id = id;
		this.food_id = food_id;
		this.meal_id = meal_id;
		this.year = year;
		this.mon = mon;
		this.day = day;
	}



	public void setId(int id) {
		this.id = id;
	}

	public int getFood_id() {
		return food_id;
	}

	public void setFood_id(int food_id) {
		this.food_id = food_id;
	}

	public int getMeal_id() {
		return meal_id;
	}

	public void setMeal_id(int meal_id) {
		this.meal_id = meal_id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMon() {
		return mon;
	}

	public void setMon(int mon) {
		this.mon = mon;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}
}
