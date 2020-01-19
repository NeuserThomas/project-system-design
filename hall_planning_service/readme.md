# Hall Planning Service (Robin) 
This service plans movies, and also makes itself available to get info from movies.

## Deployment

To locally run this service. You need to run:
- Kafka
- Zookeeper
- [ mysql server](https://github.com/NeuserThomas/project-system-design#running-locally)
- [A mongo server](https://github.com/NeuserThomas/project-system-design#running-locally-1)

## **Rest Calls** ##
Not all the calls have been fully tested.
In dev: use localhost:2223/.....

### cinema ###
```
localhost:2223/cinema								: Get all cinema objects
localhost:2223/cinema								: Post one cinema object
localhost:2223/cinema/getAllByName				: Get one by name
localhost:2223/cinema/{id}							: Get by id
localhost:2223/cinema/plannedMovies/{id}		: Get all planned movies for cinema
localhost:2223/cinema/unplanMovie/{cinemaId}	: Remove movie object from planned movies.
```

### hall ###
```
localhost:2223/hall		: Get all hall objects
localhost:2223/hall		: Post one hall object
localhost:2223/hall/{id}	: Get by id
localhost:2223/hall/cinema/{id}		: Get all halls for specific cinema
localhost:2223/hall/{cinemaId}/{hallNumber}	: Get Hall by cinema id and hall number (not id)
```

### movie ###
```
localhost:2223/movie			: Get all
localhost:2223/movie			: Post one
localhost:2223/movie/{id}		: Get by id
localhost:2223/movie/getMovieByName/{name}	: Search for a movie through external api
```
### planning ###
```
localhost:2223/planning						: Get all day objects
localhost:2223/planning						: Post one day object
localhost:2223/planning/planDays			: Plan for every cinema, for the next week (if days are already planned, they are not replaced)
localhost:2223/planning/plannedMovies/{cinemaId}/{date} : Returns a list of movies that play on that day.
localhost:2223/planning/cinema/{id}		: Get all day for a cinema, after today (so for the next week)
localhost:2223/planning/days/clear			: Testing purposes (remove all). Not meant to be on production.
```

