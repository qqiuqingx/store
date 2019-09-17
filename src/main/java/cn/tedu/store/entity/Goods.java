package cn.tedu.store.entity;
/**
 * 	商品数据的实体类
 * @author tarena
 *
 */
public class Goods extends BaseEntity {

	private static final long serialVersionUID = -5291001218643303391L;
    
	private Long id;
	private Long categorpId;
	private String itemType;
	private String title;
	private String sellpPoint;
	private Long price;
	private Integer num;
	private String barcode;
	private String image;
	private Integer status;
	private Integer priority;
	
	@Override
	public String toString() {
		return "Goods [id=" + id + ", categorpId=" + categorpId + ", itemType=" + itemType + ", title=" + title
				+ ", sellpPoint=" + sellpPoint + ", price=" + price + ", num=" + num + ", barcode=" + barcode
				+ ", image=" + image + ", status=" + status + ", priority=" + priority + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCategorpId() {
		return categorpId;
	}

	public void setCategorpId(Long categorpId) {
		this.categorpId = categorpId;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSellpPoint() {
		return sellpPoint;
	}

	public void setSellpPoint(String sellpPoint) {
		this.sellpPoint = sellpPoint;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
