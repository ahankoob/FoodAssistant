package com.ahankoob.foodassistant.classes;

import com.ahankoob.foodassistant.Models.food;
import com.ahankoob.foodassistant.Models.food_calendar;
import com.ahankoob.foodassistant.Models.meal;

import java.util.Random;

public class dbSampleData {
	public dbSampleData()
	{
		new meal(1,"صبحانه",8,0).save();
		new meal(2,"ناهار",13,0).save();
		new meal(3,"شام",20,0).save();

		new food(1,1,"نان و پنیر").save();
		new food(2,1,"تخم مرغ").save();
		new food(3,2,"قورمه سبزی").save();
		new food(4,2,"قیمه سبزی").save();
		new food(5,2,"قیمه").save();
		new food(6,2,"کباب").save();
		new food(7,2,"چلو مرغ").save();
		new food(8,3,"سالاد ماکارونی").save();
		new food(9,3,"الویه").save();
		new food(10,3,"میرزا قاسمی").save();


		//new foodCalendar(1,1,2,1399,3,1).save();
		int foodcalendar_id=0;
		for(int i=1;i<=31;i++){
			foodcalendar_id++;
			Random rand = new Random();

			int sobhane_id = rand.nextInt(2)+1;
			int nahar_id = rand.nextInt(5)+3;
			int sham_id = rand.nextInt(3)+8;
			new food_calendar(foodcalendar_id,sobhane_id,1,1399,3,i).save();
			foodcalendar_id++;

			new food_calendar(foodcalendar_id,nahar_id,2,1399,3,i).save();
			foodcalendar_id++;

			new food_calendar(foodcalendar_id,sham_id,3,1399,3,i).save();
			foodcalendar_id++;



		}
	}


}
