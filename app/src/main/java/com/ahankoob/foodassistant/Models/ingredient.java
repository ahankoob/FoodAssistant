package com.ahankoob.foodassistant.Models;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

public class ingredient extends SugarRecord {
	@Unique
	int id;
	String name;

	public ingredient() {
	}

	public ingredient(int id, String name) {
		this.id = id;
		this.name = name;
	}
}
