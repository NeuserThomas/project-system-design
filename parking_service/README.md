## **Parking Service (Thomas)**

This service is used for parking management. When initialized, one parking is available with 200 free spots.

General info

Server port: 2301

Dependencies:
- MySQL

### ***Adapters***

One REST controller is added: ParkingRestController. The endpoints are:

```

- /parking/parking -> getParkings() : returns (all of) the parking(s). At the moment, only one parking is used.
- /parking/parking/numberOfFreeSpots -> getNumberOfFreeSpots(): returns how many spots are still free on the parking
- /parking/parking/tickets -> getParkingTickets() : returns all the parkingtickets in the database
- /parking/parking/exitParking/{parkingTicketId} -> exitParking() : for exiting the parking when a valid parkingTicketId is supplied. 
- /parking/parking/validateTicket/{parkingTicketId}?ticketId={cinemaTicketId} -> validateParkingTicket() : for validating your parking ticket when an valid cinemaTicketId is supplied.
- /parking/parking/getTicket -> getParkingTicket() : when entering the parking, a new parkingticket is returned.

```

### ***Domain***

Classes are: 

- Parking 
- ParkingTicket

### ***Persistence***

Two repositories are used, both interacting with MYSQL databases:
- ParkingRepository
- ParkingTicketRepository
