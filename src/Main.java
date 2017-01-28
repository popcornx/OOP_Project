import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
// TODO javadoc
/**
 * @author Lorenz Rasch & Nicole Scheffel
 */

public class Main extends Application {

	private Forecast f;

	@Override public void start(Stage stage) throws Exception{
		// TODO change to one city, load at start of program
		// TODO fill table
		// TODO get icons
		// TODO exception handling
		stage.setTitle("Weather Forecast");
		
    	BorderPane root = new BorderPane();

        Scene scene  = new Scene(root,1000,600);
    	
    	//Bottom
    	root.setBottom(new Label("Created by Lorenz Rasch & Nicole Scheffel"));
/*  	
   		final ScrollBar sc = new ScrollBar();
        sc.setLayoutX(scene.getWidth()-sc.getWidth());
        sc.setMin(0);
        sc.setOrientation(Orientation.VERTICAL);
        sc.setPrefHeight(50);
        sc.setMax(100); 
*/        

    	//Center
    	FlowPane flowPane = new FlowPane();
    	root.setCenter(flowPane);
    	
    	//BarChart Center
    	BarChart<String, Number> barChart = ChartLoader.loadPercipitationChart(f);
    	double height = barChart.prefHeight(flowPane.getMaxWidth()*0.5);
    	barChart.resize(flowPane.getMaxWidth()*0.5, height);
    	flowPane.getChildren().add(barChart);
    	
    	//LineChart Center
    	LineChart<String, Number> lineChart = ChartLoader.loadTemperatureChart(f);
    	flowPane.getChildren().add(lineChart);
    	
    	//TableView Center
    	final TableView<String> table = new TableView<String>();
    	table.setEditable(false);
    	 
        TableColumn<String, String> description = new TableColumn<String, String>("Parameter");
        TableColumn<String, String> number = new TableColumn<String, String>("Amount/Number");
        description.prefWidthProperty().bind(table.widthProperty().divide(2));
        number.prefWidthProperty().bind(table.widthProperty().divide(2));
         
        table.getColumns().addAll(description, number);
    	
        flowPane.getChildren().add(table);

    	//Top
    	HBox hboxTop = new HBox();
    	root.setTop(hboxTop);
        hboxTop.setAlignment(Pos.CENTER);
        
        stage.setScene(scene);
        stage.show();
	}

    public static void main(String[] args) {
        launch(args);
    }
}
