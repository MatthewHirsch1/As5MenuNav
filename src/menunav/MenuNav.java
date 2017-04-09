/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menunav;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Matthew Hirsch
 * CSC 190
 * Assignment 5
 * 4/10/17
 */ 
public class MenuNav extends Application {
    int currScene = 0; // Global variable that sets the initial value of the current scene.
    
    @Override
    public void start(Stage primaryStage) {
        // Create an ArrayList of dishes to dynamically hold Dish objects
        ArrayList<Dish> arrDishes = new ArrayList<>();
        
        // Extract the Dish information from config.txt
        try {
            // Create an InputStream to read from the stream directory of "config.txt"
            InputStream inStream = MenuNav.class.getResourceAsStream("/resources/config.txt");
            // Create a Scanner to read from the InputStream
            Scanner fileIn = new Scanner(inStream);
	
            // Read every four lines of the input file and create a Dish object 
            // with a name, description, price, and image file. 
            while (fileIn.hasNext()) { 
                fileIn.skip("Name: "); // Skip over "Name: " in the config.txt file
                String dishName = fileIn.nextLine(); // The Dish's name
                fileIn.skip("Description: "); // Skip over "Description: " in the config.txt file
                String dishDesc = fileIn.nextLine(); // The Dish's description
                String dishPrice = fileIn.nextLine(); // The Dish's price
                fileIn.skip("Image: "); // Skip over "Image: " in the config.txt file
                String dishImage = fileIn.nextLine(); // The name of the image file
                // Create a new Dish object with the above information
                Dish dish = new Dish(dishName, dishDesc, dishPrice, dishImage);
                arrDishes.add(dish); // Add the Dish to the ArrayList of dishes
            }
            
            fileIn.close(); // Close the input stream
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
        
        // Load the first Dish scene
        nextDishScene(arrDishes, currScene, primaryStage);
    }

    // Load the next Dish scene to show the next Dish in the menu
    public static void nextDishScene(ArrayList<Dish> arrDishes, int currScene, Stage primaryStage){        
        GridPane gp = new GridPane(); // Create the grid pane
        // Add label for Dish name
        Label lblName = new Label();
        lblName.setText(arrDishes.get(currScene).name);
        gp.add(lblName, 0, 0, 2, 1);
        
        // Add label for Dish description
        Label lblDesc = new Label();
        lblDesc.setText(arrDishes.get(currScene).desc);
        gp.add(lblDesc, 0, 1);
        
        // Add label for Dish price
        Label lblPrice = new Label();
        lblPrice.setText(arrDishes.get(currScene).price);
        gp.add(lblPrice, 0, 2);
        
        // Input image from resources folder
        Image image = new Image("/resources/" + arrDishes.get(currScene).imageFile);
        ImageView imageV = new ImageView(image);
        imageV.setFitHeight(400); // Set height and width of image
        imageV.setFitWidth(600);
        gp.add(imageV, 0, 3);
        
        // Add Next button
        Button btnNext = new Button();
        btnNext.setText("Next >");
        gp.add(btnNext, 3, 4);
                
        // The action that happens when Next button is clicked
        btnNext.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                int numScene = arrDishes.size(); // The number of elements in the Dish Arraylist
                // Calculate the number of the next scene (if there are 4 scenes); 0->1, 1->2, 2->3, 3->0
                int nextScene = (currScene + 1) % (numScene);
                nextDishScene(arrDishes, nextScene, primaryStage); // Load the next Dish scene
            }
        });

        // Add Prev button
        Button btnPrev = new Button();
        btnPrev.setText("< Prev");
        gp.add(btnPrev, 0, 4);
        
        // The action that happens when Prev button is clicked
        btnPrev.setOnAction(new EventHandler<ActionEvent>() {
         
            @Override
            public void handle(ActionEvent event) {
                int numScene = arrDishes.size(); // The number of elements in the Dish Arraylist
                // Calculate the number of the previous scene (if there are 4 scenes); 0->3, 1->0, 2->1, 3->2
                int prevScene = (currScene + (numScene - 1)) % numScene; 
                nextDishScene(arrDishes, prevScene, primaryStage); // Load the previous Dish scene
            }
        });
        
        Scene scene = new Scene(gp, 700, 500); // Create a new scene for the current Dish
        primaryStage.setTitle("SmartRestaurant"); // Title the stage
        primaryStage.setScene(scene); // Add the scene to the stage
        primaryStage.show(); // Show the stage
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}