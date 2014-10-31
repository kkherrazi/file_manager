package application;
	
import java.io.File;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class Main extends Application {
	
	File directory;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			primaryStage.setTitle("File Manager");
	         
	        final Label labelFile = new Label();
	         
	        Button btn = new Button();
	        btn.setText("Choose root folder...");
	        
	        ListView<String> list = new ListView<String>();
	        
	        btn.setOnAction(new EventHandler<ActionEvent>() {
	 
	            @Override
	            public void handle(ActionEvent event) {
	            	DirectoryChooser chooser = new DirectoryChooser();
	                 
	                //Open directory from existing directory
	                if(directory != null){
	                    File existDirectory = directory.getParentFile();
	                    chooser.setInitialDirectory(existDirectory);
	                }
	 
	                //Set extension filter
	                //FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("AVI files (*.avi)", "*.avi");
	                File defaultDirectory = new File("c:/");
					chooser.setInitialDirectory(defaultDirectory);
					
				    //Show open file dialog
					directory = chooser.showDialog(primaryStage);
	                 
	                labelFile.setText(directory.getPath());
	                
	                ObservableList<String> items = FXCollections.observableArrayList (directory.list());
	                list.setItems(items);
	            }
	        });
	         
	        VBox vBox = new VBox();
	        vBox.getChildren().addAll(labelFile, btn, list);
	         
	        StackPane root = new StackPane();
	        root.getChildren().add(vBox);
	        primaryStage.setScene(new Scene(root, 600, 650));
	        primaryStage.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
