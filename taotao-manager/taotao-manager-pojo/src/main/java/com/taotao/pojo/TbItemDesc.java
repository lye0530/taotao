package com.taotao.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品描述表对应实体
 * @author MLOONG
 *
 */
public class TbItemDesc implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3369180805974144058L;
	//商品ID
	private Long itemId;
	//商品描述
    private Date created;
    //创建时间
    private Date updated;
    //更新时间
    private String itemDesc;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc == null ? null : itemDesc.trim();
    }
}