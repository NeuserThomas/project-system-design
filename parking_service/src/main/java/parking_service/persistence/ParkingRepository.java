package parking_service.persistence;

import org.springframework.data.repository.CrudRepository;

import parking_service.domain.Parking;

public interface ParkingRepository extends CrudRepository<Parking, Long> {

}
