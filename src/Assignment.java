import java.io.Serializable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Assignment implements Serializable {
    private String code;
    private double TotalGrade;
    private double StudentGrade;
    private boolean submitted=false;
    private String Solution;
    private String comment;
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd:HH:mm:ss");
    private static SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy :HH:mm:ss");
    private long CreationTime;
    private long AssignmentTime;
    private static Date date=null;
    private static Scanner input=new Scanner(System.in);


    public Assignment(String code, double TotalGrade)
    {
        this.code=code;
        this.TotalGrade=TotalGrade;
        this.date=new Date();
        this.CreationTime=date.getTime();

    }
    public void setAssignmentTime(int day, int hour)
    {
        this.AssignmentTime=this.CreationTime+(hour*(60*60*1000))+(day*(24*60*60*1000));
    }
    public boolean isSubmitted(){return this.submitted; }
    public String getComment(){return this.comment; }
    public void setComment(String comment)
    {
        this.comment=comment;
        System.out.println("Done");
    }
    public String getCode(){return this.code; }
    public void setGrade(double grad) /**Only Dr could set and update this grade */
    {
        this.StudentGrade= grad;
    }
    public void updateTotalGrade(double grade)
    {
        this.TotalGrade=grade;
    }
    public void submitSolution() /**Only student could submit */
    {
        System.out.println("************enter ur solution*********** ");
        this.Solution=input.nextLine();
        this.submitted=true;
    }
    public boolean getSubmitionStatus()
    {
        return this.submitted;
    }
    public String getSolution()
    {
        if(this.isSubmitted())
            return this.Solution;
        else
            return "has no solution yet ";
    }
    public double getStudentGrade()
    {
        return this.StudentGrade;
    }
    public double getTotalGrade()
    {
        return this.TotalGrade;
    }
    public void showInfo()
    {
        System.out.println("the time will end at  : " +formatter2.format(this.AssignmentTime));
        System.out.println("Still : " + formatter.format(this.AssignmentTime-this.CreationTime));
        System.out.println("the total grade for this assignment is : " + this.TotalGrade);

    }

}
