package system_design.project.shop_service.adapters.payment;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public interface IPaymentAdapter {
	CompletableFuture<Boolean> canPay() throws InterruptedException;
	
	CompletableFuture<Boolean> pay() throws InterruptedException;
}
