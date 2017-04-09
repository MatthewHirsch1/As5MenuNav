/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menunav;

/**
 *
 * @author Matthew Hirsch
 * CSC 190
 * Assignment 5
 * 4/10/17
 */
// Create a Dish class to store information about each dish
public class Dish{ 
    String name; // Dish's name
    String desc; // Dish's description
    String price; // Dish's price
    String imageFile; // File name of the dish image
    // Constructor for Dish class: it takes 4 parameters
    Dish(String name, String desc, String price, String imageFile){
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.imageFile = imageFile;
    } 
}