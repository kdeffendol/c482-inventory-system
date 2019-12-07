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
    private static ObservableList<Part> allParts;
    private static ObservableList<Product> allProducts;
    
    public void addPart(Part part) {
        
    }
    
    public void addProduct(Product product) {
        
    }
    
    /**
     * Return a part object from given partId
     * @param partId
     * @return Part object that matches given partId
     */
    public Part lookupPart(int partId) {
        for (Part p : allParts) {
            if (p.getId() == partId) {
                return p;
            }
        }
        return null;
    }
    
    public Product lookupProduct(int productId) {
        for (Product p : allProducts) {
            if (p.getId() == productId) {
                return p;
            }
        }
        return null;
    }
    
    public ObservableList lookupPart(String partName) {
        ObservableList<Part> matchingParts = null;
        for (Part p : allParts) {
            if (p.getName().equals(partName)) {
                matchingParts.add(p);
            }
        }
        return matchingParts;
    }
    
    public ObservableList lookupProduct(String productName) {
        ObservableList<Product> matchingProducts = null;
        for (Product p : allProducts) {
            if (p.getName().equals(productName)) {
                matchingProducts.add(p);
            }
        }
        return matchingProducts;
    }
    
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
