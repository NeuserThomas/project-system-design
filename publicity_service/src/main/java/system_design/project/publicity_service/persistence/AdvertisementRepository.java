package system_design.project.publicity_service.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import system_design.project.publicity_service.domain.Advertisement;
import system_design.project.publicity_service.domain.Category;
import system_design.project.publicity_service.domain.Trailer;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
	@Query("select a from Advertisement a where a.category = ?1")
	List<Advertisement> findAdvertisementByCategory(Category category);

	List<Advertisement> findAdvertisementByName(String Name);
}
