package com.example;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

public class FirstScene extends Application {
    ObservableList<FileData> data;
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Student Information Management");

        //Label for education
      Label label = new Label("Student Info:");
      Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12);
      label.setFont(font);
      //Creating a table view
      TableView<FileData> table = new TableView<FileData>();
       data = FXCollections.observableArrayList();
      StockInsert obj= new StockInsert();
      obj.stockListView(data);
      //Creating columns
      TableColumn fileNameCol = new TableColumn("Student ID");
      fileNameCol.setCellValueFactory(new PropertyValueFactory<>("fileName"));
      fileNameCol.setResizable(false);
      fileNameCol.prefWidthProperty().bind(table.widthProperty().multiply(0.25));

      TableColumn pathCol = new TableColumn("Student Name");
      pathCol.setCellValueFactory(new PropertyValueFactory("path"));
      pathCol.setResizable(false);
      pathCol.prefWidthProperty().bind(table.widthProperty().multiply(0.25));

      TableColumn sizeCol = new TableColumn("Ph.No");
      sizeCol.setResizable(false);
      sizeCol.setCellValueFactory(new PropertyValueFactory("size"));
      sizeCol.prefWidthProperty().bind(table.widthProperty().multiply(0.25));

      TableColumn dateCol = new TableColumn("Class");
      dateCol.setResizable(false);
      dateCol.setCellValueFactory(new PropertyValueFactory("dateModified"));
      dateCol.prefWidthProperty().bind(table.widthProperty().multiply(0.25));

      //Adding data to the table
      ObservableList<String> list = FXCollections.observableArrayList();
      table.setItems(data);
      table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
      table.getColumns().addAll(fileNameCol, pathCol, sizeCol, dateCol);
      //Setting the size of the table
      table.setMaxSize(700, 400);
       


        // Create the registration form grid pane
        GridPane gridPane = createRegistrationFormPane();
        // Add UI controls to the registration form grid pane
        addUIControls(gridPane);
        final VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.getChildren().addAll(gridPane,label,table);
        // Create a scene with registration form grid pane as the root node
        Scene scene = new Scene(vbox, 800, 500);
        // Set the scene in primary stage	
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }


    private GridPane createRegistrationFormPane() {
        // Instantiate a new Grid Pane
        GridPane gridPane = new GridPane();

        // Position the pane at the center of the screen, both vertically and horizontally
        gridPane.setAlignment(Pos.CENTER);

        // Set a padding of 20px on each side
        gridPane.setPadding(new Insets(40, 40, 40, 40));

        // Set the horizontal gap between columns
        gridPane.setHgap(10);

        // Set the vertical gap between rows
        gridPane.setVgap(10);

        // Add Column Constraints

        // columnOneConstraints will be applied to all the nodes placed in column one.
        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);

        // columnTwoConstraints will be applied to all the nodes placed in column two.
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;
    }

    private void addUIControls(GridPane gridPane) {
        // Add Header
        Label headerLabel = new Label("Student Info");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        // Add Name Label
        Label student_idLabel = new Label("Student_id : ");
        gridPane.add(student_idLabel, 0,1);

        // Add Name Text Field
        TextField Studentidtext = new TextField();
        
        Studentidtext.setPrefHeight(40);
        gridPane.add(Studentidtext, 1,1);


        // Add Email Label
        Label studentnameLabel = new Label("Student_name : ");
        gridPane.add(studentnameLabel , 0, 2);

        // Add Email Text Field
        TextField Studentnametext = new TextField();
        Studentnametext .setPrefHeight(40);
        gridPane.add(Studentnametext , 1, 2);

        // Add stockavailable Label
        Label phnoLabel = new Label("Ph.No :");
        gridPane.add(phnoLabel, 0, 3);

        // Add stockavailable Field
        TextField Phnotext = new TextField();
        Phnotext.setPrefHeight(40);
        gridPane.add(Phnotext, 1, 3);


        // Add retailer Label
        Label classLabel = new Label("Class :");
        gridPane.add(classLabel, 0, 4);

        // Add Password Field
        TextField Classtext = new TextField();
        Classtext.setPrefHeight(40);
        gridPane.add(Classtext, 1, 4);

        // Add Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        gridPane.add(submitButton, 0, 5, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0,20,0));

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(Studentidtext.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter ID");
                    return;
                }
                if(Studentnametext .getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter the Name");
                    return;
                }
                if(Classtext.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter Father_name");
                    return;
                }   
                if(Phnotext.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter pnno ");
                    return;
                }

                StockInsert obj = new StockInsert();
                String ans=obj.insertstudent(Studentidtext.getText(),Studentnametext.getText(), Phnotext.getText(), Classtext.getText());
                showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "info Update Successful!", "Product:" + Studentnametext.getText()+"\n"+ans);
                if(ans=="Success"){
                    Studentidtext.clear();
                    Studentnametext .clear();
                    Classtext.clear();
                    Phnotext.clear();
                }
                else{
                    System.out.println("Errr in insertion");
                }
                obj.stockListView(data);
            }
        });

        
    }

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}