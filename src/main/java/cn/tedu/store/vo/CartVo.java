package cn.tedu.store.vo;

import java.io.Serializable;

public class CartVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8286453578719452818L;
	private Integer uid;
	private Integer cid;
	private  Long gid;
	private Integer num;
	private String title;
	private String image;
	private Long price;
	
	@Override
	public String toString() {
		return "CartVo [uid=" + uid + ", cid=" + cid + ", gid=" + gid + ", num=" + num + ", title=" + title + ", image="
				+ image + ", price=" + price + "]";
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Long getGid() {
		return gid;
	}
	public void setGid(Long gid) {
		this.gid = gid;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
