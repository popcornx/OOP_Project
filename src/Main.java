import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * @author Lorenz Rasch & Nicole Scheffel
 */

/**
 * Contains GUI for weather forecast dashboard.
 */

public class Main extends Application {

	private static Forecast f;
	boolean error = false;
	Alert alert = new Alert(Alert.AlertType.ERROR);

	@Override
    public void init(){
        try {
            OWMLoader.loadOWMFile(OWMConstants.BERN);
        } catch (IOException e) {
            error = true;
            // Problems loading File
            alert.setTitle("Error");
            alert.setHeaderText("Failed loading file");
            alert.setContentText("Error while loading the file from external source. You may not be connected to the internet!");
        }
        try {
            f = OWMReader.readOWMFile(Main.class.getResource("/" + OWMConstants.BERN + ".xml").getFile(), "Bern");
        } catch (Exception e) {
            error = true;
            // Problems reading file
            alert.setTitle("Error");
            alert.setHeaderText("Failed loading file");
            alert.setContentText("Error while loading the file from disk.");
        }
    }

    @Override
    public void start(Stage stage){
        if(error) {
            alert.showAndWait();
            Platform.exit();
        }

		stage.setTitle("Weather Forecast for Bern");
		
    	BorderPane root = new BorderPane();

        Scene scene  = new Scene(root,1000,700);
    	
    	//Bottom
    	root.setBottom(new Label("Created by Lorenz Rasch & Nicole Scheffel"));

    	//Top
        Label top = new Label("Current Weather for Bern, Switzerland");
        top.setFont(new Font(32));
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().add(top);
        root.setTop(hbox);

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
        // TODO fill table
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
        //TODO center image in quadrant
        hbox = new HBox();
        hbox.setPrefSize(400, 300);
        hbox.setMinSize(200, 200);
        hbox.setMaxSize(400, 400);
        hbox.setAlignment(Pos.CENTER);
        Image image = new Image ("http://openweathermap.org/img/w/" + f.getWeather().get(0).getId() + ".png");
        ImageView img = new ImageView();
        img.setImage(image);
        img.setPreserveRatio(true);
        img.setFitWidth(100);
        hbox.getChildren().add(img);
        
        //Add everything to the flowPane in the center
        flowPane.getChildren().addAll(barChart, table, lineChart, hbox);
        
        stage.setScene(scene);
        stage.show();
	}

    /**
     * Recalls data from openweathermap.org and reads it.
     * Visualizes weather forecast data on a dashboard.
     * @param args Unused.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
