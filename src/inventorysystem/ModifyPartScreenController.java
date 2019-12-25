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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kelsey
 */
public class ModifyPartScreenController implements Initializable {
    @FXML private ToggleGroup radioButtons;
    @FXML private RadioButton inhouseRadioButton;
    @FXML private RadioButton outsourcedRadioButton;
    
    @FXML private TextField partIdTextField;
    @FXML private TextField partNameTextField;
    @FXML private TextField partInvTextField;
    @FXML private TextField partPriceTextField;
    @FXML private TextField partMaxTextField;
    @FXML private TextField partMinTextField;
    @FXML private TextField partCompanyNameTextField;
    @FXML private TextField partMachineIdTextField;
    
    @FXML private Button SaveButton;
    @FXML private Button CancelButton;
    
    @FXML private HBox companyNameHBox;
    @FXML private HBox machineIdHBox;

    
    /**
     * Changes screen to MainScreen without saving changes 
     */
    public void CancelButtonPushed(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, 
                "Are you sure you want to cancel modifying selected part?", 
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
    
    /**
     * Changes screen to MainScreen with saving changes
     */
    public void SaveButtonPushed(ActionEvent event) throws IOException {
       if (validatePart() == true) {
            // change existing part information
            modifyPart();

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
    
    public boolean validatePart() {
        boolean isLess = true;
        //check if inventory is greater than max inventory
        if (Integer.parseInt(partInvTextField.getText()) > Integer.parseInt(partMaxTextField.getText())) {
            isLess = false;
        }
        
        return isLess;
    }
    
    public void outsourcedButtonPressed() {
        companyNameHBox.setVisible(true);
        machineIdHBox.setVisible(false);
    }
    
    public void inhouseButtonPressed() {
        companyNameHBox.setVisible(false);
        machineIdHBox.setVisible(true);
    }
    
    public void setPartInfo(Part partToModify) {
        if (partToModify instanceof Outsourced) {
            outsourcedRadioButton.setSelected(true);
            partCompanyNameTextField.setText(((Outsourced) partToModify).getCompanyName());
            companyNameHBox.setVisible(true);
        }
        else {
            inhouseRadioButton.setSelected(true);
            partMachineIdTextField.setText(Integer.toString(((InHouse) partToModify).getMachineId()));
            machineIdHBox.setVisible(true);
        }
        
        partIdTextField.setText(Integer.toString(partToModify.getId()));
        partNameTextField.setText(partToModify.getName());
        partInvTextField.setText(Integer.toString(partToModify.getStock()));
        partPriceTextField.setText(Double.toString(partToModify.getPrice()));
        partMinTextField.setText(Integer.toString(partToModify.getMin()));
        partMaxTextField.setText(Integer.toString(partToModify.getMax()));
        
    }
    
    
    public void modifyPart() {
        
        //make part with same partId as old part
        if (inhouseRadioButton.isSelected()) {
            Part newPart =  new InHouse(Integer.parseInt(partIdTextField.getText()), 
                    partNameTextField.getText(), 
                    Double.parseDouble(partPriceTextField.getText()), 
                    Integer.parseInt(partInvTextField.getText()), 
                    Integer.parseInt(partMaxTextField.getText()), 
                    Integer.parseInt(partMinTextField.getText()), 
                    Integer.parseInt(partMachineIdTextField.getText()));
            
            updatePart(Integer.parseInt(partIdTextField.getText()) - 1, newPart);
        }
        else if (outsourcedRadioButton.isSelected()) {
            Part newPart =  new Outsourced(Integer.parseInt(partIdTextField.getText()), 
                    partNameTextField.getText(), 
                    Double.parseDouble(partPriceTextField.getText()), 
                    Integer.parseInt(partInvTextField.getText()), 
                    Integer.parseInt(partMaxTextField.getText()), 
                    Integer.parseInt(partMinTextField.getText()), 
                    partCompanyNameTextField.getText());
            
            updatePart(Integer.parseInt(partIdTextField.getText()) - 1, newPart);
        }
        
        
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        radioButtons = new ToggleGroup();
        this.inhouseRadioButton.setToggleGroup(radioButtons);
        this.outsourcedRadioButton.setToggleGroup(radioButtons);
       
    }    
    
}
