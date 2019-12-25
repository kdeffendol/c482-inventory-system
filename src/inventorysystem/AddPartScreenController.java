package inventorysystem;

import static inventorysystem.Inventory.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Controller for AddPartScreen
 * @author kelsey
 */
public class AddPartScreenController implements Initializable {

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
    
    @FXML private HBox companyNameHBox;
    @FXML private HBox machineIdHBox;
    
    @FXML private Button SaveButton;
    @FXML private Button CancelButton;

    
    /**
     * Changes screen to MainScreen without saving changes 
     */
    public void cancelButtonPushed(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, 
                "Are you sure you want to cancel creating a new part?", 
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
        
        if (validatePart() == true) {
            //get values from each TextField to create new part and add to the inventory
            createNewPart();


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
    
    
    /**
     * Creates new part based on data entered in text fields 
     * and adds to inventory
     */
    public void createNewPart() {
        if (inhouseRadioButton.isSelected()) {
            Part newPart =  new InHouse(Part.getNextPartId(), 
                    partNameTextField.getText(), 
                    Double.parseDouble(partPriceTextField.getText()), 
                    Integer.parseInt(partInvTextField.getText()), 
                    Integer.parseInt(partMaxTextField.getText()), 
                    Integer.parseInt(partMinTextField.getText()), 
                    Integer.parseInt(partMachineIdTextField.getText()));
            
            addPart(newPart);
        }
        else if (outsourcedRadioButton.isSelected()) {
            Part newPart =  new Outsourced(Part.getNextPartId(), 
                    partNameTextField.getText(), 
                    Double.parseDouble(partPriceTextField.getText()), 
                    Integer.parseInt(partInvTextField.getText()), 
                    Integer.parseInt(partMaxTextField.getText()), 
                    Integer.parseInt(partMinTextField.getText()), 
                    partCompanyNameTextField.getText());
            
            addPart(newPart);
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //initialize radio buttons
        radioButtons = new ToggleGroup();
        inhouseRadioButton.setToggleGroup(radioButtons);
        outsourcedRadioButton.setToggleGroup(radioButtons);


    }    
    
}
