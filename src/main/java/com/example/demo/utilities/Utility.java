package com.example.demo.utilities;

import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Map;

public class Utility {
    public static boolean showPopup() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Input");
        //alert.setHeaderText("Look, an Information Dialog");
        alert.setContentText("Please Check Your Input");
        alert.showAndWait();
        return false;
    }
    public static void showPopup(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle("Just Electronics");
        alert.setContentText(message);
        alert.showAndWait();

    }
    public static boolean isInputANumber(String input) {
        Boolean b= false;
        if (!(input == null || input.length() == 0)) {
            try {
                // Do all the validation you need here such as
                Double d = Double.parseDouble(input);
                return true;
            } catch (NumberFormatException e) {

            }
        }
        return b;
    }

    public static boolean validateMap(Map<Object, String> parameterMap){
        for(Map.Entry<Object, String> entry:parameterMap.entrySet()){
            Object object=entry.getKey();
            if(object instanceof Label){
                Label label=(Label) object;
                if(label.getText().equals(entry.getValue())&&!label.getId().equalsIgnoreCase("description_2")&&
                        !label.getId().equalsIgnoreCase("manufacturer_2")&&
                        !label.getId().equalsIgnoreCase("model_no_2")&&
                                !label.getId().equalsIgnoreCase("e_date_2")&&
                        !label.getId().equalsIgnoreCase("serial_no_2")){
                   return  showPopup();
                }
            }else if(object instanceof TextField) {
                TextField textField = (TextField) object;
                if (textField.getText().equals(entry.getValue())&&(!textField.getId().equalsIgnoreCase("tag_no")&&!textField.getId().equalsIgnoreCase("serialNoInstrument")
                        &&!textField.getId().equalsIgnoreCase("asset_no_2"))) {

                    return showPopup();
                }
            }else if(object instanceof ComboBox){
                ComboBox comboBox = (ComboBox) object;
                if(comboBox.getValue().toString().equalsIgnoreCase(entry.getValue())){
                    return showPopup();
                }
            }
        }
        return true;
    }
}
