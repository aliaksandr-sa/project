package telran.java29.project.configuration;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;

import telran.java29.project.domain.Car;

@Component
public class Test {
	@Autowired
	MongoTemplate mongoTemplate;

	public void name(String make, String model, String year, String engine, String fuel, String gear,
			String wheels_drive, String fuel_consumption) {
		Map<String, String> filter = new LinkedHashMap<String, String>();
		filter.put("make", make);
		filter.put("model", model);
		filter.put("year", year);
		filter.put("engine", engine);
		filter.put("fuel", fuel);
		filter.put("gear", gear);
		filter.put("wheels_drive", wheels_drive);
		filter.put("fuel_consumption", fuel_consumption);

//********Poluchaem iterable document ne znayu kak obrabativat etot tip**************************

		MongoCollection<Document> collection = mongoTemplate.getCollection("cars");
		
		AggregateIterable<Document> output = collection
				.aggregate(Arrays.asList(new Document("$match", new Document("make", "Audi")),
						new Document("$match", new Document("model", "A6"))));
//		for (Document dbObject : output) {
//			System.out.println(dbObject);
//		}

//***********************************************************************************************		  

//*********Query zapros, rabotaet no ne izvestno naskolko zatratno*******************************			  

		Query query = new Query();
		// dobavlyau iz mapi v criteria, proverayu na null znacheniya
		for (Map.Entry<String, String> entry : filter.entrySet())
			if (entry.getValue() != null) {
				query.addCriteria(Criteria.where(entry.getKey()).is(entry.getValue()));
			}
		List<Car> cars = mongoTemplate.find(query, Car.class, "cars");

//		for (Car car : cars) {
//			System.out.println(car.getMake());
//		}

//***********************************************************************************************

//********Rabotaet cherez agregaciyu no tolko s odnim match filtrom******************************

		MatchOperation matchStage = Aggregation.match(Criteria.where("make").is(make));

		Aggregation aggregation = Aggregation.newAggregation(matchStage);

		AggregationResults<Car> output2 = mongoTemplate.aggregate(aggregation, "cars", Car.class);

//		for (Car car : output2) {
//			System.out.println(car.getMake());
//		}

	}

}
