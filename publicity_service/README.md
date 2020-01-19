## **Publicity Service (Jasper)**

This service is used for publicity. When initialized, there are some entries added in the repositories.

## ***General info***

Server port: 5555

Dependencies:
- MySQL
- Kafka
- Zookeeper

## ***Deployment***
To run locally, first you need:

- [ mysql server](https://github.com/NeuserThomas/project-system-design#running-locally)
 

### ***Adapters***

One REST controller is added: PublicityRestController. The endpoints are:

```

- /publicity/publicity/{category}            (GET) -> getAdMovieByCategoryForToday() : returns the most recently made AdMovie whose commissioningDate has already passed.
- /publicity/publicity/maxDuration           (GET) -> getMaxDurationInSeconds()      : returns the maximum number of seconds an AdMovie can last.
- /publicity/publicity/maxDuration/{minutes} (POST) -> setMaximalDuration()          : set the maximal Duration of an AdMovie to a specific value.
- /publicity/publicity/delay/{minutes}       (POST) -> setDelay()                    : to set the minimal Duration of an AdMovie, post the difference in minutes. 

```

### ***Domain***

Classes are: 

- AdMovie
- Advertisement
- AMovie : an abstract class from which all others are derived
- Category : an enum
- Trailer

### ***Persistence***

Three repositories are used, all interacting with MYSQL databases:
- AdMovieRepository
- AdvertisementRepository
- TrailerRepository

They all give access to a subset of the greater database of AMovie.

### ***Service***

There is one service: the PublicityService
