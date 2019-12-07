/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem;

import javafx.collections.ObservableList;

/**
 *
 * @author kelsey
 */
public class Product {
    private ObservableList associatedParts;
    int id;
    String name;
    double price;
    int stock;
    int min;
    int max;
    private static int nextProductId = 1;

    /**
     * Product constructor - creates a new Product
     * @param id - product id 
     * @param name - name of product
     * @param price - price of product
     * @param stock - number of given product in inventory
     * @param min - minimum stock needed
     * @param max - maximum stock inventory can hold
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }
    
    private static int getNextProductId() {
        return nextProductId++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
    
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }
    
    public void deleteAssociatedPart(Part part) {
        associatedParts.remove(part);
    }
    
    public ObservableList getAllAssociatedParts() {
        return associatedParts;
    }
  
}
