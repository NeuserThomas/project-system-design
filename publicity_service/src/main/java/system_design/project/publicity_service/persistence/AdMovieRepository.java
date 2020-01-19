package system_design.project.publicity_service.persistence;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import system_design.project.publicity_service.domain.AdMovie;
import system_design.project.publicity_service.domain.Category;

@Repository
public interface AdMovieRepository extends JpaRepository<AdMovie, Long> {
	/**
	 * 
	 * @param category category of the AdMovie
	 * @param maxCommissioningDate the AdMovie it's commissioningDate has to be older (lower, before) maxCommissioningDate
	 * @return list of adMovies with category and with commissionDate not more recent than maxCommissioningDate
	 */
	@Query("Select a from AdMovie a where a.category = ?1 and a.commissioningDate <= ?2")
	List<AdMovie> findAdMovieByCategoryAndDate(Category category, LocalDate maxCommissioningDate);

	List<AdMovie> findAdMovieByCategory(Category category);
	
	@Query("Select a from AdMovie a where a.category = 1")
	List<AdMovie> findAdMovieByCategoryIsChildren();

	List<AdMovie> findAdMovieByName(String Name);
}
