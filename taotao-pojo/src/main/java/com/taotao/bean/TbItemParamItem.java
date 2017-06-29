package com.taotao.bean;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
@TableName("tb_item_param_item")
public class TbItemParamItem implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "TbItemParamItem [id=" + id + ", itemId=" + itemId + ", created=" + created + ", updated=" + updated
				+ ", paramData=" + paramData + "]";
	}
@TableId(type=IdType.INPUT)
	private Long id;
@TableField("item_id")
    private Long itemId;

    private Date created;
@TableField("param_data")
    private Date updated;

    private String paramData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getParamData() {
        return paramData;
    }

    public void setParamData(String paramData) {
        this.paramData = paramData == null ? null : paramData.trim();
    }
}