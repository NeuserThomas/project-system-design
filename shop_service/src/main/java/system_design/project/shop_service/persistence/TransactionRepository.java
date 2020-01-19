package system_design.project.shop_service.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import system_design.project.shop_service.domain.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	
	@Query("Select t from Transaction t where t.paid=False")
	List<Transaction> findByUnPaidTransactions();
}
