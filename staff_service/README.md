# StaffService

## Deployment information

### za 18 jan 2020 18:24:59 CET
Running cassandra 
```
# origin: bitnami/cassandra
# Container ID: 811984d095fafd4d2e010cb442fab31d85725a57e3d75b7d3012e65f9b8bb114
docker start cassandra_01
```
When cassandra is running, StaffService can be launched in `dev`.
The difference in dev is that the value of the cassandra contactpoints
```
spring.data.cassandra.contact-points=localhost
``` 
Overview of working parts, for which the following URLs are of relevance
````plain 
http://localhost:2224/timeslot/
http://localhost:2224/cinemas
http://localhost:2224/employees
````

`http://localhost:2224/timeslot/` should have an output similar to
```
[{"id":"198d9770-3a17-11ea-a5cf-df37814616fe","cinemaId":0,"day":{"millisSinceEpoch":1577836800000,
"daysSinceEpoch":18262,"year":2020,"month":1,"day":1},
"timeslot":"12:00:00","employeeId":"1980c630-3a17-11ea-a5cf-df37814616 ... 
```

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