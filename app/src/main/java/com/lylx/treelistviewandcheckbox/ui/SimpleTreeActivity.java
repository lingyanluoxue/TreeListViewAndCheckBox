package com.lylx.treelistviewandcheckbox.ui;



import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.lylx.treelistviewandcheckbox.R;
import com.lylx.treelistviewandcheckbox.adapter.SimpleTreeAdapter;
import com.lylx.treelistviewandcheckbox.entity.FileBean;

import java.util.ArrayList;
import java.util.List;


public class SimpleTreeActivity extends Activity {
	private List<FileBean> mDatas2 = new ArrayList<FileBean>();
	private ListView mTree;
	private Button mBtn;
	private SimpleTreeAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_tree);

		initDatas();
		mTree = (ListView) findViewById(R.id.id_tree);
		mBtn = (Button) findViewById(R.id.id_btn);
		try {
			mAdapter = new SimpleTreeAdapter<FileBean>(mTree, this, mDatas2, 10);
			
			
/*			mAdapter.setOnTreeNodeClickListener(new OnTreeNodeClickListener() {
				@Override
				public void onClick(final Node node, int position, View v) {
					if (node.isLeaf()) {
						Toast.makeText(getApplicationContext(), node.getName(),
								Toast.LENGTH_SHORT).show();
					}
				}


			});*/

		} catch (Exception e) {
			e.printStackTrace();
		}
		mTree.setAdapter(mAdapter);
		
		mAdapter.setOnItemChangeListener(new SimpleTreeAdapter.OnItemChangeListener() {
		
			@Override
			public void onCheckChange() {
				mBtn.setText("数量："+mAdapter.getCheckDataSize());
			}
		});
	}

	private void initDatas() {

		mDatas2.add(new FileBean(1, 0, "文件管理系统"));
		mDatas2.add(new FileBean(2, 1, "游戏"));
		mDatas2.add(new FileBean(3, 1, "文档"));
		mDatas2.add(new FileBean(4, 1, "程序"));
		mDatas2.add(new FileBean(5, 1, "应用"));
		
		mDatas2.add(new FileBean(6, 2, "war3"));
		mDatas2.add(new FileBean(7, 2, "刀塔传奇"));
		mDatas2.add(new FileBean(8, 2, "我的世界"));

		mDatas2.add(new FileBean(9, 4, "面向对象"));
		mDatas2.add(new FileBean(10, 4, "非面向对象"));
		
		mDatas2.add(new FileBean(11, 5, "影音视听"));
		mDatas2.add(new FileBean(12, 5, "聊天社交"));
		mDatas2.add(new FileBean(13, 5, "时尚购物"));
		mDatas2.add(new FileBean(14, 5, "效率办公"));

		mDatas2.add(new FileBean(15, 9, "C++"));
		mDatas2.add(new FileBean(16, 9, "JAVA"));
		mDatas2.add(new FileBean(17, 9, "Javascript"));
		mDatas2.add(new FileBean(18, 10, "C"));
		
		mDatas2.add(new FileBean(19, 11, "网易云音乐"));
		mDatas2.add(new FileBean(20, 11, "爱奇艺"));
		mDatas2.add(new FileBean(21, 11, "优酷"));
		mDatas2.add(new FileBean(22, 11, "唱吧"));
		mDatas2.add(new FileBean(23, 12, "QQ"));
		mDatas2.add(new FileBean(24, 12, "微信"));
		mDatas2.add(new FileBean(25, 13, "京东"));
		mDatas2.add(new FileBean(26, 13, "天猫"));
		mDatas2.add(new FileBean(27, 14, "WPS"));
		mDatas2.add(new FileBean(28, 14, "印象笔记"));
		mDatas2.add(new FileBean(29, 14, "邮箱"));
		mDatas2.add(new FileBean(30, 14, "钉钉"));

	}

}
