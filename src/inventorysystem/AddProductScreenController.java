/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Add Product Screen Controller
 *
 * @author kelsey
 */
public class AddProductScreenController implements Initializable {

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
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
