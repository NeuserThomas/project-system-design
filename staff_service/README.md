# StaffService

## Apache Cassandra

Several approaches for Cassandra DB access
- CqlTemplate and ReactiveCqlTemplate are the classic Spring CQL approach and the most popular. This is the “lowest-level” approach. Note that components like CassandraTemplate use CqlTemplate under-the-hood.
- CassandraTemplate wraps a CqlTemplate to provide query result-to-object mapping and the use of SELECT, INSERT, UPDATE, and DELETE methods instead of writing CQL statements. This approach provides better documentation and ease of use.

- ReactiveCassandraTemplate wraps a ReactiveCqlTemplate to provide query result-to-object mapping and the use of SELECT, INSERT, UPDATE, and DELETE methods instead of writing CQL statements. This approach provides better documentation and ease of use.

- Repository Abstraction lets you create repository declarations in your data access layer. The goal of Spring Data’s repository abstraction is to significantly reduce the amount of boilerplate code required to implement data access layers for various persistence stores.

### MAC OSX
Running cassandra
```python
# set this as JAVA HOME to avoid JVM not found errors
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_221.jdk/Contents/Home
cassandra/bin/cassandra -f
```

### Implementation
#### Domain Objects
If you want to use Java configuration, use the @EnableCassandraRepositories annotation. The annotation carries the same attributes as the namespace element. If no base package is configured, the infrastructure scans the package of the annotated configuration class.

Because our domain repository extends CrudRepository, it provides you with basic CRUD operations. Working with the repository instance is a matter of injecting the repository as a dependency into a client.


## References
- [Sprint Data for Apache Cassandra](https://docs.spring.io/spring-data/cassandra/docs/current/reference/html/#reference)
- https://github.com/spring-projects/spring-data-cassandra/blob/master/src/main/asciidoc/reference/cassandra.adoc
- [Spring IOC ](https://docs.spring.io/spring/docs/3.2.x/spring-framework-reference/html/beans.html)
- Defining entities: https://docs.spring.io/spring-data/cassandra/docs/current/reference/html/#cassandra-repo-usage
- [Spring Data Cassandra (Github) examples](https://github.com/spring-projects/spring-data-examples/tree/master/cassandra)