import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Data implements Serializable {
    private Map<String , TA> TAs=new HashMap();
    private Map<String, Doctor> Drs=new HashMap();
    private Map<String, Student> students=new HashMap();
    private Map<String , Course>courses=new HashMap();


    public void addTA(TA ta) { this.TAs.put(ta.getID(), ta); }
    public void addDr(Doctor dr)
    {
        this.Drs.put(dr.getID(), dr);
    }
    public boolean hasStudent(String id){return this.students.containsKey(id);}
    public boolean hasDoctur(String id){return this.Drs.containsKey(id);}
    public boolean hasCourse(String code){return this.courses.containsKey(code);}
    public boolean hasTA(String id){return this.TAs.containsKey(id);}

    public void addCourse(Course course)
    {
        this.courses.put(course.getCode(), course);
    }
    public void addStudent(Student student)
    {
        this.students.put(student.getID(), student);
    }

    public void removeDr(Doctor dr)
    {
        this.Drs.remove(dr.getID());
    }
    public void removeStudent(String id)
    {
        this.students.remove(id);
    }

    public void showAvailableTAs()
    {
        System.out.println("TAs--------------------------------");
        for (String key: TAs.keySet()){
            TA value = TAs.get(key);
            value.print();
        }
    }



    public void showAvilableCourses(String id)
    {
        for (String key: courses.keySet()){
            Course value = courses.get(key);
            if(!value.StudentRegisterInThisCourse(id))
                System.out.println(key + " " + value.getName());
        }
    }
    public void updateDoctor(Doctor dr){
        this.Drs.replace(dr.getID(), dr);
    }
    public void updateCourse(Course course){
        this.courses.replace(course.getCode(), course);
    }
    public TA getTA(String id)
    {
        return this.TAs.get(id);
    }
    public Course getCourse(String code)
    {
        return courses.get(code);
    }
    public Doctor getDoctor(String code) {return this.Drs.get(code); }
    public Student getStudent(String id)
    {
        return this.students.get(id);
    }


}
