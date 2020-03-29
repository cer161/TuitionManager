/**
 This file defines the Controller class, which is a class
 that interacts with the GUI to have user actions
 affect the program.
 @author Caitlyn Romano, Rose Sirohi
 */

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
    public int fundCheckTracker = 0;
    public int tristateCheckTracker = 0;
    public int exchangeCheckTracker = 0;
    public int numberStudents= 0;
    public static final int INSTATE = 1;
    public static final int OUTSTATE = 2;
    public static final int INTERNATIONAL = 3;

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
        if (credits.getText().trim().isEmpty())return false;
        return true;
    }

    /**
     * Method set the student type to instate if instate button is pressed
     */
    public void isInstate(ActionEvent actionEvent) {
        if (instateBtn.isSelected() == true){
            typeOfStudent = INSTATE;
        }
        else{
            typeOfStudent = 0;
        }
        funding.clear();
        funding.setDisable(true);
        fundingBtn.setSelected(false);
        tristateBtn.setSelected(false);
        exchangeBtn.setSelected(false);
        exchangeCheckTracker = 0;
        fundCheckTracker = 0;
        tristateCheckTracker = 0;
        fundingBtn.setDisable(!disable);
        internationalBtn.setDisable(disable);
        outstateBtn.setDisable(disable);
        disable = !disable;
    }

    /**
     * Method to set the student type to outstate if outstate button is pressed
     */
    public void isOutstate(ActionEvent actionEvent) {
        if (outstateBtn.isSelected() == true){
            typeOfStudent = OUTSTATE;
        }
        else{
            typeOfStudent = 0;
        }
        funding.setDisable(true);
        fundingBtn.setSelected(false);
        tristateBtn.setSelected(false);
        exchangeBtn.setSelected(false);
        exchangeCheckTracker = 0;
        fundCheckTracker = 0;
        tristateCheckTracker = 0;
        fundingBtn.setDisable(disable);
        tristateBtn.setDisable(!disable);
        internationalBtn.setDisable(disable);
        instateBtn.setDisable(disable);
        disable = !disable;
    }

    /**
     * Method to set the student type to international if international button is pressed
     */
    public void isInternational(ActionEvent actionEvent) {
        if (internationalBtn.isSelected() == true){
            typeOfStudent = INTERNATIONAL;
        }
        else{
            typeOfStudent = 0;
        }
        funding.setDisable(true);
        fundingBtn.setSelected(false);
        tristateBtn.setSelected(false);
        exchangeBtn.setSelected(false);
        exchangeCheckTracker = 0;
        fundCheckTracker = 0;
        tristateCheckTracker = 0;
        fundingBtn.setDisable(disable);
        exchangeBtn.setDisable(!disable);
        instateBtn.setDisable(disable);
        outstateBtn.setDisable(disable);
        disable = !disable;
    }
    /**
     * Method to set the tristate boolean to true if tristate button is pressed
     */
    public void isTristate(){
        //when tracker is odd, tristate box is checked.
        tristateCheckTracker++;
        if (tristateCheckTracker % 2 == 0){
            tristate = false;
            return;
        }
        typeOfStudent = OUTSTATE;
        tristate = true;
    }

    /**
     * Method to set the exchange boolean to true if exchange button is pressed
     */
    public void isExchange(){
        //when tracker is odd, exchange box is checked.
        exchangeCheckTracker++;
        if (exchangeCheckTracker % 2 == 0){
            exchange = false;
            return;
        }
        typeOfStudent = INTERNATIONAL;
        exchange = true;
    }

    /**
     * Method to activate Funding checkmark and enable funding textbox when checked.
     */
    public void clickFunding(ActionEvent actionEvent) {
        //increment tracker; when tracker is odd, funding box is checked.
        fundCheckTracker++;
        funding.clear();
        if(fundingBtn.isDisabled() || instateBtn.isDisabled() || fundCheckTracker % 2 == 0) {
            hasFunding = false;
            funding.setDisable(true);
            return;
        }
        else{
            funding.setDisable(false);
            typeOfStudent = INSTATE;
            hasFunding = true;
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


            if (typeOfStudent == INSTATE) {
                int funds = 0;
                if(hasFunding) {
                    String fundTemp = funding.getText();
                    try {
                        funds = Integer.parseInt(fundTemp);
                    } catch (NumberFormatException nfe) {
                        console.appendText("NumberFormatException: invalid funding" + "\n");
                        //console.appendText("Please add a numeric value for the amount of" +
                        // " funding for this student, or 0 if none." + "\n");
                        return;
                    }
                    if (funds < 0) {
                        console.appendText("Error: Funding cannot be a negative value." + "\n");
                        return;
                    }
                    if (creds < 9 && funds > 0)
                    {
                        console.appendText("Error: Part-time students are not eligible for funding." + "\n");
                        return;
                    }
                }

                Instate s = new Instate(fname, lname, creds, funds);
                addInstateStudent(s);
            }

            if (typeOfStudent == OUTSTATE) {
                Outstate s = new Outstate(fname, lname, creds, tristate);
                addOutstateStudent(s);
            }

            if (typeOfStudent == INTERNATIONAL) {
                if(creds < 9){
                    console.appendText("Error: Credits must be at least 9 for international students" + "\n");
                    return;
                }
                International s = new International(fname, lname, creds, exchange);
                addInternationalStudent(s);
            }

        } else {
            console.appendText("Please fill in all required fields" + "\n");
            return;
        }
        fundingBtn.setSelected(false);
        funding.clear();
        tristateBtn.setSelected(false);
        exchangeBtn.setSelected(false);
        exchangeCheckTracker = 0;
        fundCheckTracker = 0;
        tristateCheckTracker = 0;
        typeOfStudent = 0;
    }

    /**
     * Method to add Instate student to list, if the student has not
     * already been added.
     */
    public void addInstateStudent(Instate student) {
        if (cs213.contains(student) == true) {
            console.appendText( "Student has already been added." + "\n");
            return;
        }
        cs213.add(student);
        numberStudents++;
        firstName.clear();
        lastName.clear();
        credits.clear();
        fundingBtn.setDisable(true);
        funding.setDisable(true);
        console.appendText("Student has been added successfully." + "\n");
        hasFunding = false;
    }

    /**
     * Method to add Outstate student to list, if the student has not
     * already been added.
     */
    public void addOutstateStudent(Outstate student) {
        if (cs213.contains(student) == true) {
            console.appendText("Student has already been added." + "\n");
            return;
        }
        cs213.add(student);
        numberStudents++;
        firstName.clear();
        lastName.clear();
        credits.clear();
        console.appendText("Student has been added successfully." + "\n");
        tristate = false;
    }

    /**
     * Method to add International student to list, if the student has not
     * already been added.
     */
    public void addInternationalStudent(International student) {
        if(cs213.contains(student) == true) {
            console.appendText("Student has already been added." + "\n");
            return;
        }
        cs213.add(student);
        numberStudents++;
        firstName.clear();
        lastName.clear();
        credits.clear();
        console.appendText("Student has been added successfully."+"\n");
        exchange = false;
    }

    /**
     * Method to print the list of Students, as well as the data
     * associated with each Student, unless the list is empty.
     */
    public void printList(ActionEvent actionEvent) {
        if (cs213.isEmpty() || numberStudents == 0){
            console.appendText("Error: The list is empty" + "\n");
            return;
        }
        for(int i = 0; i < numberStudents; i++)
        {
            console.appendText(cs213.toString(i));
        }
        firstName.clear();
        lastName.clear();
        credits.clear();
        return;
    }

    /**
     * Method to remove a student from the list, unless the list is empty
     * or the student is not in the list.
     */
    public void removeStudent(ActionEvent actionEvent) {
        if (cs213.isEmpty()){
            console.appendText("Error: The list is empty" + "\n");
            return;
        }
        if (!firstNameEntered() || !lastNameEntered()){
            console.appendText("Please enter the first and last name of the student" +
                    " you want to delete" + "\n");
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
        numberStudents--;
        console.appendText(fname + " " + lname + " removed from list." + "\n");
        fundingBtn.setSelected(false);
        funding.clear();
        tristateBtn.setSelected(false);
        exchangeBtn.setSelected(false);
        exchangeCheckTracker = 0;
        fundCheckTracker = 0;
        tristateCheckTracker = 0;
        firstName.clear();
        lastName.clear();
        credits.clear();
    }

}
