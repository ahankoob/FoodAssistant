package com.ahankoob.foodassistant.Models;

import com.ahankoob.foodassistant.DB.myDB;
import com.dbflow5.annotation.Column;
import com.dbflow5.annotation.PrimaryKey;
import com.dbflow5.annotation.Table;

import java.util.UUID;

@Table(database = myDB.class)
public class food {
	@PrimaryKey
	UUID id;

	@Column
	int meal_id;

	@Column
	String name;

}
