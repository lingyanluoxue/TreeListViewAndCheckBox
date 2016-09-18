package com.lylx.treelistviewandcheckbox.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lylx.treelistviewandcheckbox.R;
import com.lylx.treelistviewandcheckbox.tree.Node;

public class SimpleTreeAdapter<T> extends TreeListViewAdapter<T> {

	private Map<Integer, Node> selectMaps = new HashMap<Integer, Node>();
	private Map<Integer, Node> leafMaps = new HashMap<Integer, Node>();
	private boolean isParent;

	public SimpleTreeAdapter(ListView mTree, Context context, List<T> datas,
			int defaultExpandLevel) throws IllegalArgumentException,
			IllegalAccessException {
		super(mTree, context, datas, defaultExpandLevel);
	}

	public interface OnItemChangeListener {

		void onCheckChange();

	}

	OnItemChangeListener listener = null;

	public void setOnItemChangeListener(OnItemChangeListener l) {
		listener = l;
	}

	@Override
	public View getConvertView(final Node node, final int position,
							   View convertView, ViewGroup parent) {

		final ViewHolder viewHolder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_item, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.icon = (ImageView) convertView
					.findViewById(R.id.id_treenode_icon);
			viewHolder.label = (TextView) convertView
					.findViewById(R.id.id_treenode_label);
			viewHolder.check = (CheckBox) convertView
					.findViewById(R.id.id_treenode_checkbox);
			convertView.setTag(viewHolder);

		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		if (node.getIcon() == -1) {
			viewHolder.icon.setVisibility(View.INVISIBLE);
		} else {
			viewHolder.icon.setVisibility(View.VISIBLE);
			viewHolder.icon.setImageResource(node.getIcon());
		}

		viewHolder.label.setText(node.getName());

		viewHolder.check.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (viewHolder.check.isChecked()) {
					setChildrenNodeChecked(node);
					setLeafNodeChecked(node);
					setParentNodeChecked(node);
//					setRootNodeChecked(node);
				} else {
//					if (!isParent) {
						setChildrenNodeRemove(node);
//					}
					setLeafNodeRemove(node);
					setParentNodeRemove(node);

				}

				listener.onCheckChange();
				notifyDataSetChanged();

			}

		});

		viewHolder.check
				.setChecked(selectMaps.get(node.getId()) == null ? false : true);

		return convertView;
	}

	private void setRootNodeChecked(Node node) {

		if (node.isRoot()) {
			isParent = false;
			selectMaps.put(node.getId(), node);
			for (Node n : node.getChildren()) {
				setChildrenNodeChecked(n);
			}
		}
	}

	private void setParentNodeChecked(final Node node) {
		if (!node.isRoot()) {
			Node parentNode = node.getParent();
			boolean isAllChecked = true;
			for (Node n : parentNode.getChildren()) {
				if (selectMaps.get(n.getId()) == null) {
					isAllChecked = false;
					break;
				}
			}
			if (isAllChecked) {
				selectMaps.put(parentNode.getId(), parentNode);
			}
			setParentNodeChecked(parentNode);
		}
	}

	private void setParentNodeRemove(final Node node) {

		if (!node.isRoot()) {
			isParent = false;
			Node parentNode = node.getParent();
			for (Node n : parentNode.getChildren()) {
				if (selectMaps.get(n.getId()) == null) {
					isParent = true;
					selectMaps.remove(parentNode.getId());
					break;
				}
			}

			setParentNodeRemove(parentNode);
		}

	}

	private void setChildrenNodeChecked(final Node node) {
		if (!node.isLeaf()) {
			for (Node n : node.getChildren()) {
				selectMaps.put(node.getId(), node);
				selectMaps.put(n.getId(), n);
				if (n.isLeaf()) {
					leafMaps.put(n.getId(), n);
				}
				setChildrenNodeChecked(n);
			}
		}
	}

	private void setChildrenNodeRemove(Node node) {
		if (!node.isLeaf()) {
			// isParent = false;
			for (Node n : node.getChildren()) {
				selectMaps.remove(node.getId());
				selectMaps.remove(n.getId());
				if (n.isLeaf()) {
					leafMaps.remove(n.getId());
				}
				setChildrenNodeRemove(n);
			}
		}
	}

	private void setLeafNodeChecked(final Node node) {
		if (node.isLeaf()) {
			selectMaps.put(node.getId(), node);
			leafMaps.put(node.getId(), node);
		}
	}

	private void setLeafNodeRemove(Node node) {
		if (node.isLeaf()) {
			isParent = false;
			selectMaps.remove(node.getId());
			leafMaps.remove(node.getId());
		}
	}

	public int getCheckDataSize() {
		return leafMaps.size();
	}

	private final class ViewHolder {
		ImageView icon;
		TextView label;
		CheckBox check;
	}

}
