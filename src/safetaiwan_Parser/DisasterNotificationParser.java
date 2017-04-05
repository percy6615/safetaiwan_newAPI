package safetaiwan_Parser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Feature;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Point;
import safetaiwan_CommTools.CommonTools;
import safetaiwan_messageObject.CoordinatesPoint;
import safetaiwan_messageObject.DisasterNotification;

public class DisasterNotificationParser {
	private Kml kml;
	private String fileName;

	public static void main(String[] args) {

		DisasterNotificationParser disasterNotificationParser = new DisasterNotificationParser();
		disasterNotificationParser.setKml("test.kml");
		List<?>  list= disasterNotificationParser.disasterNotificationParserList(disasterNotificationParser.getKml());
		List<String> descriptionList = new ArrayList<String>();
		for(int i = 0 ; i < list.size();i++){
			String description = ((DisasterNotification)list.get(i)).getReportContent();
			System.out.println(description);
			descriptionList.add(description);
		}
		
		
	}

	public  List<?> disasterNotificationParserList(Kml kml) {
		List<DisasterNotification> returnObject = new ArrayList<DisasterNotification>();
		Document document = (Document) kml.getFeature();
		List<Feature> folderList = (List<Feature>) document.getFeature();
		int folderListSize = featureListSize(folderList);
		for (int i = 0; i < folderListSize; i++) {
			Folder folder = (Folder) folderList.get(i);
			List<Feature> placemarkList = (List<Feature>) folder.getFeature();
			int placemarkListSize = featureListSize(placemarkList);
			for (int j = 0; j < placemarkListSize; j++) {
				Placemark placemark = (Placemark) placemarkList.get(j);
				String name = placemark.getName();
				String description = placemark.getDescription();
				String styleUrl = placemark.getStyleUrl();
				Point geometry = (Point) placemark.getGeometry();
				List<Coordinate> pointList = (List<Coordinate>) geometry.getCoordinates();
				List<CoordinatesPoint> CoordinatesPointList = new ArrayList<CoordinatesPoint>();
				int pointListSize = featureListSize(pointList);
				for (int k = 0; k < pointListSize; k++) {
					CoordinatesPoint coordinatesPoint = new CoordinatesPoint();
					coordinatesPoint.setLatitudeCoord(pointList.get(k).getLatitude());
					coordinatesPoint.setLongitudeCoord(pointList.get(k).getLongitude());
					CoordinatesPointList.add(coordinatesPoint);
				}
				DisasterNotification disasterNotification = new DisasterNotification(name, CoordinatesPointList,
						description, styleUrl);
				disasterNotification.descriptionParser(); //descript html parser to object
				returnObject.add(disasterNotification);
			}
		}
		return returnObject;

	}
	public Kml getKml() {
		return kml;
	}

	public void setKml(String fileName) {
		setFileName(fileName);
		String filePath = CommonTools.APPLocation() + "\\resources\\exampledata\\" + getFileName();
		File f = new File(filePath);
		this.kml = Kml.unmarshal(f);
	}

	public String getFileName() {
		// TODO Auto-generated method stub
		return this.fileName;
	}

	private void setFileName(String fileName) {
		// TODO Auto-generated method stub
		this.fileName = fileName;
	}

	public static int featureListSize(List<?> Listf) {
		return Listf.size();
	}
	
}
