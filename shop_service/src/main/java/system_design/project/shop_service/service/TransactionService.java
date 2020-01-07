package system_design.project.shop_service.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import system_design.project.shop_service.domain.Stock;
import system_design.project.shop_service.domain.Transaction;
import system_design.project.shop_service.persistence.TransactionRepository;


@Service("TransactionService")
public class TransactionService {
	
	@Autowired
	public TransactionRepository transactionRepo;
	@Autowired
	private StockService stockService;
	
	final Logger logger = LoggerFactory.getLogger(TransactionService.class);
		
	/*
	 * Returns how much items are sold. If enough items are present, it returns the same amount, if less, it returns how many are left.
	 * If the product is not present, returns -1 if transaction not present.
	 * Returns -2 if product is not present
	 */
	public synchronized boolean viableTransaction(Transaction transaction) {
		Optional<Stock> stock = stockService.stockRepo.findById(transaction.getStockId());
		if(stock.isPresent()) {
			boolean possible = true;
			if(transaction.getSoldItems().size()>0) {
				for(long productId: transaction.getSoldItems().keySet()) {
					long amount = stockService.canSellProduct(stock.get().getId(), productId, transaction.getSoldItems().get(productId));
					if(amount != transaction.getSoldItems().get(productId)) {
						possible=false;
					}
				}
				if(possible) {
					for(long productId: transaction.getSoldItems().keySet()) {
						stockService.sellProduct(stock.get(), productId, transaction.getSoldItems().get(productId));
					}
					transactionRepo.save(transaction);
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	
	
}
