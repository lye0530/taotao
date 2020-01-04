package com.taotao.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 因为EasyUI的datagrid控件要求数据格式为：{total："",rows:[]},所以需要我们封装定义一个返回对象用于数据网格渲染数据,
 * 否则，就需要自己手动组装
 * @author MLOONG
 * @param <T>
 * @param <T>
 */
public class DataResult<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1373780087438241599L;

	private long total;
	
	private List<T> rows;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}
