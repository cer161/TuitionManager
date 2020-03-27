package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    //GUI components
    public TextField firstName;
    public Button addBtn;
    public TextArea console;
    public TextField credits;
    public TextField lastName;
    public RadioButton instateBtn;
    public TextField funding;
    public CheckBox fundingBtn;
    public RadioButton outstateBtn;
    public CheckBox tristateBtn;
    public RadioButton internationalBtn;
    public CheckBox exchangeBtn;
    public Button printBtn;

    StudentList cs213 = new StudentList();
    public Student tempStudent;
    public int typeOfStudent = 0;
    public boolean tristate = false;
    public boolean exchange = false;
    public boolean hasFunding = false;
    public boolean disable = true;
    public int tracker = 0;
    public int numStudents= 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    funding.setDisable(true);
    fundingBtn.setDisable(true);
    tristateBtn.setDisable(true);
    exchangeBtn.setDisable(true);
    }

    /**
     * Method to check if first name is entered properly
     *
     * @return true if first name is entered properly, false otherwise
     */
    public boolean firstNameEntered() {
        if (firstName.getText().trim().isEmpty()) return false;
        return true;
    }

    /**
     * Method to check if last name is entered properly
     *
     * @return true if last name is entered properly, false otherwise
     */
    public boolean lastNameEntered() {
        if (lastName.getText().trim().isEmpty()) return false;
        return true;
    }

    /**
     * Method to check if credits are entered properly
     *
     * @return true if credits are entered properly, false otherwise
     */
    public boolean creditsEntered() {
        if (credits.getText().trim().isEmpty()) return false;
        return true;
    }

    /**
     * Method set the student type to instate if instate button is pressed
     */
    public void isInstate(ActionEvent actionEvent) {
        funding.clear();
        funding.setDisable(true);
        typeOfStudent = 1;
        fundingBtn.setDisable(!disable);
        internationalBtn.setDisable(disable);
        outstateBtn.setDisable(disable);
        disable = !disable;
    }

    /**
     * Method to set the student type to outstate if outstate button is pressed
     */
    public void isOutstate(ActionEvent actionEvent) {
        typeOfStudent = 2;
        funding.setDisable(true);
        tristateBtn.setDisable(!disable);
        internationalBtn.setDisable(disable);
        instateBtn.setDisable(disable);
        disable = !disable;
    }

    /**
     * Method to set the student type to international if international button is pressed
     */
    public void isInternational(ActionEvent actionEvent) {
        typeOfStudent = 3;
        funding.setDisable(true);
        exchangeBtn.setDisable(!disable);
        instateBtn.setDisable(disable);
        outstateBtn.setDisable(disable);
        disable = !disable;
    }
    /**
     * Method to set the tristate boolean to true if tristate button is pressed
     */
    public void isTristate(){
        tristate = true;
    }



    /**
     * Method to set the exchange boolean to true if exchange button is pressed
     */
    public void isExchange(){
        exchange = true;
    }

    /**
     * Method to activate Funding checkmark
     */
    public void clickFunding(ActionEvent actionEvent) {
        //increment tracker, when tracker is odd, funding box is checked.
        tracker++;
        funding.clear();
        hasFunding = true;
        if(fundingBtn.isDisabled() || instateBtn.isDisabled() || tracker%2 == 0) {
            funding.setDisable(true);
        }
        else{
            funding.setDisable(false);
        }
    }

    /**
     * Method which saves input from the user and calls add methods
     *
     * @param event the add button is clicked
     */
    public void pressAddButton(ActionEvent event) {

        if (firstNameEntered() && lastNameEntered() && creditsEntered() && typeOfStudent != 0) {
            String fname = firstName.textProperty().get().trim();
            String lname = lastName.textProperty().get().trim();
            String credsTemp = credits.textProperty().get().trim();
            int creds;
            try{
                creds = Integer.parseInt(credsTemp);
            }
            catch (NumberFormatException nfe)
            {
                console.appendText("NumberFormatException: invalid credits input" + "\n");
                return;
            }
            if(creds<1){
                console.appendText("NumberFormatException: credits must exceed 0" + "\n");
                return;
            }


            if (typeOfStudent == 1) {
                int funds = 0;
                if(hasFunding) {
                    String fundTemp = funding.getText();
                    try {
                        funds = Integer.parseInt(fundTemp);
                    } catch (NumberFormatException nfe) {
                        console.appendText("NumberFormatException: invalid funding" + "\n");
                        return;
                    }
                    if (funds < 1) {
                        console.appendText("Error: Funding must exceed 0" + "\n");
                        return;
                    }
                }
                Instate s = new Instate(fname, lname, creds, funds);
                addInstateStudent(s);
            }

            if (typeOfStudent == 2) {
                Outstate s = new Outstate(fname, lname, creds, tristate);
                addOutstateStudent(s);
            }

            if (typeOfStudent == 3) {
                if(creds<=9){
                    console.appendText("Error: Credits must exceed 9 for international students" + "\n");
                    return;
                }
                International s = new International(fname, lname, creds, exchange);
                addInternationalStudent(s);
            }

        } else {
            console.appendText("Please fill in all required fields" + "\n");
            return;
        }
    }

    public void addInstateStudent(Instate student) {
        if (cs213.contains(student) == true) {
            console.appendText("Student has already been added." + "\n");
            return;
        }
        cs213.add(student);
        numStudents++;
        console.appendText("Student has been added successfully." + "\n");
    }

    public void addOutstateStudent(Outstate student) {
        if (cs213.contains(student) == true) {
            console.appendText("Student has already been added." + "\n");
            return;
        }
        cs213.add(student);
        numStudents++;
        console.appendText("Student has been added successfully." + "\n");
    }

    public void addInternationalStudent(International student) {
         if(cs213.contains(student)==true) {
        console.appendText("Student has already been added." + "\n");
        return;
         }
        cs213.add(student);
         numStudents++;
        console.appendText("Student has been added successfully."+"\n");
    }

    //unfinished method to print the list
    public void printList(ActionEvent actionEvent) {
        if (cs213.isEmpty()){
            console.appendText("Error: The list is empty" + "\n");
            return;
        }
        console.appendText(cs213.toString());
    }

    //unfinished method to remove a student from the list
    public void removeStudent(ActionEvent actionEvent) {
        if (cs213.isEmpty()){
            console.appendText("Error: The list is empty" + "\n");
            return;
        }
        if (!firstNameEntered() || !lastNameEntered()){
            console.appendText("Please enter the first and last name of the student you want to delete" + "\n");
            return;
        }
        String fname = firstName.textProperty().get().trim();
        String lname = lastName.textProperty().get().trim();
        //create a temp student to search the list and delete
        tempStudent = new Outstate(fname, lname, 10, false);
        if(cs213.remove(tempStudent) == false) {
            console.appendText("Error: The student does not exist." + "\n");
            return;
        }
        console.appendText(fname + " " + lname + " removed from list.");
    }

}


