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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author kelsey
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
    
    
    public void addPartButtonPushed(ActionEvent event) throws IOException {
        Parent partPage = FXMLLoader.load(getClass().getResource("AddPartScreen.fxml"));
        Scene partScene = new Scene(partPage);
        
        //this line gets the stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(partScene);
        window.show();
    }
    
    public void modifyPartButtonPushed(ActionEvent event) throws IOException {
        Parent partPage = FXMLLoader.load(getClass().getResource("ModifyPartScreen.fxml"));
        Scene partScene = new Scene(partPage);
        
        //this line gets the stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(partScene);
        window.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //initialize Part table
        partIdColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partId"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("partName"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("partPrice"));
    }    
    
}
