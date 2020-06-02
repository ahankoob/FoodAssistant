package com.ahankoob.foodassistant.Models;

import com.dbflow5.annotation.Column;
import com.dbflow5.annotation.PrimaryKey;

import java.util.UUID;



public class meal {
	@PrimaryKey
	UUID id;

	@Column
	String name;

	@Column
	int hour;

	@Column
	int min;



}
