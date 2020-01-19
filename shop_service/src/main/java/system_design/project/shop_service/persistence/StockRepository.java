package system_design.project.shop_service.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import system_design.project.shop_service.domain.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long>{

	
	public Optional<Stock> findStockByName(String name);
	
	@Query("select s.name from Stock s")
	public List<String> findAllNames();
}
