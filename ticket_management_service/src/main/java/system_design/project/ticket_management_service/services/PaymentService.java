package system_design.project.ticket_management_service.services;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import system_design.project.ticket_management_service.adapters.payment.IPaymentAdapter;
import system_design.project.ticket_management_service.domain.Ticket;
import system_design.project.ticket_management_service.persistence.TicketRepository;

@Service("PaymentService")
@EnableAsync
public class PaymentService {
	
	final Logger logger = LoggerFactory.getLogger(PaymentService.class);
	
	@Autowired
	private IPaymentAdapter paymentAdapter;
	
	@Autowired
	private TicketRepository tp;
	
	@Async
	public CompletableFuture<Boolean> TryAndSell(Ticket ticket) {
		logger.info("Checking transaction");
		try {
			Boolean canPay = paymentAdapter.canPay().get();
			
			if(canPay) {
					logger.info("Ticket for Screening: " + ticket.getScreeningId() + " can be sold");
					Boolean payDone = paymentAdapter.pay().get();
					ticket.setPaid(1);
					tp.save(ticket);
					logger.info("Ticket for screening " + ticket.getScreeningId() + " is sold");
					return CompletableFuture.completedFuture(true);
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return CompletableFuture.completedFuture(false);
	}

}
