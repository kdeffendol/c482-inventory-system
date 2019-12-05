/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem;

import javafx.collections.ObservableList;

/**
 * Inventory class : stores all Parts and Products objects
 * @author kelsey
 */
public class Inventory {
    private ObservableList allParts;
    private ObservableList allProducts;
    
    public void addPart(Part part) {
        
    }
    
    public void addProduct(Product product) {
        
    }
    
//    public Part lookupPart(int partID) {
//        
//    }
//    
//    public Product lookupProduct(int productID) {
//        
//    }
//    
//    public ObservableList lookupPart(String partName) {
//        
//    }
//    
//    public ObservableList lookupProduct(String productName) {
//        
//    }
    
    public void updateProduct(int index, Product product) {
        
    }
    
    public void updatePart(int index, Part part) {
        
    }
    
    public void deletePart(Part part) {
        
    }
    
    public void deleteProduct(Product product) {
        
    }
    
    public ObservableList getAllParts() {
        return allParts;
    }
    
    public ObservableList getAllProducts() {
        return allProducts;
    }
}
