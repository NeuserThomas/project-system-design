package system_design.project.shop_service.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import system_design.project.shop_service.domain.Stock;
import system_design.project.shop_service.persistence.StockRepository;


@Service("StockService")
public class StockService {
	
	@Autowired
	public StockRepository stockRepo;
	@Autowired
	static final Logger logger = LoggerFactory.getLogger(StockService.class);
		
	/*
	 * Returns how much items are sold. If enough items are present, it returns the same amount, if less, it returns how many are left.
	 * If the product is not present, returns -1 if stock not present.
	 * Returns -2 if product is not present
	 */
	public long sellProduct(Long stockId, Long productId, long amount) {
		Optional<Stock> stock = stockRepo.findById(stockId);
		if(stock.isPresent()) {
			long result = stock.get().sellItem(productId, amount);
			if(result==amount) {
				return amount;
			} else if(result==1){
				return -2;
			} else {
				return result;
			}
		} else {
			return -1;
		}
	}
	
	public void checkRestock(long stockId, long productId) {
		Optional<Stock> stock = stockRepo.findById(stockId);
		if(stock.isPresent() && 
				(stock.get().getThresholdPerProduct().containsKey(productId) && stock.get().getAmountPerProduct().containsKey(productId))
				&& stock.get().getThresholdPerProduct().get(productId) > stock.get().getAmountPerProduct().get(productId)) {
			logger.warn("Restock "+productId);
		}
	}
	
	public void changeProductTreshold(long stockId, long productId,long threshold) {
		Optional<Stock> stock = stockRepo.findById(stockId);
		if(stock.isPresent()) {
			stock.get().getThresholdPerProduct().put(productId, threshold);
		}
	}
	
	public void restockProduct(long stockId, long productId,long amount) {
		Optional<Stock> stock = stockRepo.findById(stockId);
		if(stock.isPresent()) {
			long inStock = stock.get().getAmountPerProduct().get(productId);
			stock.get().getAmountPerProduct().put(productId, inStock+amount);
		}
	}
}
