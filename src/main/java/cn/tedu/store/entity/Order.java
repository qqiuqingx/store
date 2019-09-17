package cn.tedu.store.entity;

import java.util.Date;
/**
 * 订单表实体类
 * @author tarena
 *
 */
public class Order extends BaseEntity {
	
	private static final long serialVersionUID = 3606258040448034304L;
	private Integer oid  ;
	private Integer uid ;
	private String name ;
	private String  phone ;
	private String address ;
	private Integer status ;
	private Long price;
	private Date  orderTime;
	private Date payTime ;
	
	@Override
	public String toString() {
		return "Order [oid=" + oid + ", uid=" + uid + ", name=" + name + ", phone=" + phone + ", address=" + address
				+ ", status=" + status + ", price=" + price + ", orderTime=" + orderTime + ", payTime=" + payTime + "]";
	}
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	
}
