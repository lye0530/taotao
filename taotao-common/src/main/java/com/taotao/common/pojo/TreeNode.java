package com.taotao.common.pojo;

import java.io.Serializable;

/**
 *商品类目选择传输实体，因为前段使用的EasyUI的tree控件展示。该tree控件要求返回的json数据格式必须包含id，text，state三个字段
 * @author MLOONG
 *
 */
public class TreeNode implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6857886143492190373L;

	private long id;
	
	private String text;
	
	private String state;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
