package com.taotao.bean;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
@TableName("tb_order_shipping")
public class TbOrderShipping implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "TbOrderShipping [orderId=" + orderId + ", receiverName=" + receiverName + ", receiverPhone="
				+ receiverPhone + ", receiverMobile=" + receiverMobile + ", receiverState=" + receiverState
				+ ", receiverCity=" + receiverCity + ", receiverDistrict=" + receiverDistrict + ", receiverAddress="
				+ receiverAddress + ", receiverZip=" + receiverZip + ", created=" + created + ", updated=" + updated
				+ "]";
	}
@TableId(type=IdType.INPUT,value="order_id")
	private String orderId;
@TableField("receiver_name")
    private String receiverName;
@TableField("receiver_phone")
    private String receiverPhone;
@TableField("receiver_mobile")
    private String receiverMobile;
@TableField("receiver_state")
    private String receiverState;
@TableField("receiver_city")
    private String receiverCity;
@TableField("receiver_district")
    private String receiverDistrict;
@TableField("receiver_address")
    private String receiverAddress;
@TableField("receiver_zip")
    private String receiverZip;

    private Date created;

    private Date updated;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName == null ? null : receiverName.trim();
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone == null ? null : receiverPhone.trim();
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile == null ? null : receiverMobile.trim();
    }

    public String getReceiverState() {
        return receiverState;
    }

    public void setReceiverState(String receiverState) {
        this.receiverState = receiverState == null ? null : receiverState.trim();
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity == null ? null : receiverCity.trim();
    }

    public String getReceiverDistrict() {
        return receiverDistrict;
    }

    public void setReceiverDistrict(String receiverDistrict) {
        this.receiverDistrict = receiverDistrict == null ? null : receiverDistrict.trim();
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress == null ? null : receiverAddress.trim();
    }

    public String getReceiverZip() {
        return receiverZip;
    }

    public void setReceiverZip(String receiverZip) {
        this.receiverZip = receiverZip == null ? null : receiverZip.trim();
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
}