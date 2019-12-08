package system_design.project.shop_service.domain;

import java.util.HashMap;
import java.util.Map;

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
	private long cinemaId;
	
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
	
	/*
	 * Returns how much items are sold. If enough items are present, it returns the same amount, if less, it returns how many are left.
	 * If the product is not present, returns -1.
	 */
	public long sellItem(Long productId, long amount) {
		if(amountPerProduct.containsKey(productId)) {
			long amountLeft = amountPerProduct.get(productId);
			if(amountLeft>=amount) {
				amountPerProduct.replace(productId, amountLeft-amount);
				return amount;
			} else {
				return amountLeft;
			}
		} else {
			return -1;
		}
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCinemaId() {
		return cinemaId;
	}

	public void setCinemaId(long cinemaId) {
		this.cinemaId = cinemaId;
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
}
