package safetaiwan_messageObject;

import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import safetaiwan_CommTools.CommonTools;

public class DisasterNotification {
	private String name;
	private List<CoordinatesPoint> coordinatesPoints;
	private String description;
	private String iconStyleID;
	private String reportContent;
	private Date reportDate;
	private String imgURL;

	public DisasterNotification() {

	}

	public DisasterNotification(DisasterNotification disasterNotification) {
		this.name = disasterNotification.getName();
		this.coordinatesPoints = disasterNotification.getCoordinatesPoints();
		this.description = disasterNotification.getDescription();
		this.iconStyleID = disasterNotification.getIconStyleID();
	}

	public DisasterNotification(String name, List<CoordinatesPoint> coordinatesPoint, String description,
			String iconStyleID) {
		this.name = name;
		this.coordinatesPoints = coordinatesPoint;
		this.description = description;
		this.iconStyleID = iconStyleID;
	}

	public String getIconStyleID() {
		return iconStyleID;
	}

	public void setIconStyleID(String iconStyleID) {
		this.iconStyleID = iconStyleID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<CoordinatesPoint> getCoordinatesPoints() {
		return coordinatesPoints;
	}

	public void setCoordinatesPoints(List<CoordinatesPoint> coordinatesPoints) {
		this.coordinatesPoints = coordinatesPoints;
	}

	public String getReportContent() {
		return reportContent;
	}

	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	public void descriptionTag(String htmlDesciption) {
		Document doc = Jsoup.parse(htmlDesciption);
		Elements link = doc.select("tr");
		CommonTools commonTools = new CommonTools();
		int linkNum = link.size();
		if (linkNum >= 2) {
			
			Element e1 = link.get(0).select("td").first();
			String[] content = e1.html().split("<br>");
			setReportContent(content[0]);
			setReportDate( commonTools.StringToDate(content[1]));
			
			Element e2 = link.get(1).select("td").first();
			Element a = e2.select("a").first();
			String imgPath = a.attr("href");
			setImgURL(imgPath);
			
			System.out.println(imgPath);
			System.out.println(content[0]);
			System.out.println(content[1]);

		} else if (linkNum < 2) {
			Element e = link.get(0);
			String[] splitChar = e.html().split("</td>");
			Element e1 = e.select("td").first();
			String recallContent = e1.text();
			setReportContent(recallContent);
			setReportDate( commonTools.StringToDate(splitChar[1]));
			setImgURL(null);
			System.out.println(recallContent);
			System.out.println(splitChar[1]);
		}
	}

}
