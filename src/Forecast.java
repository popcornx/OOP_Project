import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Lorenz Rasch & Nicole Scheffel
 */


public final class Forecast {
	
	final List<Weather> weather;
	final String city;
	
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
	
}
