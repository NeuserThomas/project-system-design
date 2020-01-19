package system_design.project.shop_service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import system_design.project.shop_service.domain.ShopItem;
import system_design.project.shop_service.domain.Stock;
import system_design.project.shop_service.domain.Transaction;
import system_design.project.shop_service.persistence.ShopItemRepository;
import system_design.project.shop_service.persistence.StockRepository;
import system_design.project.shop_service.persistence.TransactionRepository;
import system_design.project.shop_service.service.TransactionService;

@SpringBootApplication
public class ShopServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopServiceApplication.class, args);
	}
	
	@Bean
	/**
	 * Method that removes unpaid transactions (When the application crashed)
	 * @param transRepo
	 * @param transService
	 * @return
	 */
	CommandLineRunner checkRepo(TransactionRepository transRepo, TransactionService transService) {
		return (args)->{
			final Logger logger = LoggerFactory.getLogger(ShopServiceApplication.class);
			logger.info("Checking for unpaid transactions!");
			List<Transaction> trs = transRepo.findByUnPaidTransactions();
			for(Transaction t : trs) {
				logger.warn("Removing old transaction:"+t.getId());
				transService.unSell(t);
				transRepo.delete(t);
			}
		};
	}
	
	@Bean
	CommandLineRunner testRepository(ShopItemRepository shopRepo, StockRepository stockRepo) {
		return (args)->{
			if(shopRepo.count()==0) {
				ShopItem a = new ShopItem();
				a.setName("Water");
				a.setDescription("Water: Be a HydroHomie!");
				a.setPrice(1);
				ShopItem b = new ShopItem();
				b.setName("Coca Cola");
				b.setDescription("Coca Cola: Live the american dream!");
				b.setPrice(2);
				ShopItem c = new ShopItem();
				c.setDescription("Popcorn: Be a bit corny!");
				c.setName("Popcorn sugar medium");
				c.setPrice(5);
				ShopItem d = new ShopItem();
				d.setName("Doritos Sweet Chili 200g");
				d.setDescription("Doritos: Be the best version of yourself!");
				d.setPrice(2.5);
				shopRepo.save(a);
				shopRepo.save(b);
				shopRepo.save(c);
				shopRepo.save(d);
				if(stockRepo.count()==0) {
					Stock stock= new Stock();
					/*
					 * Low for testing purposes.
					 */
					stock.setName("Cinema Init");
					stock.addProduct(a, 500, 100);
					stock.addProduct(b, 500, 100);
					stock.addProduct(c, 500, 100);
					stock.addProduct(d, 500, 100);
					stockRepo.save(stock);
				}
			}
		};
	}
}
