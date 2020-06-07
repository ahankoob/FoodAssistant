package com.ahankoob.foodassistant.Activities;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;

import com.ahankoob.foodassistant.Adapters.foodListViewAdapter;
import com.ahankoob.foodassistant.Models.food;
import com.ahankoob.foodassistant.R;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Foods extends AppCompatActivity {
	RecyclerView foodsRecyclerView;
	Toolbar toolbar;
	List<food> foods;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_foods);
		setSupportActionBar(toolbar);
		setVars();
		foods = food.listAll(food.class);
		foodListViewAdapter adapter = new foodListViewAdapter(foods,this);
		RecyclerView.LayoutManager manager = new GridLayoutManager(Foods.this,1);
		foodsRecyclerView.setLayoutManager(manager);
		foodsRecyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
		foodsRecyclerView.setItemAnimator(new DefaultItemAnimator());

		foodsRecyclerView.setAdapter(adapter);


	}
	public void setVars(){

		toolbar =(Toolbar) findViewById(R.id.AppToolbar);
		foodsRecyclerView =(RecyclerView) findViewById(R.id.foodsRecyclerView);


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
