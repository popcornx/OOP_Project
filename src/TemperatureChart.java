import java.util.Collections;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/**
 * @author Lorenz Rasch & Nicole Scheffel
 */

public class TemperatureChart extends Application{
	@Override public void start(Stage stage) {
        stage.setTitle("Temperature Forecast");
        //defining the axes
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Date and Time");
        yAxis.setLabel("Temperature in degree Celsius");
        
        //creating the chart
        final LineChart<String,Number> lineChart = 
            new LineChart<String,Number>(xAxis,yAxis);
        lineChart.setTitle("Temperature Forecast");
        
        
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Temperature in degree Celsius");
        
        //populating the series with data
        
        Forecast f =  new Forecast(Collections.emptyList(), "abc"); //test
	    //series.getData().add(new XYChart.Data(time, temperature));
	    for (int i = 0; i<8; i++){
	    	series.getData().add(new XYChart.Data(f.getWeather().get(i).getTime().toString(), f.getWeather().get(i).getTemperature()));	
	    }
        
        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);
       
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}