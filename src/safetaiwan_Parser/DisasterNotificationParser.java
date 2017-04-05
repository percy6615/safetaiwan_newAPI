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
		List<String> descriptionList = new ArrayList();
		for(int i = 0 ; i < list.size();i++){
			String description = ((DisasterNotification)list.get(i)).getReportContent();
			System.out.println(description);
			descriptionList.add(description);
		}
		String description0 = descriptionList.get(0);
		
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
// //*[@id="viewplayer"]/div[2]/video
//	//*[@id="viewplayer"]/div[2]
//	#viewplayer > div.jw-media.jw-reset
//	<div class="jw-media jw-reset"><video class="jw-video jw-reset" x-webkit-airplay="allow" webkit-playsinline="" src="https://redirector.googlevideo.com/videoplayback?id=0acfde7554cf123c&amp;itag=37&amp;source=webdrive&amp;requiressl=yes&amp;ttl=transient&amp;mm=30&amp;mn=sn-vgqs7ns7&amp;ms=nxu&amp;mv=u&amp;pl=32&amp;ei=Q8nkWJuOGNXnqgWg6anIAg&amp;mime=video/mp4&amp;lmt=1491198205707619&amp;mt=1491388622&amp;ip=2604:4300:a:11:202:c9ff:feb9:574a&amp;ipbits=0&amp;expire=1491403139&amp;sparams=ip%2Cipbits%2Cexpire%2Cid%2Citag%2Csource%2Crequiressl%2Cttl%2Cmm%2Cmn%2Cms%2Cmv%2Cpl%2Cei%2Cmime%2Clmt&amp;signature=9BA0BF550EA220F601FB7BA1F77D9DBED945B86A.255D05F83D122A03C59FC0961B0C22C6B7C8AFD8&amp;key=ck2&amp;app=explorer" style="transform: scale(0.482292, 0.481481); left: -497px; right: -497px; width: 1920px; height: 1080px; bottom: -280px; top: -280px;"></video></div>
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
