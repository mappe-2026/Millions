package edu.ntnu.idatt2003.gruppe50.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class NewGameView {

    private Stage stage;
    private BorderPane root;
    private List<File> selectedFiles = new ArrayList<>();
    private TextField nameField;
    private TextField capitalField;
    //lager en list in case man skal utvide
    //til å legge til flere filer i framtiden

    public NewGameView(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {
        root = new BorderPane();
        root.setStyle("-fx-background-color: #1C1C1C;");

        //Card
        VBox cardBox = new VBox();
        cardBox.setPrefWidth(860);
        cardBox.setMaxWidth(860);
        cardBox.setPrefHeight(420);
        cardBox.setMaxHeight(420);
        cardBox.setStyle("""
                -fx-background-color: #2C2C2C;
                -fx-background-radius: 12;
        """);
        cardBox.setAlignment(Pos.CENTER);
        cardBox.setPadding(new Insets(40, 32, 40, 32));
        cardBox.setSpacing(12);


        //Millions title
        Label millionsLabel = new Label("Millions");
        millionsLabel.setAlignment(Pos.TOP_CENTER);
        millionsLabel.setStyle("""
                -fx-font-size: 28px;
                -fx-font-weight: bold;
                -fx-text-fill: #FFFFFF;
                """);

        //Trading Game subtitle
        Label tradingGameLabel = new Label("Trading Game");
        tradingGameLabel.setAlignment(Pos.TOP_CENTER);
        tradingGameLabel.setStyle("""
                -fx-font-size: 14;
                -fx-text-fill: #888888;
                """);

        //Title box
        VBox titleBox = new VBox(2);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.getChildren().addAll(millionsLabel, tradingGameLabel);

        //Input fields
        VBox nameFieldBox = createInputField("Player name", "Enter name...");
        VBox startCapitalBox = createInputField("Starting Capital", "10 000kr");
        VBox filePickerBox = createFilePickerField("Enter .csv file...");

        //Start game
        Button startGameBtn = new Button("Start Game");
        startGameBtn.setStyle("""
            -fx-background-color: #2563EB;
            -fx-text-fill: #FFFFFF;
            -fx-background-radius: 8;
            -fx-padding: 12;
            -fx-font-size: 14px;
            -fx-cursor: hand;
        """);
        startGameBtn.setMaxWidth(Double.MAX_VALUE);



        cardBox.getChildren().addAll(titleBox, nameFieldBox, startCapitalBox, filePickerBox, startGameBtn);

        StackPane center = new StackPane(cardBox);
        root.setCenter(center);

        return new Scene(root, 1280, 900);
    }




    private VBox createInputField(String label, String placeholder) {

        // Container
        VBox box = new VBox(4);

        // Label
        Label fieldLabel = new Label(label);
        fieldLabel.setStyle("""
            -fx-text-fill: #FFFFFF;
            -fx-font-size: 14px;
            """);

        // Input field
        TextField field = new TextField();
        field.setPromptText(placeholder);
        field.setStyle("""
            -fx-background-color: #3C3C3C;
            -fx-text-fill: #FFFFFF;
            -fx-prompt-text-fill: #888888;
            -fx-background-radius: 8;
            -fx-padding: 10;
            -fx-border-color: #444444;
            -fx-border-radius: 8;
            -fx-border-width: 1;
            """);

        box.getChildren().addAll(fieldLabel, field);
        return box;
    }

    private VBox createFilePickerField(String labelText) {
        VBox box = new VBox(4);
        box.setMaxWidth(Double.MAX_VALUE);

        Label fieldLabel = new Label(labelText);
        fieldLabel.setStyle("""
            -fx-text-fill: #FFFFFF;
            -fx-font-size: 14px;
            """);

        HBox filePickerRow = new HBox(8);
        filePickerRow.setStyle("""
            -fx-background-color: #3C3C3C;
            -fx-background-radius: 8;
            -fx-padding: 10;
            """);
        filePickerRow.setAlignment(Pos.CENTER_LEFT);
        filePickerRow.setMaxWidth(Double.MAX_VALUE);


        Label fileNameLabel = new Label("Choose .csv file");
        fileNameLabel.setStyle("-fx-text-fill: #888888;");
        HBox.setHgrow(fileNameLabel, Priority.ALWAYS);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button addButton = getAddButton(fileNameLabel);

        filePickerRow.getChildren().addAll(fileNameLabel, spacer, addButton);
        box.getChildren().addAll(fieldLabel, filePickerRow);
        return box;
    }

    private Button getAddButton(Label fileNameLabel) {
        Button addButton = new Button("+");
        addButton.setStyle("""
            -fx-background-color: #4C4C4C;
            -fx-text-fill: #FFFFFF;
            -fx-background-radius: 6;
            -fx-border-color: transparent;
            -fx-cursor: hand;
            """);
        addButton.setOnAction(e -> {
            File file = handleFilePicker();
            if (file != null) {
                selectedFiles.add(file);
                fileNameLabel.setText(file.getName());
            }
        });
        return addButton;
    }

    private File handleFilePicker() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("CSV-filer", "*.csv")
        );
        return fileChooser.showOpenDialog(stage);
    }

}
