import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * @author Lorenz Rasch & Nicole Scheffel
 */

/**
 * Contains GUI for weather forecast dashboard.
 */

public class Main extends Application {

    public static final int ID = 2661552;
    public static final String CITY = "Bern";

	private static Forecast f;
	private boolean loadingError = false;
	private Alert loadingAlert = new Alert(Alert.AlertType.ERROR);

	@Override
    public void init() throws InterruptedException {
        try {
            f = OWMReader.readOWMFile(ID, CITY);
        } catch (SAXException e) {
            loadingError = true;
            // Problems loading File
            loadingAlert.setTitle("Error");
            loadingAlert.setHeaderText("Failed reading file");
            loadingAlert.setContentText("Error while reading the file!");
        } catch (IOException e) {
            loadingError = true;
            // Problems loading File
            loadingAlert.setTitle("Error");
            loadingAlert.setHeaderText("Failed loading file");
            loadingAlert.setContentText("Error while loading the file from external source. You may not be connected to the internet!");
        }
    }

    @Override
    public void start(Stage stage){
        if(loadingError) {
            loadingAlert.showAndWait();
            System.exit(0);
        }

		stage.setTitle("Weather Forecast for " + f.getCity());
		
    	BorderPane root = new BorderPane();

        Scene scene  = new Scene(root,1000,700);
    	
    	//Bottom
    	root.setBottom(new Label("Created by Lorenz Rasch & Nicole Scheffel"));

    	//Top
        Label top = new Label("Current Weather for " + f.getCity());
        top.setFont(new Font(32));
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().add(top);
        root.setTop(hbox);

    	//Center
    	FlowPane flowPane = new FlowPane();
    	flowPane.setPadding(new Insets(10, 0, 0, 0));
    	root.setCenter(flowPane);
    	
    	//BarChart Center
    	BarChart<String, Number> barChart = ChartLoader.loadPrecipitationChart(f);
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
    	final TableView<DataItem> table = new TableView<DataItem>();
    	table.setEditable(false);
    	table.setPrefSize(500, 300);
    	table.setMinSize(400, 200);
    	table.setMaxSize(600, 400);

        TableColumn<DataItem, String> description = new TableColumn<DataItem, String>("Parameter");
        TableColumn<DataItem, String> value = new TableColumn<DataItem, String>("Amount/Number");

        //add data to table
        Weather w = f.getWeather().get(0);
        ObservableList<DataItem> data = FXCollections.observableArrayList(
                new DataItem("City", f.getCity()),
                new DataItem("Time and Date", w.getTime().toString().replace("T", ", ")),
                new DataItem("Current Temperature",w.getTemperature() + "°C"),
                new DataItem("Cloudiness", "" + w.getCloudiness() + "%"),
                new DataItem("Humidity", w.getHumidity() + "%"),
                new DataItem("Pressure",w.getPressure() + " hpa"),
                new DataItem("Windspeed", w.getWind() + " m/s")
        );

        description.setCellValueFactory(new PropertyValueFactory<DataItem, String>("desc"));
        value.setCellValueFactory(new PropertyValueFactory<DataItem, String>("val"));
        table.setItems(data);

        //Change width
        description.prefWidthProperty().bind(table.widthProperty().divide(2));
        value.prefWidthProperty().bind(table.widthProperty().divide(2));
         
        table.getColumns().addAll(description, value);

        //Icon
        hbox = new HBox();
        hbox.setPrefSize(500, 300);
        hbox.setMinSize(400, 200);
        hbox.setMaxSize(600, 400);
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

    public class DataItem {
        private SimpleStringProperty desc;
        private SimpleStringProperty val;

        public DataItem(String desc, String val){
            this.desc = new SimpleStringProperty(desc);
            this.val = new SimpleStringProperty(val);
        }

        public String getDesc(){
            return desc.get();
        }

        public String getVal(){
            return val.get();
        }

        public void setDesc(String desc){
            this.desc.set(desc);
        }

        public void setVal(String val){
            this.val.set(val);
        }
    }
}
