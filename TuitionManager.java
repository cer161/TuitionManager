package sample;

import java.util.Scanner;
/**
 This class defines the TuitionManager, which handles the input
 with the user that is sent through the command line, and responds
 appropriately.
 @author Rose Sirohi, Caitlyn Romano
 */

public class TuitionManager
{
    Scanner stdin = new Scanner(System.in);
    StudentList cs213 = new StudentList();
    public char function;
    public String command;
    public String first;
    public String last;
    public int credits;
    public int funding;
    public boolean tristate = false;
    public boolean exchange = false;
    public Student student;

    /**
     Method to take in commands from the user
     */
    public void readInput()
    {
        System.out.println("Enter input.");
        boolean done = false;

        while ( !done )
        {
            command = stdin.next();
            function = command.charAt(0);
            exchange = false;
            tristate = false;
            switch (function)
            {
                case 'I':
                    first= stdin.next();
                    last = stdin.next();
                    credits = stdin.nextInt();
                    funding = stdin.nextInt();
                    addInstate();
                    break;
                case 'O' :
                    first= stdin.next();
                    last = stdin.next();
                    credits = stdin.nextInt();
                    if((stdin.next()).charAt(0) == 'T'){
                        tristate = true;
                    }
                    addOutstate();
                    break;
                case 'N' :
                    first= stdin.next();
                    last = stdin.next();
                    credits = stdin.nextInt();
                    if((stdin.next()).charAt(0) == 'T'){
                        exchange = true;
                    }
                    addInternational();
                    break;
                case 'R' :
                    first = stdin.next();
                    last = stdin.next();
                    remove();
                    break;
                case 'P' :
                    print();
                    break;
                case 'Q' :
                    quit();
                    done = true;
                    break;
                default:
                    System.out.println("Command '" + function + "' not supported!");
                    stdin.nextLine();

            }
        }
    }
    /**
     Adds an in-state student to the students list,
     so long as the student's data fields are valid and the
     student is not already in the list.
     */
    private void addInstate()
    {
        if(credits <= 0){
            System.out.println("Error: credits must exceed 0.");
            return;
        }
        if(funding < 0){
            System.out.println("Error: funding cannot be negative.");
            return;
        }
        if(credits < 12 && funding > 0){
            funding = 0; //part-time students are not eligible for funding.
        }
        student = new Instate(first, last, credits, funding);
        if(cs213.contains(student) == true){
            System.out.println("Student has already been added.");
            return;
        }
        cs213.add(student);
        System.out.println("Student " + first + " " + last + " has been added.");
    }

    /**
     Adds an out-of-state student to the students list,
     so long as the student's data fields are valid and the
     student is not already in the list.
     */
    private void addOutstate()
    {
        if(credits <= 0){
            System.out.println("Error: credits must exceed 0.");
            return;
        }
        student = new Outstate(first, last, credits, tristate);
        if(cs213.contains(student) == true){
            System.out.println("Student has already been added.");
            return;
        }
        cs213.add(student);
        System.out.println("Student " + first + " " + last + " has been added.");
    }

    /**
     Adds an international student to the students list, unless the
     student's fields are invalid or the student is already in the list.
     */
    private void addInternational()
    {
        if(credits <= 9){
            System.out.println("Error: credits must exceed 9 for international students.");
            return;
        }
        student = new International(first, last, credits, exchange);
        if(cs213.contains(student) == false){
            cs213.add(student);
            System.out.println("Student " + first + " " + last + " has been added.");
        }
        else{
            System.out.println("Student has already been added.");
        }

    }

    /**
     Removes a student from the students list, unless
     the sample.Student is not in the list or the list is empty.
     */
    private void remove()
    {
        if (cs213.isEmpty()){
            System.out.println("The list of students is empty!");
            return;
        }

        student = new Outstate(first, last, 9, tristate);

        if(cs213.remove(student) == false){
            System.out.println("Student does not exist.");
        }
        return;
    }

    /**
     Prints the contents of the sample.StudentList, unless
     the sample.StudentList is empty.
     */
    private void print()
    {
        if (cs213.isEmpty()){
            System.out.println("The list of students is empty!");
            return;
        }

        cs213.print();
    }

    /**
     Prints a message to alert the user that the program
     has been terminated.
     */
    private void quit()
    {
        System.out.println("Program terminated");
    }

}
