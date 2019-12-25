/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem;

import static inventorysystem.Inventory.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kelsey
 */
public class ModifyProductScreenController implements Initializable {
    
    //Add Product text fields
    @FXML private TextField idTextField;
    @FXML private TextField nameTextField;
    @FXML private TextField invTextField;
    @FXML private TextField priceTextField;
    @FXML private TextField maxTextField;
    @FXML private TextField minTextField;
    
    //top table elements
    @FXML private Button searchInvButton;
    @FXML private TextField searchInvTextField;
    
    @FXML private TableView availablePartsTable;
    @FXML private TableColumn partIdColumnAvailable;
    @FXML private TableColumn partNameColumnAvailable;
    @FXML private TableColumn partInvLevelColumnAvailable;
    @FXML private TableColumn partPriceColumnAvailable;
    
    //add button
    @FXML private Button addToProductButton;
    
    //bottom table elements
    @FXML private TableView addedPartsTable;
    @FXML private TableColumn partIdColumnAdded;
    @FXML private TableColumn partNameColumnAdded;
    @FXML private TableColumn partInvLevelColumnAdded;
    @FXML private TableColumn partPriceColumnAdded;
    
    //delete button
    @FXML private Button deletePartButton;
    
    //navigation buttons
    @FXML private Button saveButton;
    @FXML private Button cancelButton;
    
    
    public void updateAvailablePartsTable() {
        availablePartsTable.setItems(getAllParts());
    }
    
    public void addButtonPressed(ActionEvent event) throws IOException {
        Part partToBeAdded = (Part) availablePartsTable.getSelectionModel().getSelectedItem();
        
        addedPartsTable.getItems().add(partToBeAdded);
    }
    
    public void deleteButtonPressed(ActionEvent event) throws IOException {
        Part partToBeDeleted = (Part) addedPartsTable.getSelectionModel().getSelectedItem();
        
        addedPartsTable.getItems().remove(partToBeDeleted);
    }
    
    public void searchPartsButtonPushed(ActionEvent event) throws IOException {
        String searchField = searchInvTextField.getText();
        ObservableList<Part> partSearch = FXCollections.observableArrayList();
        
        try {
           
           partSearch.add((lookupPart(Integer.parseInt(searchField))));
           availablePartsTable.setItems(partSearch);
           
        } catch (NumberFormatException e) {
            
            availablePartsTable.setItems(lookupPart(searchField));
        }   
    }
    
    /**
     * Changes screen to MainScreen without saving changes 
     */
    public void CancelButtonPushed(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, 
                "Are you sure you want to cancel modifying selected product?", 
                ButtonType.YES, 
                ButtonType.CANCEL);
        
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            Parent partPage = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            Scene partScene = new Scene(partPage);
        
            //this line gets the stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(partScene);
            window.show();
        }
    }
    
    public void saveButtonPushed(ActionEvent event) throws IOException {
        if (validateProduct() == true) {
            //get values from each TextField to create new part and add to the inventory
            modifyProduct();


            //Changes screen ---------

            Parent partPage = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            Scene partScene = new Scene(partPage);

            //this line gets the stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(partScene);
            window.show();
        
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, 
                "Inventory is greater than maximum inventory allowed.");
        
            alert.showAndWait();
        }

    }
    
    public boolean validateProduct() {
        boolean isLess = true;
        //check if inventory is greater than max inventory
        if (Integer.parseInt(invTextField.getText()) > Integer.parseInt(maxTextField.getText())) {
            isLess = false;
        }
        
        return isLess;
    }
    
    public void setPartInfo(Product productToModify) {
        
        idTextField.setText(Integer.toString(productToModify.getId()));
        nameTextField.setText(productToModify.getName());
        invTextField.setText(Integer.toString(productToModify.getStock()));
        priceTextField.setText(Double.toString(productToModify.getPrice()));
        minTextField.setText(Integer.toString(productToModify.getMin()));
        maxTextField.setText(Integer.toString(productToModify.getMax()));
        
        //fill associated parts table
        addedPartsTable.setItems(productToModify.getAllAssociatedParts());
    }
    
    public void modifyProduct() {
        
    //make part with same partId as old part
            Product newProduct =  new Product(Integer.parseInt(idTextField.getText()), 
                    nameTextField.getText(), 
                    Double.parseDouble(priceTextField.getText()), 
                    Integer.parseInt(invTextField.getText()), 
                    Integer.parseInt(maxTextField.getText()), 
                    Integer.parseInt(minTextField.getText()));
            
            updateProduct(Integer.parseInt(idTextField.getText()) - 1, newProduct);   
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
//initialize Available Parts table
        partIdColumnAvailable.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumnAvailable.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvLevelColumnAvailable.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumnAvailable.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        //initialize Added Parts table
        partIdColumnAdded.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumnAdded.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvLevelColumnAdded.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumnAdded.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        updateAvailablePartsTable();
        addedPartsTable.getItems();
    }    
    
}
