package system_design.project.publicity_service.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import system_design.project.publicity_service.domain.Trailer;
import system_design.project.publicity_service.domain.Category;

@Repository
public interface TrailerRepository extends JpaRepository<Trailer, Long> {
	@Query("select a from Trailer a where a.category = ?1")
	List<Trailer> findTrailerByCategory(Category category);
	
	List<Trailer> findTrailerByName(String Name);
}
