package com.lylx.treelistviewandcheckbox.entity;


import com.lylx.treelistviewandcheckbox.tree.TreeNodeId;
import com.lylx.treelistviewandcheckbox.tree.TreeNodeLabel;
import com.lylx.treelistviewandcheckbox.tree.TreeNodePid;

public class FileBean
{
	@TreeNodeId
	private int _id;
	@TreeNodePid
	private int parentId;
	@TreeNodeLabel
	private String name;
	private long length;
	private String desc;

	public FileBean(int _id, int parentId, String name)
	{
		super();
		this._id = _id;
		this.parentId = parentId;
		this.name = name;
	}

}
