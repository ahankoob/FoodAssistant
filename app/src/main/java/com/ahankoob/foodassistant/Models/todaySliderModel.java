package com.ahankoob.foodassistant.Models;

import java.util.List;

public class todaySliderModel {
	int number;
	List<food_calendar> calendar;

	public todaySliderModel(int number, List<food_calendar> calendar) {
		this.number = number;
		this.calendar = calendar;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public List<food_calendar> getCalendar() {
		return calendar;
	}

	public void setCalendar(List<food_calendar> calendar) {
		this.calendar = calendar;
	}
}
