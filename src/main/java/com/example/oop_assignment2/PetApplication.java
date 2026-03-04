package com.example.oop_assignment2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.InputStream;

public class PetApplication extends Application {

    private ImageView petImageView;

    @Override
    public void start(Stage stage) {
        // Create radio buttons
        RadioButton birdBtn = new RadioButton("Bird");
        RadioButton catBtn = new RadioButton("Cat");
        RadioButton dogBtn = new RadioButton("Dog");
        RadioButton rabbitBtn = new RadioButton("Rabbit");
        RadioButton pigBtn = new RadioButton("Pig");

        // Group the buttons
        ToggleGroup group = new ToggleGroup();
        birdBtn.setToggleGroup(group);
        catBtn.setToggleGroup(group);
        dogBtn.setToggleGroup(group);
        rabbitBtn.setToggleGroup(group);
        pigBtn.setToggleGroup(group);

        // Image view for pet display
        petImageView = new ImageView();
        petImageView.setFitWidth(400);
        petImageView.setFitHeight(300);
        petImageView.setPreserveRatio(true);

        // Selection handling
        group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (group.getSelectedToggle() != null) {
                RadioButton selectedBtn = (RadioButton) group.getSelectedToggle();
                String petName = selectedBtn.getText();
                updatePetDisplay(petName);
                showSelectionMessage(petName);
            }
        });

        // Layout
        VBox rbBox = new VBox(10, birdBtn, catBtn, dogBtn, rabbitBtn, pigBtn);
        rbBox.setPadding(new Insets(20));
        rbBox.setAlignment(Pos.CENTER_LEFT);

        HBox mainLayout = new HBox(30, rbBox, petImageView);
        mainLayout.setPadding(new Insets(25));
        mainLayout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(mainLayout, 700, 450);
        stage.setTitle("RadioButtonDemo");
        stage.setScene(scene);
        stage.show();

        // Default selection
        pigBtn.setSelected(true);
    }

    private void updatePetDisplay(String petName) {
        String imagePath = "/com/example/oop_assignment2/images/" + petName.toLowerCase() + ".jpeg";
        InputStream is = getClass().getResourceAsStream(imagePath);
        if (is != null) {
            petImageView.setImage(new Image(is));
        } else {
            // Placeholder if image not found
            System.err.println("Resource not found: " + imagePath);
        }
    }

    private void showSelectionMessage(String petName) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pet Selected");
        alert.setHeaderText(null);
        alert.setContentText("You have selected: " + petName);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}
