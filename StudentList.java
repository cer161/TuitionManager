package sample;

/**
 This file defines the sample.StudentList class, which is a growable
 array of Students, that can be added to, removed from, and printed.
 @author Rose Sirohi, Caitlyn Romano
 */

public class StudentList
{

    private final int GROW_SIZE = 4;
    private final int NOT_FOUND = -1;
    public static final int EMPTY = 0;
    private Student[] studentList;
    private int numStudents;


    /**
     Default constructor for this class.
     */
    public StudentList()
    {

    }

    /**
     Method to check if the sample.StudentList is empty.
     @return True if sample.StudentList is empty, false otherwise.
     */
    public boolean isEmpty()
    {
        if(numStudents == EMPTY)
        {
            return true;
        }
        return false;
    }

    /**
     Method to increase the size of the sample.StudentList.
     */
    private void grow()
    {
        Student[] temp = new Student[numStudents + GROW_SIZE];
        for(int i = 0; i < numStudents; i++)
        {
            temp[i] = studentList[i];
        }
        studentList = temp;
    }

    /**
     Method to obtain the index of a sample.Student in the sample.StudentList array.
     @param s The sample.Student to be found.
     @return The index of the sample.Student.
     */
    private int find(Student s)
    {
        for(int i = 0; i < numStudents; i++)
        {
            if (studentList[i].compareTo(s) == 0)
            {
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     Method to add a sample.Student.
     @param s The sample.Student to be added.
     */
    public void add(Student s)
    {
        if (this.isEmpty())
        {
            studentList = new Student[GROW_SIZE];
            numStudents = EMPTY;
        }
        else if(numStudents == studentList.length)
        {
            grow();
        }

        numStudents++;
        studentList[numStudents - 1] = s;
    }

    /**
     Method to remove a sample.Student.
     @param s The sample.Student to be deleted.
     @return True if sample.Student has been deleted, false otherwise.
     */
    public boolean remove(Student s)
    {
        if(!this.contains(s))
            return false;
        int indexToRemove = find(s);
        studentList[indexToRemove] = studentList[numStudents - 1];
        studentList[numStudents - 1] = null;
        numStudents--;
        return true;

    }

    /**
     Method to check if the sample.StudentList contains a sample.Student.
     @param s The sample.Student to be checked.
     @return True if sample.Student exists in the sample.StudentList array, false otherwise.
     */
    public boolean contains(Student s)
    {
        if(this.find(s) == NOT_FOUND)
        {
            return false;
        }
        return true;
    }

    /**
     Method to print all Students in sample.StudentList to console
     */
    public void print()
    {
        for(int i = 0; i < numStudents; i++)
        {
            System.out.println(studentList[i].toString());
            System.out.println("tuition due: " + studentList[i].tuitionDue());
        }
    }

    /**
    Method used in Controller, returns printable information of all Students in list.
     @return String format of each Student in list.
     */
    public String toString()
    {
        for(int i = 0; i < numStudents; i++)
        {
            return studentList[i].toString() + " " + "tuition due: " + studentList[i].tuitionDue() + "\n";
        }
        return null;
    }

    /**
     testbed main; includes test cases that exercise
     the constructor and all methods in this class.
     */
    public static void main(String [] args)
    {
        StudentList list = new StudentList();
        Student s = new Instate("martin", "colucci", 15, 200);
        Student s2 = new Instate("mike", "mike", 15, 100);
        list.add(s);
        list.add(s2);
        System.out.println(list.find(s2));
        list.print();
    }

}
