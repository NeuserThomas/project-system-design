package system_design.project.publicity_service.persistence;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import system_design.project.publicity_service.domain.Ad_Movie;
import system_design.project.publicity_service.domain.Category;

@Repository
public interface Ad_MovieRepository extends JpaRepository<Ad_Movie, Long> {
	@Query("Select a from Ad_Movie a where a.category = ?1 and a.creationDate > ?2")
	List<Ad_Movie> findAd_MovieByCategoryAndDate(Category category, LocalDate creationDate);
	
	@Query("Select a from Ad_Movie a where a.category = 1")
	List<Ad_Movie> findAd_MovieByCategoryIsChildren();
}
