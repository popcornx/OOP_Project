import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
/**
 * @author Lorenz Rasch & Nicole Scheffel
 */

public class ChartLoader {
	
	private ChartLoader(){};

	/**
	 * Creates a bar chart that visualizes precipitation data.
	 * There must be a forecast object containing time and precipitation data.
	 * @param f forecast object that contains weather data and a city code.
	 * @return returns a bar chart that visualizes the weather forecast data. 
	 */
	
	public static BarChart<String, Number> loadPercipitationChart(Forecast f){
		//defining the axes
	    final Axis<String> xAxis = new CategoryAxis();
	    final Axis<Number> yAxis = new NumberAxis();
	    xAxis.setLabel("Date and Time");
	    yAxis.setLabel("Precipitation in mm");

		//creating the chart
		final BarChart<String,Number> barChart =
				new BarChart<String,Number>(xAxis,yAxis);
		barChart.setTitle("Precipitation Forecast");
	    barChart.setLegendVisible(false);
	    
		try {
			//compiling list of data
			List<XYChart.Data<String, Number>> list = new ArrayList<XYChart.Data<String, Number>>();
			for (int i = 0; i < 8; i++) {
				list.add(new XYChart.Data<String, Number>(f.getWeather().get(i).getTime().toString(), f.getWeather().get(i).getPrecipitation()));
			}
			XYChart.Series<String, Number> series = new XYChart.Series<String, Number>(FXCollections.observableArrayList(list));

			//adding data
			barChart.getData().add(series);
		} //TODO exception handling
		catch (NullPointerException e){}
	    
	    for(Node n:barChart.lookupAll(".default-color0.chart-bar")) {
            n.setStyle("-fx-bar-fill: blue;");
        }
	    
	    return barChart;
	}
	
	/**
	 * Creates a line chart that visualizes temperature data.
	 * There must be a forecast object containing time and temperature data.
	 * @param f forecast object that contains weather data and a city code.
	 * @return returns a line chart that visualizes the weather forecast data.
	 */
	public static LineChart<String, Number> loadTemperatureChart(Forecast f){
		 //defining the axes
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Date and Time");
        yAxis.setLabel("Temperature in degree Celsius");

		//creating the chart
		final LineChart<String,Number> lineChart =
				new LineChart<String,Number>(xAxis,yAxis);
		lineChart.setTitle("Temperature Forecast");
	    lineChart.setLegendVisible(false);
	    
        try {
			//compiling list of data
			List<XYChart.Data<String, Number>> list = new ArrayList<XYChart.Data<String, Number>>();
			for (int i = 0; i < 8; i++) {
				list.add(new XYChart.Data<String, Number>(f.getWeather().get(i).getTime().toString(), f.getWeather().get(i).getTemperature()));
			}
			XYChart.Series<String, Number> series = new XYChart.Series<String, Number>(FXCollections.observableArrayList(list));

			//add data
			lineChart.getData().add(series);
		} //TODO exception handling
		catch (NullPointerException e){}
		
		return lineChart;
	}
}
