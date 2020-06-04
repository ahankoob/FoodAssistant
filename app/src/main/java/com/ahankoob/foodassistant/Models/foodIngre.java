package com.ahankoob.foodassistant.Models;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

public class foodIngre extends SugarRecord {
	@Unique
	int id;

	int food_id;
	int ingre_id;

	public foodIngre() {
	}

	public foodIngre(int id, int food_id, int ingre_id) {
		this.id = id;
		this.food_id = food_id;
		this.ingre_id = ingre_id;
	}
}
