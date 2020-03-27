package sample;

/**
 This class defines the sample.Outstate class, subclass of sample.Student.
 Each sample.Outstate student has all the characteristics of the sample.Student class,
 and adds a tristate field. The tuition of sample.Outstate students is unique
 from other subclasses, at $756 per credit with the opportunity
 to receive $200 off per credit.
 @author Rose Sirohi, Caitlyn Romano
 */

public class Outstate extends Student
{
    private boolean tristate;
    public static final int OUTSTATE_CPC = 756;

    /**
     Constructor for sample.Outstate sample.Student subclass, adds tristate field.
     @param fname sample.Student's first name.
     @param lname sample.Student's last name.
     @param credit sample.Student's number of credit hours.
     @param tristate True if student is from tristate area, false otherwise.
     @return Instance of sample.Instate sample.Student subclass, with fields equal to the
     parameters sent.
     */
    public Outstate(String fname, String lname, int credit, boolean tristate)
    {
        super(fname, lname, credit);
        this.tristate = tristate;
    }

    /**
     This method calculates the amount of tuition due for the sample.Outstate sample.Student.
     If credit hours are above 15, students only pay for up to 15 credits.
     sample.Outstate students pay $756 per credit.
     Fulltime sample.Outstate students get $200 off tuition per credit.
     @return Integer value of tuition due.
     */
    public int tuitionDue()
    {

        int tuition = 0;

        if (credit >= FULLTIME){
            tuition += FULLTIME_FEE;
            if (credit > 15){
                tuition += OUTSTATE_CPC * 15;
                if (tristate == true){
                    tuition -= 15 * 200;
                }
                return tuition;
            }
            if (tristate == true){
                tuition -= credit * 200;
            }
            tuition += OUTSTATE_CPC * credit;
        }
        else if (credit < FULLTIME){
            tuition += PARTTIME_FEE + OUTSTATE_CPC * credit;
        }

        return tuition;

    }

    /**
     Method returns a string with fname, lname, credit hours,
     and tristate fields of sample.Outstate sample.Student.
     @return fields of sample.Outstate sample.Student as Strings.
     */
    public String toString()
    {
        return super.toString() + " " + this.tristate;
    }
    /**
     Test bed main, to check whether code works with various cases.
     */
    public static void main(String [] args)
    {
        String f, l;
        f = "Bob";
        l = "Ross";
        //test toString()
        Outstate student1 = new Outstate(f, l, 12, true);
        System.out.println(student1.toString());

        //test tuitionDue()
        Outstate student2 = new Outstate(f, l, 15, true);
        System.out.println(student2.tuitionDue());
        Outstate student3 = new Outstate(f, l, 12, false);
        System.out.println(student3.tuitionDue());
        Outstate student4 = new Outstate(f, l, 18, false);
        System.out.println(student4.tuitionDue());
        Outstate student5 = new Outstate(f, l, 9, true);
        System.out.println(student5.tuitionDue());
    }
}
