package safetaiwan_messageObject;

import java.util.ArrayList;
import java.util.List;

public class HsinChuGeoJson {
	String cityName;
	List<CoordinatesPoint> multiPolygon = new ArrayList();
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public List<CoordinatesPoint> getMultiPolygon() {
		return multiPolygon;
	}
	public void setMultiPolygon(List<CoordinatesPoint> multiPolygon) {
		this.multiPolygon = multiPolygon;
	}
}
