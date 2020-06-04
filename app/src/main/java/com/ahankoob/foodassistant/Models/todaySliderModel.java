package com.ahankoob.foodassistant.Models;

public class todaySliderModel {
	int number;
	foodCalendar calendar;

	public todaySliderModel(int number, foodCalendar calendar) {
		this.number = number;
		this.calendar = calendar;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public foodCalendar getCalendar() {
		return calendar;
	}

	public void setCalendar(foodCalendar calendar) {
		this.calendar = calendar;
	}
}
