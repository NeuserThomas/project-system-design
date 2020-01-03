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
import system_design.project.shop_service.domain.Transaction;
import system_design.project.shop_service.persistence.TransactionRepository;
import system_design.project.shop_service.service.TransactionService;

@RequestMapping("transaction")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TransactionRestController {

	@Autowired
	private TransactionRepository transactionRepo;
	@Autowired
	private TransactionService transactionService;

	final Logger logger = LoggerFactory.getLogger(TransactionRestController.class);

	@GetMapping
	public @ResponseBody ResponseEntity<List<Transaction>> getTransactions() {
		List<Transaction> transactions = transactionRepo.findAll();
		return new ResponseEntity<List<Transaction>>(transactions, HttpStatus.OK);
	}

	@GetMapping("/getNewTransaction")
	public @ResponseBody ResponseEntity<Transaction> getTransaction() {
		return new ResponseEntity<Transaction>(new Transaction(), HttpStatus.OK);
	}

	@GetMapping("/{transactionId}")
	public @ResponseBody ResponseEntity<Transaction> getTransactionById(@PathVariable long transactionId) {
		Optional<Transaction> transaction = transactionRepo.findById(transactionId);
		if (transaction.isPresent()) {
			return new ResponseEntity<Transaction>(transaction.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Transaction>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * The method when selling items. Since each transaction can have multiple
	 * items.
	 * 
	 * @param transaction
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Transaction> postTransaction(@RequestBody Transaction transaction) {
		logger.info("postTransaction!");
		if (transactionService.viableTransaction(transaction)) {
			return new ResponseEntity<Transaction>(HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<Transaction>(HttpStatus.CONFLICT);
		}
	}
}
