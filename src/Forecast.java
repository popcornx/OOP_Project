import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
// TODO javadoc
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
			return new Forecast(weather, city);
		}
	}
}
