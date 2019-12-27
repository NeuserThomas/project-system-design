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
	
	final Logger logger = LoggerFactory.getLogger(StockRestController.class);
	
	@GetMapping
	public @ResponseBody ResponseEntity<List<Stock>> getStocks() {
		List<Stock> stocks = stockRepo.findAll();
		return new ResponseEntity<List<Stock>>(stocks,HttpStatus.OK);
	}
	
	@GetMapping("/getStockId/{stockId}")
	public @ResponseBody ResponseEntity<Stock> getStockById(@PathVariable long stockId) {
		Optional<Stock> stock = stockRepo.findById(stockId);
		if(stock.isPresent()) {
			return new ResponseEntity<Stock>(stock.get(),HttpStatus.OK);
		} else {
			return new ResponseEntity<Stock>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/names")
	public @ResponseBody ResponseEntity<List<String>> getNames() {
		List<String> names = stockRepo.findAllNames();
		if(!names.isEmpty()) {
			return new ResponseEntity<List<String>>(names,HttpStatus.OK);
		} else {
			return new ResponseEntity<List<String>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getByName/{name}")
	public @ResponseBody ResponseEntity<Stock> getStockById(@PathVariable String name) {
		Optional<Stock> stock = stockRepo.findStockByName(name);
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
}
