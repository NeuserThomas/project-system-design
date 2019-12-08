package system_design.project.shop_service.adapters.rest;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import system_design.project.shop_service.domain.ShopItem;
import system_design.project.shop_service.domain.Stock;
import system_design.project.shop_service.persistence.ShopItemRepository;
import system_design.project.shop_service.persistence.StockRepository;

@RequestMapping("stock")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StockRestController {

	@Autowired
	private StockRepository stockRepo;
	@Autowired
	private ShopItemRepository shopRepo;
	
	final Logger logger = LoggerFactory.getLogger(StockRestController.class);
	
	@GetMapping
	public @ResponseBody ResponseEntity<List<Stock>> getStocks() {
		List<Stock> stocks = stockRepo.findAll();
		return new ResponseEntity<List<Stock>>(stocks,HttpStatus.OK);

	}
	
	@GetMapping("/{stockId}")
	public @ResponseBody ResponseEntity<Stock> getStockById(@PathVariable long stockId) {
		Optional<Stock> stock = stockRepo.findById(stockId);
		if(stock.isPresent()) {
			return new ResponseEntity<Stock>(stock.get(),HttpStatus.OK);
		} else {
			return new ResponseEntity<Stock>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(consumes="application/json")
	public ResponseEntity<Stock> postStock(@RequestBody Stock stock) {
		stockRepo.save(stock);
		logger.info("Call: postStock");
		return new ResponseEntity<Stock>(HttpStatus.ACCEPTED);
	}
	
	//TODO: add calls to change treshhold.
	/**
	 * Method for testing
	 * @return
	 */
	@GetMapping(path="/dummydata")
	public @ResponseBody ResponseEntity<Stock> dummydata() {
		Stock stock = new Stock();
		stock.setCinemaId(1L);
		Map<Long, Long> a = stock.getAmountPerProduct();
		Map<Long, Long> b = stock.getThresholdPerProduct();
		List<ShopItem> products = shopRepo.findAll();
		for(ShopItem item: products) {
			a.put(item.getId(),500L);
			b.put(item.getId(),150L);
		}
		stock.setAmountPerProduct(a);
		stock.setThresholdPerProduct(b);
		stockRepo.save(stock);
		return new ResponseEntity<Stock>(stock,HttpStatus.OK);
	}
}
