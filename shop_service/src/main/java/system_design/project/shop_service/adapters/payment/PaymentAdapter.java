package system_design.project.shop_service.adapters.payment;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@EnableAsync
public class PaymentAdapter implements IPaymentAdapter{

	Logger logger = LoggerFactory.getLogger(PaymentAdapter.class);
	
	@Async
	public CompletableFuture<Boolean> pay() throws InterruptedException{
		CompletableFuture<Boolean> completableFuture 
	      = new CompletableFuture<>();
	 
	    Executors.newCachedThreadPool().submit(() -> {
	        Thread.sleep(3000);
	        completableFuture.complete(true);
	        return null;
	    });
	 
	    return completableFuture;
	}
	
	@Override
	public CompletableFuture<Boolean> canPay() throws InterruptedException {
		CompletableFuture<Boolean> completableFuture 
	      = new CompletableFuture<>();
	 
	    Executors.newCachedThreadPool().submit(() -> {
	        Thread.sleep(1000);
	        completableFuture.complete(true);
	        return null;
	    });
	 
	    return completableFuture;
	}
}
