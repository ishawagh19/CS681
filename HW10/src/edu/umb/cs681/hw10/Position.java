package edu.umb.cs681.hw10;

public final class Position {
	private double lat=0;
	private double longi=0;
	private double alt=0;

	public Position(double lat,double longi,double alt){
		this.lat=lat;
		this.longi=longi;
		this.alt=alt;
		
	}
	
	public double getlat() {
		return lat;
	}
	
	public double getlong() {
		return longi;
	}
	
	public double getalt() {
		return alt;
	}
	
	public String toString() {
		return this.lat + "-" + this.longi +"-" + this.alt;
	}
	
	public boolean equals(Position anotherPosition) {
		if(this.toString().equals(anotherPosition.toString())) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public Position change(double lat,double longi,double alt) {
		return new Position(lat,longi,alt);
	}
}
