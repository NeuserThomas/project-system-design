package system_design.project.hall_planning_service.adapters.messaging;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@EnableKafka
/**
 * A class to automatically create topics. Make sure kafka and zookeeper are up, or server will not start up.
 * @author robin
 *
 */

public class KafkaTopicConfig {
	final Logger logger = LoggerFactory.getLogger(KafkaTopicConfig.class);

    @Bean
    public NewTopic planningMade() {
    	logger.warn("--- Please make sure kafka and zookeeper are up! ---");
        return TopicBuilder.name("planningMade")
        		 .partitions(2)
        		 .replicas(1)
        		 .compact()
        		 .build();
    }
}
