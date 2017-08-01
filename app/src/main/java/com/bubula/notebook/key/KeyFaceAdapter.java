package com.bubula.notebook.key;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bubula.notebook.R;

import java.util.List;


public class KeyFaceAdapter extends ArrayAdapter<String> {
	private int gridItemWidth;

	public KeyFaceAdapter(Context context, int textViewResourceId,
			List<String> objects, int gridItemWidth) {
		super(context, textViewResourceId, objects);
		this.gridItemWidth = gridItemWidth;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = View.inflate(getContext(),
					R.layout.view_key_face_gridview_item, null);
		}

		ImageView imageView = (ImageView) convertView
				.findViewById(R.id.iv_face);

		LayoutParams params = imageView.getLayoutParams();
		params.width = gridItemWidth;
		params.height = gridItemWidth;
		imageView.setLayoutParams(params);
		String filename = getItem(position);
		int resId = getContext().getResources().getIdentifier(filename,
				"drawable", getContext().getPackageName());
		imageView.setImageResource(resId);

		return convertView;
	}

}
