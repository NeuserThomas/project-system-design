package system_design.project.staff_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.DropKeyspaceSpecification;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.util.List;


/**
 * ref: https://docs.spring.io/spring-data/cassandra/docs/current/reference/html/#cassandra.cassandra-java-config
 * An alternative is to register an instance of com.datastax.driver.core.Session with the container by using
 * Spring’s CassandraCqlSessionFactoryBean and CassandraCqlClusterFactoryBean.
 * As compared to instantiating a com.datastax.driver.core.Session instance directly,
 * the FactoryBean approach has the added advantage of also providing the container with
 * an ExceptionTranslator implementation that translates Cassandra exceptions to exceptions in
 * Spring’s portable DataAccessException hierarchy.
 * This hierarchy and the use of @Repository is described in Spring’s DAO support features.
 *
 *
 * @EnableCassandraRepositories(basePackageClasses = ...)
 * Type-safe alternative to basePackages() for specifying the packages to scan for annotated components.
 * The package of each class specified will be scanned.
 * Consider creating a special no-op marker class or interface in each package that serves no purpose other
 * than being referenced by this attribute.
 *
 *
 * https://docs.spring.io/spring-data/cassandra/docs/current/reference/html/#cassandra.schema-management
 *Spring Data for Apache Cassandra approaches data access with mapped entity classes that fit your data model.
 * You can use these entity classes to create Cassandra table specifications and user type definitions.
 *
 */
@Configuration
@EnableCassandraRepositories(basePackages = {"system_design.project.staff_service.persistence"})

public class CassandraConfig extends AbstractCassandraConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(CassandraConfig.class);
    private static final String CONTACT_POINTS = "localhost";
    private static final String KEYSPACE_NAME = "mykeyspace";


    private static final SchemaAction SCHEMA_ACTION = SchemaAction.CREATE_IF_NOT_EXISTS;

    @Override
    protected boolean getMetricsEnabled() {
        return false;
    }

    @Override
    protected String getContactPoints() {
        return CONTACT_POINTS;
    }

    @Override
    protected String getKeyspaceName() {
        return KEYSPACE_NAME;
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SCHEMA_ACTION;
    }


    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {

        return super.getKeyspaceCreations();
    }

    @Override
    protected List<DropKeyspaceSpecification> getKeyspaceDrops() {
        return super.getKeyspaceDrops();
    }
}
