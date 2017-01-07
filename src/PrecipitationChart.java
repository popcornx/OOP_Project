import java.util.Collections;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
	
/**
 * @author Lorenz Rasch & Nicole Scheffel
 */

public class PrecipitationChart extends Application{

	final static String time1 = "7.Januar";
	final static String time2 = "8.Januar";
	final static String time3 = "9.Januar";
	final static String time4 = "10.Januar";
	final static String time5 = "11.Januar";
	 
	@Override public void start(Stage stage) {
		stage.setTitle("Precipitation Forecast");
		//defining the axes
	    final CategoryAxis xAxis = new CategoryAxis();
	    final NumberAxis yAxis = new NumberAxis();
	    xAxis.setLabel("Date and Time");       
	    yAxis.setLabel("Precipitation in mm");
	    
	    //creating the chart
	    final BarChart<String,Number> barChart = 
	        new BarChart<String,Number>(xAxis,yAxis);
	    barChart.setTitle("Precipitation Forecast");
	    
	    //defining a series
	    XYChart.Series series = new XYChart.Series();
	    series.setName("Precipitation Forecast");
	    
	    //populating the series with data
	    Forecast f =  new Forecast(Collections.emptyList(), "abc");//test
	    
	    //series.getData().add(new XYChart.Data(time, precipitation));
	    for (int i = 0; i<8; i++){
	    	series.getData().add(new XYChart.Data(f.getWeather().get(i).getTime().toString(), f.getWeather().get(i).getPrecipitation()));	
	    } 
	    
	    Scene scene  = new Scene(barChart,800,600);
	    barChart.getData().add(series);
	    
	    stage.setScene(scene);
	    stage.show();
	    
	    for(Node n:barChart.lookupAll(".default-color0.chart-bar")) {
            n.setStyle("-fx-bar-fill: blue;");
        }
	}
	 
	public void main(String[] args) {
	    launch(args);
	}
}