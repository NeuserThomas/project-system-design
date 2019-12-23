package parking_service.persistence;

import org.springframework.data.repository.CrudRepository;

import parking_service.domain.ParkingTicket;

public interface ParkingTicketRepository extends CrudRepository<ParkingTicket, Long> {

}
