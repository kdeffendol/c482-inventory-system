package inventorysystem;

import javafx.collections.ObservableList;

/**
 * Inventory class : stores all Parts and Products objects
 * @author Kelsey Deffendol <kdeffen@wgu.edu>
 */
public class Inventory {

    private static ObservableList<Part> allParts;
    private static ObservableList<Product> allProducts; 

    /**
     * Adds part to the end of parts list
     * @param part
     */
    public static void addPart(Part part) {
        allParts.add(part);
    }
    
    /**
     * Adds product to the end of products list
     * @param product
     */
    public static void addProduct(Product product) {
        allProducts.add(product);
    }
    
    /**
     * Return a part object from given partId
     * @param partId
     * @return Part object that matches given partId
     */
    public static Part lookupPart(int partId) {
        for (Part p : allParts) {
            if (p.getId() == partId) {
                return p;
            }
        }
        return null;
    }
    
    /**
     * Return a product object from given productId
     * @param productId
     * @return Product object that matches given productId
     */
    public static Product lookupProduct(int productId) {
        for (Product p : allProducts) {
            if (p.getId() == productId) {
                return p;
            }
        }
        return null;
    }
    
    /**
     * Return list of parts with name that matches
     * @param partName
     * @return list of parts that match given partName
     */
    public static ObservableList lookupPart(String partName) {
        ObservableList<Part> matchingParts = null;
        for (Part p : allParts) {
            if (p.getName().equals(partName)) {
                matchingParts.add(p);
            }
        }
        return matchingParts;
    }
    
    /**
     * Return list of products with name that matches
     * @param productName
     * @return list of products that match given productName
     */
    public static ObservableList lookupProduct(String productName) {
        ObservableList<Product> matchingProducts = null;
        for (Product p : allProducts) {
            if (p.getName().equals(productName)) {
                matchingProducts.add(p);
            }
        }
        return matchingProducts;
    }
    
    /**
     * updates Product information in given index position
     * @param index - index of product that is updated
     * @param product - new product information
     */
    public static void updateProduct(int index, Product product) {
        allProducts.set(index, product);
    }
    
    /**
     * updates Part information in given index position
     * @param index - index of part that is updated
     * @param part - new part information
     */
    public static void updatePart(int index, Part part) {
        allParts.set(index, part);
    }
    
    /**
     * removes part from allParts list
     * @param part - part to be removed
     */
    public static void deletePart(Part part) {
        allParts.remove(part);
    }
    
    /**
     * removes product from allProducts list
     * @param product - product to be removed
     */
    public static void deleteProduct(Product product) {
        allProducts.remove(product);
    }
    
    /**
     * returns all parts in inventory
     * @return allParts
     */
    public static ObservableList getAllParts() {
        return allParts;
    }
    
    /**
     * returns all products in inventory
     * @return allProducts
     */
    public static ObservableList getAllProducts() {
        return allProducts;
    }
}
