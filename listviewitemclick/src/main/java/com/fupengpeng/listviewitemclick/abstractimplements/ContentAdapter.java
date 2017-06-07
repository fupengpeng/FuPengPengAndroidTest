package com.fupengpeng.listviewitemclick.abstractimplements;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.fupengpeng.listviewitemclick.R;

public class ContentAdapter extends BaseAdapter {

	private static final String TAG = "ContentAdapter";
	private List<String> mContentList;
	private LayoutInflater mInflater;
	private IClick mListener;

	public ContentAdapter(Context context, List<String> contentList,
			IClick listener) {
		mContentList = contentList;
		mInflater = LayoutInflater.from(context);
		mListener = listener;
	}

	@Override
	public int getCount() {
		Log.i(TAG, "getCount");
		return mContentList.size();
	}

	@Override
	public Object getItem(int position) {
		Log.i(TAG, "getItem");
		return mContentList.get(position);
	}

	@Override
	public long getItemId(int position) {
		Log.i(TAG, "getItemId");
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.i(TAG, "getView");
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_item, null);
			holder = new ViewHolder();
			holder.textView = (TextView) convertView
					.findViewById(R.id.textView1);
			holder.button = (Button) convertView.findViewById(R.id.button1);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.textView.setText(mContentList.get(position));

		holder.button.setOnClickListener(mListener);
		holder.button.setTag(position);
		return convertView;
	}

	public class ViewHolder {
		public TextView textView;
		public Button button;
	}

}