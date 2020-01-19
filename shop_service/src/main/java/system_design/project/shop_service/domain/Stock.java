package system_design.project.shop_service.domain;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
@Entity
/**
 * Class that holds the stock.
 * @author robin
 *
 */
public class Stock {
	@Id
	@GeneratedValue
	private long id;
	
	/*
	 * Maps the stock to a certain cinema
	 */
	@Column(unique=true)
	private String name;
	
	/**
	 * How many products there are in stock
	 */
	@ElementCollection
    @MapKeyColumn(name="productId")
	private Map<Long,Long> amountPerProduct;
	
	/**
	 * How many products there are in stock
	 */
	@ElementCollection
    @MapKeyColumn(name="productId")
	private Map<Long,Long> thresholdPerProduct;
	
	public Stock() {
		amountPerProduct=new HashMap<Long,Long>();
		thresholdPerProduct=new HashMap<Long,Long>();
	}
		
	
	public void addProduct(ShopItem s,long amount, long threshold) {
		thresholdPerProduct.put(s.getId(),threshold);
		amountPerProduct.put(s.getId(), amount);
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Map<Long, Long> getAmountPerProduct() {
		return amountPerProduct;
	}

	public void setAmountPerProduct(Map<Long, Long> amountPerProduct) {
		this.amountPerProduct = amountPerProduct;
	}

	public Map<Long,Long> getThresholdPerProduct() {
		return thresholdPerProduct;
	}

	public void setThresholdPerProduct(Map<Long,Long> thresholdPerProduct) {
		this.thresholdPerProduct = thresholdPerProduct;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
