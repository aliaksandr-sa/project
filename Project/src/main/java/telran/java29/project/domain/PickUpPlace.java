package telran.java29.project.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode(of = {"place_id"})
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
		// TODO Auto-generated method stub
		return "London";
	}
	
}
