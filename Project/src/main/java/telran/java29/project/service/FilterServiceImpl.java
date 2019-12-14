package telran.java29.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;

import telran.java29.project.domain.Car;
import telran.java29.project.dto.NewCarDto;
import telran.java29.project.dto.filters.FilterDto;
@Service
public class FilterServiceImpl implements FilterService {
	@Autowired
	MongoTemplate mongoTemplate;

	
	
	@Override
	public List<FilterDto> getFilters() {
		updateFilters();
		return mongoTemplate.findAll(FilterDto.class, "filters");
	}
	
	public void updateFilters() {
		TypedAggregation<Car> filtersAggregation = 
				Aggregation.newAggregation(Car.class,
						
						Aggregation.group("$make", "$model", "$year","$engine").addToSet("fuel").as("fuels"),
						Aggregation.group("$make", "$model", "$year").addToSet(new BasicDBObject(){
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					{
						put("engine","$_id.engine");
						put("fuels", "fuels");
					}
				}).as("engines"),
						
//						Aggregation.group("$make", "$model", "$year").addToSet("$engine").as("engines"),
						Aggregation.group("$make", "$model").addToSet(new BasicDBObject() {
							/**
							 * 
							 */
							private static final long serialVersionUID = 1L;

							{
							put("year", "$_id.year");
							put("engines", "$engines");
							}
						}).as("years"),
						Aggregation.group("$make").addToSet(new BasicDBObject() {
							/**
							 * 
							 */
							private static final long serialVersionUID = 1L;

							{
								put("model", "$_id.model");
								put("years", "$years");
							}
						}).as("models"),
						Aggregation.out("filters")
						);
		
		mongoTemplate.aggregate(filtersAggregation, FilterDto.class);
	}

}
