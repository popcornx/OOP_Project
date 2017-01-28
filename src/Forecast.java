import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * @author Lorenz Rasch & Nicole Scheffel
 */

public final class Forecast {

	private final static int LIMIT = 8;
	
	private final List<Weather> weather;
	private final String city;
	
	/**
	 * Creates a forecast object that contains all forecast data for the next 24 hours and the city code for the corresponding city
	 * @param weather weather object that contains all forecast data from Openweathermap.org
	 * @param city city code of the city Berne
	 */

	public Forecast(List<Weather> weather, String city){
		this.weather = Collections.unmodifiableList(new ArrayList<Weather>(weather));
		this.city = city;
	}

	public List<Weather> getWeather() {
		return weather;
	}

	public String getCity() {
		return city;
	}

	public static class Builder{

		String city;
		List<Weather> weather;

		public Builder(String city){
			this.city = city;
			weather = new ArrayList<Weather>();
		}

		public void addWeather(Weather w){
			weather.add(w);
		}

		public Forecast build(){
			return new Forecast(weather.subList(0,LIMIT), city);
		}
	}
}
