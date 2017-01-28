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
 * Class to create Charts from data.
 * @author Lorenz Rasch & Nicole Scheffel
 */

public class ChartLoader {
	
	private ChartLoader(){};

	/**
	 * Creates a bar chart that visualizes precipitation data.
	 * There must be a forecast object containing time and precipitation data.
	 * @param f forecast object that contains weather data.
	 * @return returns a bar chart that visualizes the weather forecast data. 
	 */
	
	public static BarChart<String, Number> loadPrecipitationChart(Forecast f){
		//defining the axes
	    final Axis<String> xAxis = new CategoryAxis();
	    final Axis<Number> yAxis = new NumberAxis();
	    xAxis.setLabel("Time");
	    yAxis.setLabel("Precipitation in mm");
	    
	    
		//creating the chart
		final BarChart<String,Number> barChart =
				new BarChart<String,Number>(xAxis,yAxis);
		barChart.setTitle("Precipitation Forecast");
	    barChart.setLegendVisible(false);
	    
        //compiling list of data
        List<XYChart.Data<String, Number>> list = new ArrayList<XYChart.Data<String, Number>>();
        for (int i = 0; i < 8; i++) {
            Weather w = f.getWeather().get(i);
            list.add(new XYChart.Data<String, Number>(w.getTime().toString().substring(11), w.getPrecipitation()));
        }
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>(FXCollections.observableArrayList(list));

        //adding data
        barChart.getData().add(series);

        //change color of bars
	    for(Node n:barChart.lookupAll(".default-color0.chart-bar")) {
            n.setStyle("-fx-bar-fill: blue;");
        }
	    
	    return barChart;
	}
	
	/**
	 * Creates a line chart that visualizes temperature data.
	 * There must be a forecast object containing time and temperature data.
	 * @param f forecast object that contains weather data.
	 * @return returns a line chart that visualizes the weather forecast data.
	 */
	public static LineChart<String, Number> loadTemperatureChart(Forecast f){
		 //defining the axes
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Time");
        yAxis.setLabel("Temperature in \u00BAC");

		//creating the chart
		final LineChart<String,Number> lineChart =
				new LineChart<String,Number>(xAxis,yAxis);
		lineChart.setTitle("Temperature Forecast");
	    lineChart.setLegendVisible(false);
	    
        //compiling list of data
        List<XYChart.Data<String, Number>> list = new ArrayList<XYChart.Data<String, Number>>();
        for (int i = 0; i < 8; i++) {
            Weather w = f.getWeather().get(i);
            list.add(new XYChart.Data<String, Number>(w.getTime().toString().substring(11), w.getTemperature()));
        }
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>(FXCollections.observableArrayList(list));

        //add data
        lineChart.getData().add(series);

		return lineChart;
	}
}
