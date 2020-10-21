package springshop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	
	@Column(length=50, nullable=false)
	private String name;
	
	@Column(length=50, nullable=false)
	private Integer price;
	
	@Column(length=50, nullable=false)
	private Integer stock;
		
	public long getId() {
		return Id;
	}
	public String getName() {
		return name;
	}
	public Integer getPrice() {
		return price;
	}
	public Integer getStock() {
		return stock;
	}
	public void setId(long id) {
		this.Id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
}
