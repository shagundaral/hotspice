package utilities;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@Configuration
public class MongoConfig {

	public @Bean
	MongoDbFactory mongoDbFactory() throws Exception {
		MongoClientURI mongoClientURI = new MongoClientURI("mongodb://shagundaral:shagun102@ds025409.mlab.com:25409/hotspicedb");
		MongoClient mongoClient = new MongoClient(mongoClientURI);

		return new SimpleMongoDbFactory(mongoClient, "hotspicedb");
	}

	public @Bean
	MongoTemplate mongoTemplate() throws Exception {
		
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
				
		return mongoTemplate;
		
	}
	
}
