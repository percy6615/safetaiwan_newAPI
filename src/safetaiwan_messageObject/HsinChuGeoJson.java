package safetaiwan_messageObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HsinChuGeoJson {
	String cityName;
	List<CoordinatesPoint> multiPolygon = new ArrayList();
	HashMap<Integer,List<CoordinatesPoint>> diffmultiPolygon;
	public String getCityName() {
		return cityName;
	}
	public HashMap<Integer, List<CoordinatesPoint>> getDiffmultiPolygon() {
		return diffmultiPolygon;
	}
	public void setDiffmultiPolygon(HashMap<Integer, List<CoordinatesPoint>> diffmultiPolygon) {
		this.diffmultiPolygon = diffmultiPolygon;
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
