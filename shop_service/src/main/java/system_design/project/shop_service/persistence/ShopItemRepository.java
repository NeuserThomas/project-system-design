package system_design.project.shop_service.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import system_design.project.shop_service.domain.ShopItem;
import system_design.project.shop_service.domain.Stock;

@Repository
public interface ShopItemRepository extends JpaRepository<ShopItem, Long>{
	
	@Query("Select s.name from ShopItem s where s.id in ?1")
	List<String> findNamesById(List<Long> ids);
}
