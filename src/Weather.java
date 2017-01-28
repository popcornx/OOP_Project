import java.time.LocalDateTime;
//TODO javadoc
/**
 * @author Lorenz Rasch & Nicole Scheffel
 */

public final class Weather {
	final double temperature;  //temperature in Celsius
	//final double pressure;  //hpa
	final LocalDateTime time;  //time in Switzerland
	//final double humidity;  //%
	//final double cloudiness;  //%
	//final double wind;  // m/s
	final double precipitation; //= Niederschlag, volume in mm for the last 3 hours
	final String id;  //id tag of weather icon
	
	//public Weather(double temperature, double pressure, LocalDateTime time, double humidity, double cloudiness, double wind, double precipitation, String id){
    public Weather(double temperature, LocalDateTime time, double precipitation, String id){
    	// TODO check arguments
        this.temperature = temperature;
		//this.pressure = pressure;
		this.time = time;
		//this.humidity = humidity;
		//this.cloudiness = cloudiness;
		//this.wind = wind;
		this.precipitation = precipitation;
		this.id = id;
		
	}

	public double getTemperature() {
		return temperature;
	}

	/*public double getPressure() {
		return pressure;
	}*/

	public LocalDateTime getTime() {
		return time;
	}

	/*public double getHumidity() {
		return humidity;
	}*/

	/*public double getCloudiness() {
		return cloudiness;
	}*/

	/*public double getWind() {
		return wind;
	}*/

	public double getPrecipitation() {
		return precipitation;
	}

	public String getId() {
		return id;
	}
    
	public static class Builder{
		double temperature = Double.MIN_VALUE;  //temperature in Celsius
//		double pressure = Integer.MIN_VALUE;  //hpa
		LocalDateTime time = LocalDateTime.MIN;  //time in Switzerland
//		double humidity = Double.MIN_VALUE;  //%
//		double cloudiness = Double.MIN_VALUE;  //%
//		double wind = Double.MIN_VALUE;  // m/s
		double precipitation = 0.0; //= Niederschlag, volume in mm for the last 3 hours
		String id;  //id tag of weather icon
		
		public Builder(){}
		
		public void addTemp(double temp){
			temperature = temp;
		}
		
		/*public void addPress(double press){
			pressure = press;
		}*/
		
		public void addTime(LocalDateTime time){
			this.time = time;
		}
		
		/*public void addHumidity(double humidity){
			this.humidity = humidity;
		}*/
		
		/*public void addCloud(double cloud){
			cloudiness = cloud;
		}*/
		
		/*public void addWind(double wind){
			this.wind = wind;
		}*/
		
		public void addPreci(double preci){
			precipitation = preci;
		}
		
		public void addID(String id){
			this.id = id;
		}
		
		public Weather build(){
//            return new Weather(temperature, pressure, time, humidity, cloudiness, wind, precipitation, id);
			return new Weather(temperature, time, precipitation, id);
		}
	}
}
