package com.ahankoob.foodassistant.Activities;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.ahankoob.foodassistant.Adapters.foodListViewAdapter;
import com.ahankoob.foodassistant.Dialogs.FoodItemMenuDialog;
import com.ahankoob.foodassistant.Models.food;
import com.ahankoob.foodassistant.R;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Foods extends AppCompatActivity {
	RecyclerView foodsRecyclerView;
	Toolbar toolbar;
	List<food> foods;
	SearchView FoodSearch;
	public foodListViewAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_foods);
		setVars();
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		foods = food.listAll(food.class,"ID DESC");
		adapter = new foodListViewAdapter(foods,this);
		RecyclerView.LayoutManager manager = new GridLayoutManager(Foods.this,1);
		foodsRecyclerView.setLayoutManager(manager);
		foodsRecyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
		foodsRecyclerView.setItemAnimator(new DefaultItemAnimator());

		foodsRecyclerView.setAdapter(adapter);
		ListView listView = new ListView(this);


		FoodSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String s) {

				return false;
			}

			@Override
			public boolean onQueryTextChange(String s) {
				foods = food.findWithQuery(food.class,"select * from FOOD where NAME LIKE '%"+s+"%'",null);
				adapter.setData(foods);
				adapter.notifyDataSetChanged();
				return false;
			}
		});



	}
	public void setVars(){

		toolbar =(Toolbar) findViewById(R.id.AppToolbar);
		foodsRecyclerView =(RecyclerView) findViewById(R.id.foodsRecyclerView);
		FoodSearch = findViewById(R.id.FoodSearch);

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.food_menu, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId()==android.R.id.home)
		{
			finish();
		}
		else if(item.getItemId() == R.id.food_new){
			FoodItemMenuDialog dialog = new FoodItemMenuDialog(this);
			dialog.show();
			dialog.onActionModeFinished(new ActionMode() {
				@Override
				public void setTitle(CharSequence charSequence) {

				}

				@Override
				public void setTitle(int i) {

				}

				@Override
				public void setSubtitle(CharSequence charSequence) {

				}

				@Override
				public void setSubtitle(int i) {

				}

				@Override
				public void setCustomView(View view) {

				}

				@Override
				public void invalidate() {

				}

				@Override
				public void finish() {
					adapter.notifyDataSetChanged();
				}

				@Override
				public Menu getMenu() {
					return null;
				}

				@Override
				public CharSequence getTitle() {
					return null;
				}

				@Override
				public CharSequence getSubtitle() {
					return null;
				}

				@Override
				public View getCustomView() {
					return null;
				}

				@Override
				public MenuInflater getMenuInflater() {
					return null;
				}
			});
		}
		return super.onOptionsItemSelected(item);
	}

	public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

		private int spanCount;
		private int spacing;
		private boolean includeEdge;

		public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
			this.spanCount = spanCount;
			this.spacing = spacing;
			this.includeEdge = includeEdge;
		}

		@Override
		public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
			int position = parent.getChildAdapterPosition(view); // item position
			int column = position % spanCount; // item column

			if (includeEdge) {
				outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
				outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

				if (position < spanCount) { // top edge
					outRect.top = spacing;
				}
				outRect.bottom = spacing; // item bottom
			} else {
				outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
				outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
				if (position >= spanCount) {
					outRect.top = spacing; // item top
				}
			}
		}
	}

	/**
	 * Converting dp to pixel
	 */
	private int dpToPx(int dp) {
		Resources r = getResources();
		return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
	}


}
