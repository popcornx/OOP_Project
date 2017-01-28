import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;
// TODO javadoc
/**
 * @author Lorenz Rasch & Nicole Scheffel
 */

public class Main extends Application {

	private static Forecast f;

	@Override public void start(Stage stage){
		// TODO fill table
		// TODO get icons
		// TODO exception handling
		stage.setTitle("Weather Forecast for Bern");
		
    	BorderPane root = new BorderPane();

        Scene scene  = new Scene(root,1000,700);
    	
    	//Bottom
    	root.setBottom(new Label("Created by Lorenz Rasch & Nicole Scheffel"));

    	//Top
        root.setTop(new Label("Current Weather for Bern, Switzerland"));
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
    	barChart.setPrefSize(500, 300);
    	barChart.setMinSize(400, 200);
    	barChart.setMaxSize(600, 400);
    	
    	//LineChart Center
    	LineChart<String, Number> lineChart = ChartLoader.loadTemperatureChart(f);
    	lineChart.setPrefSize(500, 300);
    	lineChart.setMinSize(400, 200);
    	lineChart.setMaxSize(600, 400);
    	
    	//TableView Center
    	final TableView<String> table = new TableView<String>();
    	table.setEditable(false);
    	table.setPrefSize(400, 300);
    	table.setMinSize(200, 200);
    	table.setMaxSize(400, 400);
    	
        TableColumn<String, String> description = new TableColumn<String, String>("Parameter");
        TableColumn<String, String> number = new TableColumn<String, String>("Amount/Number");
        //Change width
        description.prefWidthProperty().bind(table.widthProperty().divide(2));
        number.prefWidthProperty().bind(table.widthProperty().divide(2));
         
        table.getColumns().addAll(description, number);

        //Icon
        Image image = new Image ("http://openweathermap.org/img/w/" + f.getWeather().get(0).getId() + ".png");
        ImageView img = new ImageView();
        img.setImage(image);
        img.setPreserveRatio(true);
        img.setFitWidth(100);
        
        //Add everything to the flowPane in the center
        flowPane.getChildren().addAll(barChart, table, lineChart, img);
        
        stage.setScene(scene);
        stage.show();
	}

    public static void main(String[] args) {
        try {
            OWMLoader.loadOWMFile(OWMConstants.BERN);
        } catch (IOException e) {
            // Problems loading File
            System.out.println("Failed loading file!");
        }
        try {
            f = OWMReader.readOWMFile(Main.class.getResource("/" + OWMConstants.BERN + ".xml").getFile(), "Bern");
        } catch (Exception e) {
            // Problems reading file
            System.out.println("Failed reading file!");
        }
        launch(args);
    }
}
