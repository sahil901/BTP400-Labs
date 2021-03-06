package btp400.lab3.q2;

import java.util.Random;

/**
 * Model of a weather reading.
 * 
 * In this lab we store this entity to practice sorting and 
 * multi-threaded programming techniques.  You can imagine
 * such information coming from a sensing device. 
 *    
 * 
 * @author EDEN.BURTON
 *
 */
public class Reading {

	/** unique identifier for each reading */
	int id;
	
	/** region of the country */
	Reading.Location loc;
	
	/** temperature reading */
	double temp;
	
	/** windspeed reading */
	double windspeed;
	
	/** maximum temperature sensor will output */	
	static public final double MAX_TEMP = 50;
	
	/** minimum temperature sensor will output */
	static public final double MIN_TEMP = -40;	

	/** maximum windspeed sensor will output */
	static public final double MAX_WIND = 150;

	/** minimum windspeed sensor will output */	
	static public final double MIN_WIND = 0;	
	
	/** seed used to generate unique reading identifiers */ 
	static private int currentID = 0;
	
	/** generate a new identifier, it is thread-safe */
	static private synchronized int getID() {
		currentID++;
		return currentID;
	}
	
	/** 
	 * 
	 * private constructor.  By making this constructor private, users 
	 * can not create objects directly.  In this class, to create an
	 * object, you must use the createObject method.
	 * 
	 * @param l - location to be stored
	 * @param t - temperature to be stored
	 * @param w - windspeed to be stored
	 */
	private Reading(Reading.Location l, double t, double w) {
		
		// id is generated by the system
		id = Reading.getID();
				
		this.setLocation(l);
		this.setTemperature(t);
		this.setWindspeed(w);
	}
	
	
	/**
	 * used to randomly select a location. Use when to want to 
	 * create data for test purposes.
	 * 
	 * @return - location object
	 */
	private static Location getRandomLocation() {
		Random r = new Random();
		switch (r.nextInt(3)) {
			case 0:
				return Location.NW;
			case 1:
				return Location.NE;
			case 2:
				return Location.SW;
			case 3:
				return Location.SE;
			default:
				return Location.SE;	
		}
	}
	
	// instance variable accessors and mutators
	public Reading.Location getLocation() { return loc; };		
	public void setLocation(Reading.Location l) {loc = l;}

	public int getIdentifier() { return id; };		
	public void setIdentifier(int i) {id = i;}
	
	
	public double getTemperature() { return temp; };
	
	
	/**
	 * set the temperature reading if it is in range (between MIN_TEMP and MAX_TEMP).  
	 * The temperature reading remains unchanged if an out of range value is given
	 * 
	 * @param t, temparature reading to be set
	 * @throws IllegalArgumentException if argument t is out of range
	 */
	public void setTemperature(double t) { 
		if ((t < MIN_TEMP) || (t > MAX_TEMP)) throw new IllegalArgumentException("temperature argument is out of range:" + t);		
		temp = t; 
	};

	public double getWindspeed() { return windspeed; };
	
	
	/**
	 * set the wind speed reading if it is in range (between MIN_WIND and MAX_WIND).  
	 * The wind speed reading remains unchanged if an out of range value is given
	 * 
	 * @param t, wind speed reading to be set
	 * @throws IllegalArgumentException if argument t is out of range
	 */	
	public void setWindspeed(double w) { 
		if ((w < MIN_WIND) || (w > MAX_WIND)) throw new IllegalArgumentException("wind speed argument is out of range" + w);	
		windspeed = w; 
	};

	/**
	 * used instead of a "new" call + constructor to create and initialize 
	 * objects.  In this case, this ensures that all objects are created
	 * using random data.
	 * 
	 * @return newly created Reading object
	 * 
	 */
	public static Reading createObject () {	
		return new Reading(
				Reading.getRandomLocation(),
				Math.floor((Math.random() * (Reading.MAX_TEMP - Reading.MIN_TEMP) + 1) + Reading.MIN_TEMP),
				Math.floor((Math.random() * (Reading.MAX_WIND - Reading.MIN_WIND) + 1) + Reading.MIN_WIND)
		);
	}
	
	/**
	 * 
	 * Standard string representation of the object's state.
	 * 
	 */
	public String toString() {
		return
				"reading id: " + this.id +
				" loc: " + this.loc.getString() +
				" windspeed: " + this.windspeed + " km/h" + 
				" temparature: " + this.temp + " degrees";		
	}
	
	
	/**
	 * 
	 * Enumeration which represents regions in a fictional country.
	 * 
	 * @author EDEN.BURTON
	 *
	 */
	public enum Location { 
		
		NW(1,"North West"),
		NE(2, "North East"),
		SW(3, "South West"),
		SE(4, "South East");
		
		private final int locId;
		private final String locString;
		
		Location(int l, String s) { 
			locId = l; 
			locString = s;
		};
		
		public int getId() { return locId; }
		public String getString() { return locString; }
		
	}	
	
};

	
	
	



