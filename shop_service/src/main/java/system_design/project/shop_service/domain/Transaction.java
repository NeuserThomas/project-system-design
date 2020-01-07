package system_design.project.shop_service.domain;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;

@Entity
public class Transaction {
	@Id
	@GeneratedValue
	long id;
	
    @ElementCollection
    @MapKeyColumn(name="productId")
	private Map<Long,Long> soldItems;
	
	private LocalDateTime date;
	
	//In real life: Need register info

	@Column(nullable=false)
	private long stockId;

	

	public Transaction() {
		soldItems=new HashMap<Long,Long>();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Map<Long, Long> getSoldItems() {
		return soldItems;
	}

	public void setSoldItems(Map<Long, Long> soldItems) {
		this.soldItems = soldItems;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	public long getStockId() {
		return stockId;
	}

	public void setStockId(long stockId) {
		this.stockId = stockId;
	}
}
