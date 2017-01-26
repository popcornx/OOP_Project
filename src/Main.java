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

/**
 * @author Lorenz Rasch & Nicole Scheffel
 */

public class Main extends Application {

	@Override public void start(Stage stage) throws Exception{
		
		stage.setTitle("Weather Forecast");
		
    	BorderPane root = new BorderPane();
        Scene scene  = new Scene(root,1000,600);
        
    	Forecast f = OWMReader.readOWMFile(getClass().getResource("/forecast.xml").getFile(), "Bern");
    	
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
    	final TableView table = new TableView();
    	table.setEditable(false);
    	 
        TableColumn description = new TableColumn("Parameter");
        TableColumn number = new TableColumn("Amount/Number");
        description.prefWidthProperty().bind(table.widthProperty().divide(2));
        number.prefWidthProperty().bind(table.widthProperty().divide(2));
         
        table.getColumns().addAll(description, number);
    	
        flowPane.getChildren().add(table);

    	//Top
    	HBox hboxTop = new HBox();
    	root.setTop(hboxTop);
        hboxTop.setAlignment(Pos.CENTER);
        
    	//ChoiceBox Top
    	ChoiceBox<String> choiceBox = new ChoiceBox<String>(FXCollections.observableArrayList(
    			"Bern", "Biel", "Basel", "Zuerich", "Genf", "Luzern")
    	);
        //Set a default value
        choiceBox.setValue("Bern");
        hboxTop.getChildren().add(choiceBox);
        
        stage.setScene(scene);
        stage.show();
	}

    public static void main(String[] args) {
        launch(args);
    }
}
