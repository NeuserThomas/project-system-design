package system_design.project.shop_service.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import system_design.project.shop_service.domain.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long>{
	
}
