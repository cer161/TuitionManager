package sample;

/**
 This class defines the sample.International class, subclass of sample.Student.
 Each sample.International student has all the characteristics of the sample.Student class,
 and adds an exchange field. The tuition of sample.International students is unique
 from other subclasses, at $945 per credit as well as a $350 fee. Exchange
 students are exempt from paying per-credit tuition.
 @author Rose Sirohi, Caitlyn Romano
 */

public class International extends Student
{
    private boolean exchange;
    public static final int INTERNATIONAL_FEE = 350;
    public static final int INTERNATIONAL_CPC = 945;

    /**
     Constructor for sample.International sample.Student subclass, adds exchange field.
     @param fname sample.Student's first name.
     @param lname sample.Student's last name.
     @param credit sample.Student's number of credit hours.
     @param exchange True if student is an exchange student, false otherwise.
     @return Instance of sample.International sample.Student subclass, with fields equal to the
     parameters sent.
     */
    public International(String fname, String lname, int credit, boolean exchange)
    {
        super(fname, lname, credit);
        this.exchange = exchange;
    }

    /**
     This method calculates the amount of tuition due for the sample.International sample.Student.
     If credit hours are above 15, students only pay for up to 15 credits.
     sample.Outstate students pay $945 per credit.
     Exchange students only pay full-time University fee and sample.International student fee.
     @return Integer value of tuition due.
     */
    public int tuitionDue()
    {
        int tuition = INTERNATIONAL_FEE;

        if (exchange == true){
            return tuition + FULLTIME_FEE;
        }

        if (credit >= FULLTIME){
            tuition += FULLTIME_FEE;
            if (credit > 15){
                tuition += INTERNATIONAL_CPC * 15;
                return tuition;
            }
            tuition += INTERNATIONAL_CPC * credit;
        }
        else if (credit < FULLTIME){
            tuition += PARTTIME_FEE + INTERNATIONAL_CPC * credit;
        }

        return tuition;
    }

    /**
     Method returns a string with fname, lname, credit hours,
     and exchange fields of sample.International sample.Student.
     @return fields of sample.International sample.Student as Strings.
     */
    public String toString()
    {
        return super.toString() + " " + this.exchange;
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
        International student1 = new International(f, l, 15, true);
        System.out.println(student1.toString());

        //test tuitionDue()
        International student2 = new International(f, l, 15, true);
        System.out.println(student2.tuitionDue());

        International student3 = new International(f, l, 20, false);
        System.out.println(student3.tuitionDue());

        International student4 = new International(f, l, 15, false);
        System.out.println(student4.tuitionDue());

        International student5 = new International(f, l, 10, true);
        System.out.println(student5.tuitionDue());

        International student6 = new International(f, l, 10, false);
        System.out.println(student6.tuitionDue());

    }
}
