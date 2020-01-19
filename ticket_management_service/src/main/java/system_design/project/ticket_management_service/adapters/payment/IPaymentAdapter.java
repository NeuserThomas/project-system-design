package system_design.project.ticket_management_service.adapters.payment;

import java.util.concurrent.CompletableFuture;

public interface IPaymentAdapter {
	CompletableFuture<Boolean> canPay() throws InterruptedException;
	
	CompletableFuture<Boolean> pay() throws InterruptedException;
}