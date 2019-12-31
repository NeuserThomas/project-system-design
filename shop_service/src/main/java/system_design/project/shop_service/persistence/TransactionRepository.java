package system_design.project.shop_service.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import system_design.project.shop_service.domain.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	
	//TODO: implement getTransactions after day etc.
	
}
