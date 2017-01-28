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
	
	public static BarChart<String, Number> loadPrecipitationChart(Forecast f){
		//defining the axes
	    final Axis<String> xAxis = new CategoryAxis();
	    final Axis<Number> yAxis = new NumberAxis();
	    xAxis.setLabel("Time");
	    yAxis.setLabel("Precipitation in mm");
	  //  xAxis.setTickLabelRotation(60);
	  //  xAxis.tickLabelFontProperty().set(Font.font(7));
	    
	    
		//creating the chart
		final BarChart<String,Number> barChart =
				new BarChart<String,Number>(xAxis,yAxis);
		barChart.setTitle("Precipitation Forecast");
	    barChart.setLegendVisible(false);
	    
        //compiling list of data
        List<XYChart.Data<String, Number>> list = new ArrayList<XYChart.Data<String, Number>>();
        String time = new String();
        for (int i = 0; i < 8; i++) {
        	time = f.getWeather().get(i).getTime().toString();
            list.add(new XYChart.Data<String, Number>(time.substring(11), f.getWeather().get(i).getPrecipitation()));
        }
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>(FXCollections.observableArrayList(list));

        //adding data
        barChart.getData().add(series);

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
        xAxis.setLabel("Time");
        yAxis.setLabel("Temperature in �C");

		//creating the chart
		final LineChart<String,Number> lineChart =
				new LineChart<String,Number>(xAxis,yAxis);
		lineChart.setTitle("Temperature Forecast");
	    lineChart.setLegendVisible(false);
	    
        //compiling list of data
        List<XYChart.Data<String, Number>> list = new ArrayList<XYChart.Data<String, Number>>();
        String time = new String();
        for (int i = 0; i < 8; i++) {
        	time = f.getWeather().get(i).getTime().toString();
            list.add(new XYChart.Data<String, Number>(time.substring(11), f.getWeather().get(i).getTemperature()));
        }
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>(FXCollections.observableArrayList(list));

        //add data
        lineChart.getData().add(series);

		return lineChart;
	}
}
