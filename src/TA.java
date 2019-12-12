import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TA implements Serializable {
    private Map<String , Course> MyCourses=new HashMap();
    private static Scanner input=new Scanner(System.in);
    private String name;
    private String ID;
    public String getID()
    {
        return this.ID;
    }
    private boolean IsAvailable()
    {
        if(MyCourses.size()==2)return false ;
        return true;
    }

    public void print()
    {
        if(IsAvailable())
            System.out.println(this.getID()  +"  "+ this.name);
        return ;
    }
    public TA(String name , String ID)
    {
        this.name=name;
        this.ID=ID;
    }
    public void TackCourse(Course course)
    {
        if(this.IsAvailable())
        {
            MyCourses.put(course.getCode(), course);
            System.out.println("Done");
        }
        else {
            System.out.println("Can't take this course sorry ");
        }
    }



}
