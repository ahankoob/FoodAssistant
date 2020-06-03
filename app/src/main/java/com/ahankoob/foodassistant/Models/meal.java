package com.ahankoob.foodassistant.Models;


import com.orm.SugarRecord;
import com.orm.dsl.Unique;

public class meal extends SugarRecord {
	@Unique
	int id;
	String name;
	int hour;
	int min;

	public meal() {
	}

	public meal(int id, String name, int hour, int min) {
		this.id = id;
		this.name = name;
		this.hour = hour;
		this.min = min;
	}
}
