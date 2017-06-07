package com.fupengpeng.listviewitemclick.abstractimplements;

import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.fupengpeng.listviewitemclick.ListViewItemClickActivity;
import com.fupengpeng.listviewitemclick.R;

public class MainActivity extends Activity implements OnItemClickListener {

	// 模拟listview中加载的数据
	private static final String[] CONTENTS = { "北京", "上海", "广州", "深圳", "苏州",
			"南京", "武汉", "长沙", "杭州" };
	private List<String> contentList;
	private ListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	private void init() {
		mListView = (ListView) findViewById(R.id.listview);
		contentList = new ArrayList<String>();
		for (int i = 0; i < CONTENTS.length; i++) {
			contentList.add(CONTENTS[i]);
		}
		// 实例化ContentAdapter类，并传入实现类
		mListView.setAdapter(new ContentAdapter(this, contentList, mListener));

		mListView.setOnItemClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// 响应item点击事件
	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
		Toast.makeText(this, "listview的item被点击了！，点击的位置是-->" + position,
				Toast.LENGTH_SHORT).show();
	}

	/**
	 * 实现类，响应按钮点击事件
	 */
	private IClick mListener = new IClick() {
		@Override
		public void listViewItemClick(int position, View v) {
			switch (v.getId()){
				case R.id.button1:
					Toast.makeText(
							MainActivity.this,
							"listview的内部的评论按钮被点击了！，位置是-->" + (Integer) v.getTag()
									+ ",内容是-->" + contentList.get((Integer) v.getTag()),
							Toast.LENGTH_SHORT).show();
					break;
				case R.id.button2:
					Toast.makeText(
							MainActivity.this,
							"listview的内部的分享按钮被点击了！，位置是-->" + (Integer) v.getTag()
									+ ",内容是-->" + contentList.get((Integer) v.getTag()),
							Toast.LENGTH_SHORT).show();
					break;
			}

		}
	};
}