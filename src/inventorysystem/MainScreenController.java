package inventorysystem;

import static inventorysystem.Inventory.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import static javafx.scene.control.ButtonType.OK;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * Controller class for Main Screen
 * @author Kelsey Deffendol <kdeffen@wgu.edu>
 */
public class MainScreenController implements Initializable {
   
    //Parts TableView variables
    @FXML TableView<Part> partTableView;
    @FXML TableColumn<Part, Integer> partIdColumn;
    @FXML TableColumn<Part, String> partNameColumn;
    @FXML TableColumn<Part, Integer> partInventoryColumn;
    @FXML TableColumn<Part, Double> partPriceColumn;
    
    @FXML Button partSearchButton;
    @FXML TextField partSearchTextField;
    
    @FXML Button partAddButton;
    @FXML Button partModifyButton;
    @FXML Button partDeleteButton;
    
    //Products TableView variables
    @FXML TableView<Product> productTableView;
    @FXML TableColumn<Product, Integer> productIdColumn;
    @FXML TableColumn<Product, String> productNameColumn;
    @FXML TableColumn<Product, Integer> productInventoryColumn;
    @FXML TableColumn<Product, Double> productPriceColumn;
    
    @FXML Button productSearchButton;
    @FXML TextField productSearchTextField;
    
    @FXML Button productAddButton;
    @FXML Button productModifyButton;
    @FXML Button productDeleteButton;
    
    //Other
    @FXML Button ExitButton;
    
    
    //PART TABLE FUNCTIONS ------
    
    public void updatePartTable() {
        partTableView.setItems(getAllParts());
    }
    
    
    public void addPartButtonPushed(ActionEvent event) throws IOException {
        Parent partPage = FXMLLoader.load(getClass().getResource("AddPartScreen.fxml"));
        Scene partScene = new Scene(partPage);
        
        //this line gets the stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(partScene);
        window.show();
    }
    
    public void modifyPartButtonPushed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyPartScreen.fxml"));
        Parent partPage = loader.load();
        
        Scene partScene = new Scene(partPage);
        
        ModifyPartScreenController modifyPartScreenController = loader.getController();
        modifyPartScreenController.setPartInfo(partTableView.getSelectionModel().getSelectedItem());
        
        //this line gets the stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(partScene);
        window.show();
    }
    
    public void deletePartButtonPushed(ActionEvent event) throws IOException {
        Part partToBeDeleted = partTableView.getSelectionModel().getSelectedItem();
        
        //confirmation box
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, 
                "Are you sure you want to delete " + partToBeDeleted.getName() + "?", 
                ButtonType.YES, 
                ButtonType.CANCEL);
        
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
           deletePart(partToBeDeleted);
           updatePartTable();
        }
        
    }
    
    public void searchPartsButtonPushed(ActionEvent event) throws IOException {
        String searchField = partSearchTextField.getText();
        ObservableList<Part> partSearch = FXCollections.observableArrayList();
        
        try {
           
           partSearch.add((lookupPart(Integer.parseInt(searchField))));
           partTableView.setItems(partSearch);
           
        } catch (NumberFormatException e) {
            
            partTableView.setItems(lookupPart(searchField));
        }   
    }
    
    public void searchProductsButtonPushed(ActionEvent event) throws IOException {
        String searchField = productSearchTextField.getText();
        ObservableList<Product> productSearch = FXCollections.observableArrayList();
        
        try {
           
           productSearch.add((lookupProduct(Integer.parseInt(searchField))));
           productTableView.setItems(productSearch);
           
        } catch (NumberFormatException e) {
            
            productTableView.setItems(lookupPart(searchField));
        } 
    }
    
    
    //PRODUCT TABLE FUNCTIONS ------
    
    public void updateProductTable() {
        productTableView.setItems(getAllProducts());
    }
    
    public void addProductButtonPushed(ActionEvent event) throws IOException {
        Parent productPage = FXMLLoader.load(getClass().getResource("AddProductScreen.fxml"));
        Scene productScene = new Scene(productPage);
        
        //this line gets the stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(productScene);
        window.show();
    }
    
    public void modifyProductButtonPushed(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyProductScreen.fxml"));
        Parent productPage = loader.load();
        
        Scene productScene = new Scene(productPage);
        
        ModifyProductScreenController modifyProductScreenController = loader.getController();
        modifyProductScreenController.setPartInfo(productTableView.getSelectionModel().getSelectedItem());
        
        //this line gets the stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(productScene);
        window.show();
    }
    
    public void deleteProductButtonPushed(ActionEvent event) throws IOException {
        Product productToBeDeleted = productTableView.getSelectionModel().getSelectedItem();
        
        //confirmation box
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, 
                "Are you sure you want to delete " + productToBeDeleted.getName() + "?", 
                ButtonType.YES, 
                ButtonType.CANCEL);
        
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
           deleteProduct(productToBeDeleted);
           updateProductTable();
        }
        
    }
    
    //OTHER FUNCTIONS ------
    
    /**
     * Closes application when exit button is pushed
     * @param event - button press
     */
    public void exitButtonPushed(ActionEvent event) {
        
        //confirmation box
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, 
                "Are you sure you want to exit the program? (Changes will not be saved)", 
                ButtonType.OK, 
                ButtonType.CANCEL);
        
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
           Platform.exit(); 
        }  
    }
    
    public void refreshTables() {
        updatePartTable();
        updateProductTable();
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //initialize Part table
        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        //initialize Product table
        productIdColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        productInventoryColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
        
        refreshTables();
        
    }    
    
}
