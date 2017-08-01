package com.bubula.notebook.key;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bubula.notebook.R;
import com.bubula.notebook.kLog.KLog;

import java.util.ArrayList;
import java.util.List;


public class ScrollGridLayout extends LinearLayout implements
		OnPageChangeListener {
	private static final String TAG = "ScrollGridLayout";
	private Context mcontext;
	private Activity activity;
	private ViewPager facePager;// 表情页面
	private LinearLayout indicatorLayout;// 表情页面个数
	private ScrollGridPagerAdapter facePagerAdapter;
	private List<View> views = new ArrayList<View>();
	private int screenWidthDip;
	private float density;//
	private int gridItemWidth;// grid行宽度
	private int horizontalSpacing = 10;// gridView行间距
	private int Horizontal_num = 3;// 要显示的行数
	private setOnItemClickListener itemClickListener;
	private List<ImageView> indicators = new ArrayList<ImageView>();

	private void initview(Context context) {
		this.mcontext = context;
		this.activity = (Activity) context;
		LayoutInflater.from(mcontext).inflate(R.layout.view_scrollgrid, this,
				true);
		facePager = (ViewPager) findViewById(R.id.face_vPager);
		indicatorLayout = (LinearLayout) findViewById(R.id.indicator);
		DisplayMetrics dm = new DisplayMetrics();
		((Activity) mcontext).getWindowManager().getDefaultDisplay()
				.getMetrics(dm);
		screenWidthDip = dm.widthPixels;
		this.density = dm.density;
		gridItemWidth = getGridItemWidth();
	}

	public int setHorizontalNum(int horizontalNnum) {
		this.Horizontal_num = horizontalNnum;
		gridItemWidth = getGridItemWidth();
		return gridItemWidth;
	}

	public void setOnItemClickListener(setOnItemClickListener itemClickListener) {
		this.itemClickListener = itemClickListener;
	}

	public void addView(BaseAdapter gridAdapter) {
		View view = View.inflate(mcontext,
				R.layout.view_scrollgrid_expandgridview, null);
		final NoScrollView gv = (NoScrollView) view.findViewById(R.id.gridview);
		gv.setHorizontalSpacing(horizontalSpacing);// 设置gridView列间距
		gv.setVerticalSpacing(horizontalSpacing * 2);// 设置gridView行间距
		gv.setColumnWidth(gridItemWidth);// 设置gridView行宽度
		gv.setSelector(new ColorDrawable(Color.TRANSPARENT));
		gv.setNumColumns(GridView.AUTO_FIT);
		gv.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);// 设置gridView高度随宽度变化
		if (gridAdapter != null) {
			gv.setAdapter(gridAdapter);
		}
		gv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (itemClickListener != null) {
					itemClickListener.onItemClick(gv.getAdapter().getItem(
							position));
				}
			}
		});
		LayoutParams viewparams = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		view.setLayoutParams(viewparams);
		views.add(view);
		ImageView imageView = new ImageView(mcontext);
		if (indicators.size() == 0) {
			imageView.setImageResource(R.drawable.active);
		} else {
			imageView.setImageResource(R.drawable.inactive);
		}
		imageView.setPadding(6, 6, 6, 6);
		LayoutParams params = new LayoutParams(28, 28);
		imageView.setLayoutParams(params);
		indicatorLayout.addView(imageView);
		indicators.add(imageView);
	}

	/**
	 * 隐藏下面导航小点
	 */
	public void hideHint() {
		if (indicatorLayout != null) {
			indicatorLayout.setVisibility(GONE);
		}
	}

	/**
	 * 设置PagerAdapter
	 */
	public void setPagerAdapter() {
		KLog.i(TAG, "setPagerAdapter");
		if (facePagerAdapter == null) {
			facePagerAdapter = new ScrollGridPagerAdapter(views);
			facePager.setAdapter(facePagerAdapter);
		} else {
			facePagerAdapter.notifyDataSetChanged();
		}
		facePager.setOnPageChangeListener(this);
	}

	/**
	 * 获取行宽度
	 * 
	 * @return
	 */
	private int getGridItemWidth() {
		int gridItemWidth = 0;
		gridItemWidth = (int) ((screenWidthDip - (horizontalSpacing * (Horizontal_num - 1))
				* density) / Horizontal_num);
		Log.i(TAG, "间距==>" + horizontalSpacing);
		Log.i(TAG, "显示个数==>" + Horizontal_num);
		Log.i(TAG, "屏幕宽度==>" + screenWidthDip);
		Log.i(TAG, "gridView行宽度==>" + gridItemWidth);
		Log.i(TAG, "density==>" + density);
		return gridItemWidth;
	}

	public ScrollGridLayout(Context context) {
		super(context);
		initview(context);
	}

	public ScrollGridLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		initview(context);
	}

	public ScrollGridLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initview(context);
	}

	/**
	 * 每一项点击事件
	 * 
	 * @用途
	 * @author KANG
	 * @time 2015-9-24 下午4:14:00
	 * 
	 */
	public interface setOnItemClickListener {
		void onItemClick(Object object);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int arg0) {
		for (int i = 0; i < indicators.size(); i++) {
			if (arg0 != i) {
				indicators.get(i).setImageResource(R.drawable.inactive);
			} else {
				indicators.get(i).setImageResource(R.drawable.active);
			}

		}
	}
}
