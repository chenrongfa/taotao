/**
 * 
 */
package com.taotao.bean;

/**
 * description :TODO createTime:2017年6月17日 下午12:22:35 project:ssm
 * 
 * @author chenrongfa QQ:952786280 company:逸臣
 * @version:
 */

public class BIgAdvertiment {

	@Override
	public String toString() {
		return "BIgAdvertiment [alt=" + alt + ", href=" + href + ", srcB=" + srcB + ", src=" + src + ", height="
				+ height + ", heightB=" + heightB + ", width=" + width + ", widthB=" + widthB + "]";
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getSrcB() {
		return srcB;
	}

	public void setSrcB(String srcB) {
		this.srcB = srcB;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public long getHeight() {
		return height;
	}

	public void setHeight(long height) {
		this.height = height;
	}

	public long getHeightB() {
		return heightB;
	}

	public void setHeightB(long heightB) {
		this.heightB = heightB;
	}

	public long getWidth() {
		return width;
	}

	public void setWidth(long width) {
		this.width = width;
	}

	public long getWidthB() {
		return widthB;
	}

	public void setWidthB(long widthB) {
		this.widthB = widthB;
	}

	private String alt;
	private String href;
	private String srcB;
	private String src;
	private long height;
	private long heightB;
	private long width;
	private long widthB;

}
