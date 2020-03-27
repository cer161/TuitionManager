package sample; /**
 This class defines the abstract sample.Student class. Each student has a first
 name, last name, and the number of credit hours in their schedule, and their
 tuition due is based on their credit hours as well as what subclass they
 belong to. Its subclasses are sample.Instate, sample.Outstate, and sample.International.
 @author Rose Sirohi, Caitlyn Romano
 */

//WE CANNOT CHANGE THIS CLASS; USE AS IS (except constants)
/**
 "May need to add constants in sample.Student class. However,
 you need to make sure the constants defined in this
 class are common data for all subclasses"
 */

public abstract class Student implements Comparable {
    private String fname;
    private String lname;
    protected int credit;
    public final int FULLTIME = 12;
    public final int PARTTIME_FEE = 846;
    public final int FULLTIME_FEE = 1441;


    /**
     Constructor for the sample.Student object.
     @param fname sample.Student's first name.
     @param lname sample.Student's last name.
     @param credit sample.Student's number of credit hours.
     @return Instance of sample.Student class, with fields equal to the
     parameters sent.
     */
    public Student(String fname, String lname, int credit)
    {
        this.fname = fname;
        this.lname = lname;
        this.credit = credit;
    }

    /**
     Method compares this sample.Student with another sample.Student taken as parameter.
     @param obj sample.Student being compared with this sample.Student.
     @return 0 if first and last names of both Students are equal.
     @return -1 if this sample.Student is less than parameter sample.Student.
     @return 1 if this sample.Student is greater than parameter sample.Student.
     */
    public int compareTo (Object obj)
    {
        if (obj instanceof Student)
        {
            Student student = (Student) obj;
            if (this.fname.compareTo(student.fname) == 0) //first names equal
            {
                if (this.lname.compareTo(student.lname) == 0)//last name equal
                    return 0;
                if (this.lname.compareTo(student.lname) < 0)
                    return 1;
                return -1;
            }
            if (this.fname.compareTo(student.fname) < 0)
                return 1;
            return -1;

        }

        return -1;
    }

    /**
     Method returns a string with fname, lname, and credit hours
     fields of sample.Student,
     @return fields of sample.Student as Strings.
     */
    public String toString()
    {
        return String.format(this.fname + " " + this.lname + " " + this.credit);

    }

    /**
     Abstract method computes tuition due for the sample.Student,
     implemented concretely in subclasses.
     @return amount of tuitionDue as an integer.
     */
    public abstract int tuitionDue();

}
