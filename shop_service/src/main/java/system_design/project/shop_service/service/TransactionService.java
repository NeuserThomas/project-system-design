package system_design.project.shop_service.service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import system_design.project.shop_service.adapters.payment.IPaymentAdapter;
import system_design.project.shop_service.domain.Stock;
import system_design.project.shop_service.domain.Transaction;
import system_design.project.shop_service.persistence.TransactionRepository;

@Service("TransactionService")
@EnableAsync
public class TransactionService {

	@Autowired
	public TransactionRepository transactionRepo;
	@Autowired
	private StockService stockService;

	@Autowired
	private IPaymentAdapter paymentAdapter;
	
	final Logger logger = LoggerFactory.getLogger(TransactionService.class);

	/*
	 * Returns how much items are sold. If enough items are present, it returns the
	 * same amount, if less, it returns how many are left. If the product is not
	 * present, returns -1 if transaction not present. Returns -2 if product is not
	 * present
	 */
	public long sell(Transaction transaction) {
		if (canSell(transaction)) {
			Optional<Stock> stock = stockService.stockRepo.findById(transaction.getStockId());
			if (stock.isPresent()) {
				for (long productId : transaction.getSoldItems().keySet()) {
					stockService.sellProduct(stock.get(), productId, transaction.getSoldItems().get(productId));
				}
				return transactionRepo.save(transaction).getId();
			}
		}
		return -1L;
	}

	public void unSell(Transaction transaction) {
		Optional<Stock> stock = stockService.stockRepo.findById(transaction.getStockId());
		if (stock.isPresent()) {
			for (long productId : transaction.getSoldItems().keySet()) {
				stockService.unSell(stock.get(), productId, transaction.getSoldItems().get(productId));
			}
		}
	}
	
	public boolean canSell(Transaction transaction) {
		boolean possible = false;
		Optional<Stock> stock = stockService.stockRepo.findById(transaction.getStockId());
		if (stock.isPresent()) {
			possible = true;
			if (transaction.getSoldItems().size() > 0) {
				for (long productId : transaction.getSoldItems().keySet()) {
					long amount = stockService.canSellProduct(stock.get().getId(), productId,
							transaction.getSoldItems().get(productId));
					if (amount != transaction.getSoldItems().get(productId)) {
						possible = false;
					}
				}
			}
		}
		return possible;
	}
	
	@Async
	public CompletableFuture<Boolean> TryAndSell(Transaction transaction) {
		logger.info("Checking transaction");
		try {
			Boolean canPay = paymentAdapter.canPay().get();
			Boolean inStock = canSell(transaction);
			if(canPay && inStock) {
				long id = sell(transaction);
				if(id>-1L) {
					logger.info("Selling: "+id);
					Boolean payDone = paymentAdapter.pay().get();
					logger.info("Sold: "+id);
					return CompletableFuture.completedFuture(true);
				}
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return CompletableFuture.completedFuture(false);
	}	
}
