package springshop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long number;
	
	@Column(length=50, nullable=false)
	private String name;
	
	@Column(length=50, nullable=false)
	private Integer price;
	
	@Column(length=50, nullable=false)
	private String userName;

	public long getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	public Integer getPrice() {
		return price;
	}

	public String getUserName() {
		return userName;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String toString() {
		return getName();
	}

}
