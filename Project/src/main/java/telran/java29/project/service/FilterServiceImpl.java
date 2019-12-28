package telran.java29.project.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;

import telran.java29.project.convertor.Convertor;
import telran.java29.project.domain.Car;
import telran.java29.project.dto.SearchResultDto;
import telran.java29.project.dto.filters.FilterDto;
import telran.java29.project.dto.filters.SearchByFiltersDto;
@Service
public class FilterServiceImpl implements FilterService {
	@Autowired
	MongoTemplate mongoTemplate;
	@Autowired
	Convertor conventor;

	
	
	@Override
	public Iterable<FilterDto> getFilters() {
		return mongoTemplate.findAll(FilterDto.class, "filters");
	}
	
	
	
	public void updateFilters() {
		TypedAggregation<Car> filtersAggregation = 
				Aggregation.newAggregation(Car.class,
						

						Aggregation.group("$make", "$model", "$year","$engine","$fuel","$gear","wheels_drive").addToSet("fuel_consumption").as("fuel_consumptions"),
						
						Aggregation.group("$make", "$model", "$year","$engine","fuel","$gear").addToSet(new BasicDBObject(){
							/**
							 * 
							 */
							private static final long serialVersionUID = 1L;

							{
								put("wheels_drive","$_id.wheels_drive");
								put("fuel_consumptions", "$fuel_consumptions");
							}
						}).as("wheels_drives"),
						Aggregation.group("$make", "$model", "$year","$engine","fuel").addToSet(new BasicDBObject(){
							/**
							 * 
							 */
							private static final long serialVersionUID = 1L;

							{
								put("gear","$_id.gear");
								put("wheels_drives", "$wheels_drives");
							}
						}).as("gears"),
						
						Aggregation.group("$make", "$model", "$year","$engine").addToSet(new BasicDBObject(){
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					{
						put("fuel","$_id.fuel");
						put("gears", "$gears");
					}
				}).as("fuels"),
						
						
						
						Aggregation.group("$make", "$model", "$year").addToSet(new BasicDBObject(){
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					{
						put("engine","$_id.engine");
						put("fuels", "$fuels");
					}
				}).as("engines"),
						
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



	@Override
	public SearchByFiltersDto searchByFilters(String make, String model, String year, String engine,
			String fuel, String gear, String wheels_drive, int items_on_page,int current_page) {
		Pageable paging = PageRequest.of(current_page, items_on_page);
		//Search cars**********************************
		Map<String, String> filter = new LinkedHashMap<String, String>();
		filter.put("make", make);
		filter.put("model", model);
		filter.put("year", year);
		filter.put("engine", engine);
		filter.put("fuel", fuel);
		filter.put("gear", gear);
		filter.put("wheels_drive", wheels_drive);
		Query query = new Query();
		// dobavlyau iz mapi v criteria, proverayu na null znacheniya
		for (Map.Entry<String, String> entry : filter.entrySet()) {
			if (entry.getValue() != null) {
				query.addCriteria(Criteria.where(entry.getKey()).is(entry.getValue()));
				query.with(paging);
			}
		}
		List<Car> cars = mongoTemplate.find(query, Car.class, "cars");
	  
		SearchResultDto searchResultDto = SearchResultDto.builder()
		.cars(cars.stream().map(car->conventor.convertToCarDtoSimple(car)).collect(Collectors.toList()))
		.current_page(paging.getPageNumber()).items_on_page(paging.getPageSize()).items_total(cars.size())
		.build();
		return SearchByFiltersDto.builder().searchResultDto(searchResultDto).filter(getFilters()).build();
	}

}
