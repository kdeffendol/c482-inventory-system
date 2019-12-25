package inventorysystem;

import javafx.beans.property.SimpleStringProperty;

/**
 * Abstract Part class - used to build Outsourced or In-House parts
 * @author Kelsey Deffendol <kdeffen@wgu.edu>
 */
public abstract class Part {
    private int id;
    private SimpleStringProperty name;
    private double price;
    private int stock;
    private int min;
    private int max;
    private static int nextProductId = 1;

    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = new SimpleStringProperty(name);
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }
    
    public static int getNextPartId() {
        return nextProductId++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
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
        //todo: check min to be >= 0 && < max
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }  
}
