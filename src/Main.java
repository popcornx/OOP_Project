import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

/**
 * @author Lorenz Rasch & Nicole Scheffel
 */

public class Main extends Application {

	private Forecast f;
	private final static List cityList = Arrays.asList(OWMCity.values());

	@Override public void start(Stage stage) throws Exception{
		
		stage.setTitle("Weather Forecast");
		
    	BorderPane root = new BorderPane();
    	root.setBottom(new Label("Created by Lorenz Rasch & Nicole Scheffel"));
    	
    	//Center
    	FlowPane flowPane = new FlowPane();
    	root.setCenter(flowPane);
    	
    	BarChart<String, Number> barChart = ChartLoader.loadPercipitationChart(f);
    	double height = barChart.prefHeight(flowPane.getMaxWidth()*0.5);
    	barChart.resize(flowPane.getMaxWidth()*0.5, height);
    	flowPane.getChildren().add(barChart);
    	
    	LineChart<String, Number> lineChart = ChartLoader.loadTemperatureChart(f);
    	flowPane.getChildren().add(lineChart);
    	
    	//Top
    	HBox hboxTop = new HBox();
    	root.setTop(hboxTop);
        hboxTop.setAlignment(Pos.CENTER);

		ChoiceBox<String> choiceBox = new ChoiceBox<String>(FXCollections.observableArrayList(cityList));
        //Set a default value
        choiceBox.setValue("Bern");
        
        hboxTop.getChildren().add(choiceBox);

        Scene scene  = new Scene(root,1000,700);
  
        stage.setScene(scene);
        stage.show();
	}

    public static void main(String[] args) {
        launch(args);
    }

}
