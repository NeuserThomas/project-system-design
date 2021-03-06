# StaffService

## Deployment information

### zo 19 jan 2020 01:50:03 CET

Working configuration

The `application.properties`-file
```
spring.profiles.active=prod
server.port=2224
server.servlet.context-path=/staffservice
# BEAN FLAG
populateCinemaRepository.enabled=true
populateEmployeeRepository.enabled=true
populateTimeSlotRepository.enabled=true
```
Sequence of commands to run the Cassandra DB & StaffService (note that Cassandra DB should be running prior to StaffService)
```
# network 
docker network create app-tier --driver bridge
# run cassandra service (remove when being stopped)
docker run -d --rm --name cassandra --network app-tier bitnami/cassandra:latest
# build service
docker build -t staffservice:v1.0 . && docker run -P --name StaffService staffservice:v1.0
# run service & connect to network 
docker run -p 2224:2224/tcp  -dit --rm --name staffservice04 --network app-tier staffservice:v1.0
```

> The -dit flags mean to start the container detached (in the background), interactive (with the ability to type into it), and with a TTY (so you can see the input and output).
> If you have not specified any --network flags, the containers connect to the default bridge network.
> Running the staffservice when cassandra isn't active --> will cause staffservice to shutdown and throw an exception


A brief overview of (some of) the availabe calls
- `localhost:2224/staffservice/timeslot/`
    - get all timeslots
- `localhost:2224/staffservice/employee/`
    - get all employees
- `localhost:2224/staffservice/timeslot/2020-01-19`
    - get all timeslots of a given date
- `localhost:2224/staffservice/timeslot/cinema/1/employee/9b541ee0-3b08-11ea-8a57-01b8efbeb107`
    - get all timeslots for an employee with a given ID that works at a cinema (given the cinema id)
```
# localhost:2224/staffservice/timeslot/
[
    {
        "id": "f4136c50-3a5b-11ea-9406-d36fce80f50c",
        "cinemaId": 1,
        "day": {
            "millisSinceEpoch": 1579392000000,
            "daysSinceEpoch": 18280,
            "day": 19,
            "year": 2020,
            "month": 1
        },
        "timeslot": "12:00:00",
        "employeeId": "f3bff520-3a5b-11ea-9406-d36fce80f50c",
        "availabilityCode": 0
    },
 ..localhost:2224/staffservice/employee/...

]
# 
[
    {
        "id": "f3bff520-3a5b-11ea-9406-d36fce80f50c",
        "firstName": "Jan",
        "lastName": "Cnops"
    },
    {
        "id": "f3bdd240-3a5b-11ea-9406-d36fce80f50c",
        "firstName": "Joris",
        "lastName": "Moreau"
    },
    {
        "id": "f3baec10-3a5b-11ea-9406-d36fce80f50c",
        "firstName": "Geralt",
        "lastName": "Of Rivia"
    }
]
# get timeslots for a specific date
# localhost:2224/staffservice/timeslot/2020-01-19
[
    {
        "id": "f4136c50-3a5b-11ea-9406-d36fce80f50c",
        "cinemaId": 1,
        "day": {
            "millisSinceEpoch": 1579392000000,
            "daysSinceEpoch": 18280,
            "day": 19,
            "year": 2020,
            "month": 1
        },
        "timeslot": "12:00:00",
        "employeeId": "f3bff520-3a5b-11ea-9406-d36fce80f50c",
        "availabilityCode": 0
    },
  ....   
  
  # getAllTimeSlotsForEmployee
  #   localhost:2224/staffservice/timeslot/cinema/1/employee/9b541ee0-3b08-11ea-8a57-01b8efbeb107
[
    {
        "id": "9b89fbf0-3b08-11ea-8a57-01b8efbeb107",
        "cinemaId": 1,
        "day": {
            "millisSinceEpoch": 1579392000000,
            "daysSinceEpoch": 18280,
            "day": 19,
            "year": 2020,
            "month": 1
        },
        "timeslot": "12:00:00",
        "employeeId": "9b541ee0-3b08-11ea-8a57-01b8efbeb107",
        "availabilityCode": 0
    },
    ...
    
   ]
]

````


## Apache Cassandra

### MAC OSX
Running cassandra
```python
# set this as JAVA HOME to avoid JVM not found errors
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_221.jdk/Contents/Home
cassandra/bin/cassandra -f
```


## References
- [Spring Data for Apache Cassandra](https://docs.spring.io/spring-data/cassandra/docs/current/reference/html/#reference)
- https://github.com/spring-projects/spring-data-cassandra/blob/master/src/main/asciidoc/reference/cassandra.adoc
- [Spring IOC ](https://docs.spring.io/spring/docs/3.2.x/spring-framework-reference/html/beans.html)
- Defining entities: https://docs.spring.io/spring-data/cassandra/docs/current/reference/html/#cassandra-repo-usage
- [Spring Data Cassandra (Github) examples](https://github.com/spring-projects/spring-data-examples/tree/master/cassandra)
- https://hub.docker.com/r/bitnami/cassandra/
- [networking from a container's of view](https://docs.docker.com/config/containers/container-networking/)
- [docker publishing ports](https://docs.docker.com/config/containers/container-networking/)
