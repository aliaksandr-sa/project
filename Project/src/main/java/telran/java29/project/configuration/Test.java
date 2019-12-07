package telran.java29.project.configuration;

import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;

import telran.java29.project.domain.Car;
@Component
public class Test {
	    @Autowired
	    MongoTemplate mongoTemplate;
	    
	   
	   public void name() {

//		   System.out.println("FUCK");
//		   MongoCollection<Document> collection = mongoTemplate.getCollection("cars");
//		   System.out.println(collection.toString());
//		  AggregateIterable<Document> output = collection.aggregate(Arrays.asList(new Document("$match", 
//				    new Document("make", "Audi")), 
//				    new Document("$match", 
//				    new Document("model", "A6")))
//		           );
//		  
////		 Aggregation aggregation = Aggregation.newAggregation(MatchOperation.);
////		   
////		 mongoTemplate.aggregate(aggregation, Car.class);
////		   
//		   
//		   
//		   // Print for demo
//		   for (Document dbObject : output)
//		   {
//			   
//		       System.out.println(dbObject);
//		   }
//
//		   
//		   
//	   }
		   MatchOperation matchStage = Aggregation.match(new Criteria("make").is("make"));
		   GroupOperation groupByMake = Aggregation.group("make");
//		   ProjectionOperation projectStage = Aggregation.project("Audi");
		            
		   Aggregation aggregation 
		     = Aggregation.newAggregation(groupByMake);
		    
		   AggregationResults<Car> output 
		     = mongoTemplate.aggregate(aggregation, "cars", Car.class);
		   
		   
		   
//		   GroupOperation groupByMake =  Aggregation.group("make");
//		  MatchOperation matchOperation = new MatchOperation(new Criteria(groupByMake));
////		  Object match = new BasicDBObject("$match",new BasicDBObject("make", "Audi"));
//		  
//		  Aggregation aggregation = Aggregation.newAggregation(matchOperation);
//		  List<Car> result = mongoTemplate.aggregate(aggregation, "cars", Car.class).getMappedResults();
		  
		 for (Car car : output) {
			System.out.println(car.getMake());
		}
	   }   
	   }
		   

