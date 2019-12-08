package system_design.project.shop_service.adapters.rest;

import java.util.List;
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
import system_design.project.shop_service.persistence.ShopItemRepository;


@RequestMapping("shopItem")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ShopItemRestController {

	@Autowired
	private ShopItemRepository shopItemRepo;
	
	final Logger logger = LoggerFactory.getLogger(ShopItemRestController.class);
	
	@GetMapping
	public @ResponseBody ResponseEntity<List<ShopItem>> getShopItems() {
		List<ShopItem> shopItems = shopItemRepo.findAll();
		return new ResponseEntity<List<ShopItem>>(shopItems,HttpStatus.OK);

	}
	
	@GetMapping("/{shopItemId}")
	public @ResponseBody ResponseEntity<ShopItem> getShopItemById(@PathVariable long shopItemId) {
		Optional<ShopItem> shopItem = shopItemRepo.findById(shopItemId);
		if(shopItem.isPresent()) {
			return new ResponseEntity<ShopItem>(shopItem.get(),HttpStatus.OK);
		} else {
			return new ResponseEntity<ShopItem>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(consumes="application/json")
	public ResponseEntity<ShopItem> postShopItem(@RequestBody ShopItem shopItem) {
		shopItemRepo.save(shopItem);
		logger.info("Call: postShopItem");
		return new ResponseEntity<ShopItem>(HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/dummydata")
	public @ResponseBody ResponseEntity<ShopItem> dummyData() {
		ShopItem a = new ShopItem();
		a.setDescription("Water: Be a HydroHomie!");
		a.setPrice(1);
		ShopItem b = new ShopItem();
		b.setDescription("Coca Cola: Live the american dream!");
		b.setPrice(2);
		ShopItem c = new ShopItem();
		c.setDescription("Popcorn: Be a bit corny!");
		c.setPrice(5);
		ShopItem d = new ShopItem();
		d.setDescription("Doritos: Be the best version of yourself!");
		d.setPrice(2.5);
		shopItemRepo.save(a);
		shopItemRepo.save(b);
		shopItemRepo.save(c);
		shopItemRepo.save(d);
		return new ResponseEntity<ShopItem>(HttpStatus.OK);
	}
	
	
	
}
