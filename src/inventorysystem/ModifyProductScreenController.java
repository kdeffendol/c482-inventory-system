/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    
    
    /**
     * Changes screen to MainScreen without saving changes 
     */
    public void CancelButtonPushed(ActionEvent event) throws IOException {
        Parent partPage = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene partScene = new Scene(partPage);
        
        //this line gets the stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(partScene);
        window.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        partIdColumnAvailable.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partIdAv"));
        partNameColumnAvailable.setCellValueFactory(new PropertyValueFactory<Part, String>("partNameAv"));
        partInvLevelColumnAvailable.setCellValueFactory(new PropertyValueFactory<Part, Integer>("invLevelAv"));
        partPriceColumnAvailable.setCellValueFactory(new PropertyValueFactory<Part, Double>("partPriceAv"));
        
    }    
    
}
