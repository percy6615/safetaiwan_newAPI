package safetaiwan_messageObject;

public class CoordinatesPoint {
	private double longitudeCoord;
	private double latitudeCoord;
	private double constant;
	private double multiple;
	public double getConstant() {
		return constant;
	}

	public void setConstant(double constant) {
		this.constant = constant;
	}

	public double getMultiple() {
		return multiple;
	}

	public void setMultiple(double multiple) {
		this.multiple = multiple;
	}

	public double getLongitudeCoord() {
		return longitudeCoord;
	}

	public void setLongitudeCoord(double longitudeCoord) {
		this.longitudeCoord = longitudeCoord;
	}

	public double getLatitudeCoord() {
		return latitudeCoord;
	}

	public void setLatitudeCoord(double d) {
		this.latitudeCoord = d;
	}

}