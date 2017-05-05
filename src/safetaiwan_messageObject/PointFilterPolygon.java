package safetaiwan_messageObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import safetaiwan_CommTools.CommonTools;

public class PointFilterPolygon {
	public  List<CoordinatesPoint> polygonPoints;

	public static void main(String[] args) {
//		CoordinatesPoint p1 = new CoordinatesPoint();
//		p1.setLongitudeCoord(0);
//		p1.setLatitudeCoord(0);
//		CoordinatesPoint p2 = new CoordinatesPoint();
//		p2.setLongitudeCoord(5);
//		p2.setLatitudeCoord(0);
//		CoordinatesPoint p3 = new CoordinatesPoint();
//		p3.setLongitudeCoord(8);
//		p3.setLatitudeCoord(3);
//		CoordinatesPoint p4 = new CoordinatesPoint();
//		p4.setLongitudeCoord(4);
//		p4.setLatitudeCoord(7);
//		CoordinatesPoint p5 = new CoordinatesPoint();
//		p5.setLongitudeCoord(-1);
//		p5.setLatitudeCoord(5);
//		CoordinatesPoint test = new CoordinatesPoint();
//		test.setLongitudeCoord(6);
//		test.setLatitudeCoord(1.1);
//		List<CoordinatesPoint> list = new ArrayList();
//		list.add(p1);
//		list.add(p2);
//		list.add(p3);
//		list.add(p4);
//		list.add(p5);
		PointFilterPolygon PointFilterPolygon= new PointFilterPolygon();
		CoordinatesPoint test = new CoordinatesPoint();
		test.setLongitudeCoord(121.16028041524);
		test.setLatitudeCoord(24.9066644);
		List<HsinChuGeoJson> p = PointFilterPolygon.parserJson("");
		List<CoordinatesPoint> getCoordinatesPoint = PointFilterPolygon.getCityCoordinatesPoints(p,"新竹市");
		PointFilterPolygon.preCalcValues(getCoordinatesPoint);
		System.out.println(PointFilterPolygon.pointInPolygon(test));
	}

	public  List<CoordinatesPoint> getPolygonPoints() {
		return polygonPoints;
	}

	public  void setPolygonPoints(List<CoordinatesPoint> polygonPoints) {
		this.polygonPoints = polygonPoints;
	}

	public  void preCalcValues(List<CoordinatesPoint> polygonPoints) {

		int polyCorners = polygonPoints.size();
		int i = 0;
		int j = polyCorners - 1;
		for (i = 0; i < polyCorners; i++) {
			CoordinatesPoint p_i = polygonPoints.get(i);
			CoordinatesPoint p_j = polygonPoints.get(j);
			if (p_i.getLatitudeCoord() == p_j.getLatitudeCoord()) {
				p_i.setConstant(p_i.getLatitudeCoord());
				p_i.setMultiple(0);
			} else {
				p_i.setConstant(p_i.getLongitudeCoord()
						- (p_i.getLatitudeCoord() * p_j.getLongitudeCoord())
								/ (p_j.getLatitudeCoord() - p_i.getLatitudeCoord())
						+ (p_i.getLatitudeCoord() * p_i.getLongitudeCoord())
								/ (p_j.getLatitudeCoord() - p_i.getLatitudeCoord()));
				p_i.setMultiple((p_j.getLongitudeCoord() - p_i.getLongitudeCoord())
						/ (p_j.getLatitudeCoord() - p_i.getLatitudeCoord()));
			}
			j = i;
		}
		setPolygonPoints(polygonPoints);
	}

	public  boolean pointInPolygon(CoordinatesPoint test) {
		List<CoordinatesPoint> polygonPoints = getPolygonPoints();
		int polyCorners = polygonPoints.size();
		int i = 0;
		int j = polyCorners - 1;
		boolean oddNodes = false;
		for (i = 0; i < polyCorners; i++) {
			CoordinatesPoint p_i = polygonPoints.get(i);
			CoordinatesPoint p_j = polygonPoints.get(j);
			if ((p_i.getLatitudeCoord() < test.getLatitudeCoord() && p_j.getLatitudeCoord() >= test.getLatitudeCoord()
					|| p_j.getLatitudeCoord() < test.getLatitudeCoord()
							&& p_i.getLatitudeCoord() >= test.getLatitudeCoord())) {
				oddNodes ^= (test.getLatitudeCoord() * p_i.getMultiple() + p_i.getConstant() < test
						.getLongitudeCoord());
			}
			j = i;
		}
		return oddNodes;

	}

	public  List<HsinChuGeoJson> parserJson(String fileName) {
		if (fileName == null || fileName.equals("")) {
			fileName = CommonTools.APPLocation() + "\\resources\\cfg\\hsinchu.json";
		}
		try {
			FileReader reader = new FileReader(fileName);
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
			JSONArray features = (JSONArray) jsonObject.get("features");
			Iterator<?> i = features.iterator();
			List<HsinChuGeoJson> hsinChuGeoJsons = new ArrayList<HsinChuGeoJson>();
			while (i.hasNext()) {
				HsinChuGeoJson hsinChuGeoJson = new HsinChuGeoJson();
				JSONObject innerObj = (JSONObject) i.next();
				JSONObject innerProperties = (JSONObject) innerObj.get("properties");
				JSONObject innerGeometry = (JSONObject) innerObj.get("geometry");
				JSONArray Geometries = (JSONArray) innerGeometry.get("coordinates");
				Iterator<?> j = Geometries.iterator();
				hsinChuGeoJson.setCityName((String) innerProperties.get("c_name"));
				// System.out.println(innerProperties.get("c_name"));
				List<CoordinatesPoint> coordinatesPoints = new ArrayList<CoordinatesPoint>();
				while (j.hasNext()) {
					JSONArray innerinnerObj = (JSONArray) j.next();
					Iterator<?> k = innerinnerObj.iterator();
					while (k.hasNext()) {
						JSONArray innerinnerinnerObj = (JSONArray) k.next();
						Iterator<?> l = innerinnerinnerObj.iterator();
						while (l.hasNext()) {
							CoordinatesPoint coordinatesPoint = new CoordinatesPoint();
							JSONArray tt = (JSONArray) l.next();
							// System.out.println(tt.get(0));
							coordinatesPoint.setLongitudeCoord((double) tt.get(0));
							coordinatesPoint.setLatitudeCoord((double) tt.get(1));
							coordinatesPoints.add(coordinatesPoint);
							// System.out.println(tt);
						}
					}

				}
				hsinChuGeoJson.setMultiPolygon(coordinatesPoints);
				hsinChuGeoJsons.add(hsinChuGeoJson);
			}
			return hsinChuGeoJsons;
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public  List<CoordinatesPoint> getCityCoordinatesPoints(List<HsinChuGeoJson> hsinChuGeoJsons,String cityName){
		List<CoordinatesPoint> coordinatesPoints = null;
		for(int i = 0; i < hsinChuGeoJsons.size();i++){
			if(hsinChuGeoJsons.get(i).getCityName().equals(cityName)){
				coordinatesPoints = hsinChuGeoJsons.get(i).getMultiPolygon();
			}
		}
		return coordinatesPoints;
	}
}
