package com.bubula.notebook.key;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class KeyMoreAdpter extends BaseAdapter {

	List<KeyMoreMode> data;
	private int gridItemWidth;
	Context mcontext;

	public KeyMoreAdpter(List<KeyMoreMode> data, Context mcontext,
			int gridItemWidth) {
		super();
		this.data = data;
		this.mcontext = mcontext;
		this.gridItemWidth = gridItemWidth;

	}

	public void setData(List<KeyMoreMode> data) {
		this.data = data;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int arg0) {
		return data.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(final int arg0, View view, ViewGroup arg2) {
		ViewHolders holders = null;
//		if (view == null) {
//			view = LayoutInflater.from(mcontext).inflate(
//					R.layout.view_key_more_gridview_time, null);
//			holders = new ViewHolders();
//			holders.keyMoreButton = (ImageTextView) view
//					.findViewById(R.id.key_more_view);
//			holders.keyMoreButton.setWidth(gridItemWidth);
//			view.setTag(holders);
//		} else {
//			holders = (ViewHolders) view.getTag();
//		}
//		holders.keyMoreButton.setImageResource(data.get(arg0).getResID());
//		holders.keyMoreButton.setText(data.get(arg0).getText());
		return view;
	}

	class ViewHolders {
//		ImageTextView keyMoreButton;
	}

}
