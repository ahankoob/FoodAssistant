package com.ahankoob.foodassistant.App;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.ahankoob.foodassistant.Models.food;
import com.ahankoob.foodassistant.Models.food_calendar;
import com.ahankoob.foodassistant.Models.food_meal;
import com.ahankoob.foodassistant.Models.meal;
import com.orm.SugarApp;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class myApp extends SugarApp {

	public myApp() {
		super();

	}

	@Override
	public void onCreate() {
		super.onCreate();
		if (!checkDataBase()) {
			try {
				copyDataBase();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	protected void copyDataBase() throws IOException {
		dbSampleData();
	}

	protected boolean checkDataBase() {

		SQLiteDatabase checkDB = null;

		try {
			String myPath = "/data/data/com.ahankoob.foodassistant/databases/" + "myDB.db";
			checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

		} catch (SQLiteException e) {

			//database does't exist yet.

		}

		if (checkDB != null) {

			checkDB.close();

		}

		return checkDB != null ? true : false;
	}
	public void dbSampleData()
	{
		new meal(1, "صبحانه", 8, 0).save();
		new meal(2, "ناهار", 13, 0).save();
		new meal(3, "شام", 20, 0).save();

		new food(1, 1, "نان و پنیر").save();
		new food(2, 1, "تخم مرغ").save();
		new food(3, 2, "قورمه سبزی").save();
		new food(4, 2, "قیمه سبزی").save();
		new food(5, 2, "قیمه").save();
		new food(6, 2, "کباب").save();
		new food(7, 2, "چلو مرغ").save();
		new food(8, 3, "سالاد ماکارونی").save();
		new food(9, 3, "الویه").save();
		new food(10, 3, "میرزا قاسمی").save();

		new food_meal(0,1,1).save();
		new food_meal(0,2,1).save();
		new food_meal(0,2,3).save();
		new food_meal(0,3,2).save();
		new food_meal(0,3,3).save();
		new food_meal(0,4,2).save();
		new food_meal(0,4,3).save();
		new food_meal(0,5,2).save();
		new food_meal(0,6,2).save();
		new food_meal(0,7,2).save();
		new food_meal(0,8,2).save();
		new food_meal(0,8,3).save();
		new food_meal(0,9,2).save();
		new food_meal(0,9,3).save();
		new food_meal(0,10,2).save();
		new food_meal(0,10,3).save();

		//new foodCalendar(1,1,2,1399,3,1).save();
		int foodcalendar_id = 0;
		List<food_meal> sobhaneList = Select.from(food_meal.class).where(Condition.prop("MEALID").eq("1")).list();
		List<food_meal> naharList = Select.from(food_meal.class).where(Condition.prop("MEALID").eq("2")).list();
		List<food_meal> shamList = Select.from(food_meal.class).where(Condition.prop("MEALID").eq("3")).list();
		for (int i = 1; i <= 31; i++) {
			Random rand = new Random();

			food_meal sobhane =  sobhaneList.get(rand.nextInt(sobhaneList.size()));
			food_meal nahar = naharList.get( rand.nextInt(naharList.size()));
			food_meal sham = shamList.get( rand.nextInt(shamList.size()));

			new food_calendar(0, sobhane.food_id, sobhane.meal_id,sobhane.getId().intValue(), 1399, 3, i).save();
			new food_calendar(0, nahar.food_id, nahar.meal_id,nahar.getId().intValue(), 1399, 3, i).save();
			new food_calendar(0, sham.food_id, sham.meal_id,sham.getId().intValue(), 1399, 3, i).save();



		}
	}
}