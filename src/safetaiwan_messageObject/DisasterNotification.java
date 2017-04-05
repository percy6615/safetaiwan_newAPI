package safetaiwan_messageObject;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import safetaiwan_CommTools.CommonTools;

public class DisasterNotification {
	private String uuid;
	private String name;
	private List<CoordinatesPoint> coordinatesPoints;
	private String description;
	private String iconStyleID;
	private String reportContent;
	private Timestamp reportDate;
	private String imgURL;
	private CommonTools commonTools = new CommonTools();
	private String fileName;

	public DisasterNotification() {
		this.uuid = commonTools.generateUUID();
	}

	public DisasterNotification(DisasterNotification disasterNotification) {
		this.uuid = commonTools.generateUUID();
		this.name = disasterNotification.getName();
		this.coordinatesPoints = disasterNotification.getCoordinatesPoints();
		this.description = disasterNotification.getDescription();
		this.iconStyleID = disasterNotification.getIconStyleID();
	}

	public DisasterNotification(String name, List<CoordinatesPoint> coordinatesPoint, String description,
			String iconStyleID) {
		this.uuid = commonTools.generateUUID();
		this.name = name;
		this.coordinatesPoints = coordinatesPoint;
		this.description = description;
		this.iconStyleID = iconStyleID;
	}

	public String getIconStyleID() {
		return this.iconStyleID;
	}

	public void setIconStyleID(String iconStyleID) {
		this.iconStyleID = iconStyleID;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<CoordinatesPoint> getCoordinatesPoints() {
		return this.coordinatesPoints;
	}

	public void setCoordinatesPoints(List<CoordinatesPoint> coordinatesPoints) {
		this.coordinatesPoints = coordinatesPoints;
	}

	public String getReportContent() {
		return this.reportContent;
	}

	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}

	public Timestamp getReportDate() {
		return this.reportDate;
	}

	public void setReportDate(Timestamp reportDate) {
		this.reportDate = reportDate;
	}

	public String getImgURL() {
		return this.imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	public String getUUID() {
		return this.uuid;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void descriptionParser() {
		Document doc = Jsoup.parse(this.description);
		Elements link = doc.select("tr");

		int linkNum = link.size();
		if (linkNum >= 2) {
			Element e1 = link.get(0).select("td").first();
			String[] content = e1.html().split("<br>");
			setReportContent(content[0]);
			setReportDate(commonTools.StringToTimestamp(content[1]));
			Element e2 = link.get(1).select("td").first();
			Element a = e2.select("a").first();
			String imgPath = a.attr("href");
			setImgURL(imgPath);
			File f = new File(imgPath);
			setFileName(f.getName());
			System.out.println(imgPath);
			System.out.println(content[0]);
			System.out.println(content[1]);

		} else if (linkNum < 2) {
			Element e = link.get(0);
			String[] splitChar = e.html().split("</td>");
			Element e1 = e.select("td").first();
			String recallContent = e1.text();
			setReportContent(recallContent);
			setReportDate(commonTools.StringToTimestamp(splitChar[1]));
			setImgURL(null);
			System.out.println(recallContent);
			System.out.println(splitChar[1]);
		}
	}

}
