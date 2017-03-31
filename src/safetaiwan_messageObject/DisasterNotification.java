package safetaiwan_messageObject;

import java.util.List;

public class DisasterNotification {
	String name;
	List<CoordinatesPoint> coordinatesPoints;
	String description;
	String iconID;
	public DisasterNotification(){
		
	}
	public DisasterNotification(DisasterNotification disasterNotification){
		this.name = disasterNotification.getName();
		this.coordinatesPoints = disasterNotification.getCoordinatesPoints();
		this.description = disasterNotification.getDescription();
		this.iconID = disasterNotification.getIconID();
	}
	public DisasterNotification(String name,List<CoordinatesPoint> coordinatesPoint,String description,String iconID){
		this.name = name;
		this.coordinatesPoints = coordinatesPoint;
		this.description = description;
		this.iconID = iconID;
	}
	public String getIconID() {
		return iconID;
	}

	public void setIconID(String iconID) {
		this.iconID = iconID;
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

	
}
