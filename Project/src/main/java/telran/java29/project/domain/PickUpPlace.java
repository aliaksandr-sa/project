package telran.java29.project.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import telran.java29.project.dto.CityDto;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode(of = { "place_id" })
//S
public class PickUpPlace {
	@Id
	String place_id;
	String city;
	@GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
	GeoJsonPoint location;

	public PickUpPlace(String place_id, GeoJsonPoint location) {
		super();
		this.place_id = place_id;
		this.location = location;
		this.city = getCityByCoordinates(location.getX(), location.getY());
	}


	private String getCityByCoordinates(double x, double y) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://maps.googleapis.com/maps/api/geocode/json?latlng="+y+","+x;
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		builder.queryParam("key", "AIzaSyCZ9DyANaDuYPRMLvQ8sAujPxXH0yBB68w");
		RequestEntity<String> requestEntity = new RequestEntity<>(HttpMethod.GET,builder.build().toUri());
		System.out.println(requestEntity.toString());
		ResponseEntity<CityDto> responseEntity = restTemplate.exchange(requestEntity,CityDto.class);
		System.out.println(responseEntity.getBody().getLongName());
		return responseEntity.getBody().getLongName();
	}

}
