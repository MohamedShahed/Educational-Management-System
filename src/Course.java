import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Course implements Serializable {

    private String name;
    private String code;
    private String DoctorName;
    private String DoctorId;
    private Map<String , Assignment> assignments=new HashMap<>();
    private Map<String , Student> registeredStudents=new HashMap<>();
    private Map<String , TA> TAs=new HashMap<>();



    public Course(String name, String code)
    {
        this.name=name;
        this.code=code;
    }
    public String getDoctorId(){return this.DoctorId; }
    public boolean CourseHasThisAssignment(String code)

    {
        return this.assignments.containsKey(code);
    }
    public boolean StudentRegisterInThisCourse(String id){return registeredStudents.containsKey(id); }
    public void removeStudent(String id){
        this.registeredStudents.remove(id);
    }
    public void GradeReportForSpecificAss(String AssignmentCode)
    {
        if(!this.registeredStudents.isEmpty()) {
            for (String Key : this.registeredStudents.keySet()) {
                this.registeredStudents.get(Key).courseAssignmentsReport(this.getCode(), AssignmentCode);
            }
        }
        else
            System.out.println("there is no students registered in this course yet :(' ");

    }
    public void listSolutionsForAllStudentsInThisAssignment(String assignmentCode)
    {
        if(!registeredStudents.isEmpty())
        {
        for (String Key: this.registeredStudents.keySet()) {
            this.registeredStudents.get(Key).getSolutions(this.getCode(), assignmentCode);
             }
        }
        else
            System.out.println("there is no students registered in this course yet :(' ");
    }
    public Assignment getAssignment(String assCode)
    {
        return this.assignments.get(assCode);
    }
    public Student getStudent(String id)
    {
        return this.registeredStudents.get(id);
    }
    public void registerStudent(Student student){this.registeredStudents.put(student.getID(), student); }
    public String getName(){return this.name; }
    public String getCode(){return this.code; }
    public void setDoctorName(String name)
    {
        this.DoctorName=name;
    }
    public void setDoctorId(String id){this.DoctorId=id; }
    public void addAssignment(Assignment ass)
    {
        this.assignments.put(ass.getCode(), ass);
    }
    public void listAssignments()
    {
        int counter=0;
        if(!assignments.isEmpty()){
        for (String key: assignments.keySet()){
            System.out.println("Assignment " + ++counter + " with code: " +key );
        }
        System.out.println("--------------------------");
        }
        else
            System.out.println("********this course have no assignments yet*********");
    }
    public void listAssignmentsForStudents()
    {
        int counter=0;
        for (String key: assignments.keySet()){
            Assignment value = assignments.get(key);
            System.out.println("Assignment " + ++counter + " with code: " +key + (value.getSubmitionStatus()?" Submitted ": " Not submitted ") + value.getStudentGrade() +" / "+ value.getTotalGrade());

        }
    }
    public int getNumberOfAssignments()
    {
        return this.assignments.size();
    }
    public boolean hasAssignments()
    {
        return this.assignments.size()!=0;
    }
    public double TotalStudentGrade()
    {
        double total=0;
        for (String key: assignments.keySet()){
            Assignment value = assignments.get(key);
            total+=value.getStudentGrade();
        }
        return total;
    }
    public double getSumOfAllAssignmentsTotalGrades()
    {
        double total=0;
        for (String key: assignments.keySet()){
            Assignment value = assignments.get(key);
            total+=value.getTotalGrade();
        }
        return total;
    }
    public void AddTA(TA ta)
  {
      this.TAs.put(ta.getID(), ta);
  }
    public void courseInfo()
    {
    System.out.println("\nCourse Name: "+ this.getName() +
            "  course Code: "+ this.getCode()+
            "  teaches by Dr: "+ this.DoctorName +
            "  Total number of Assignments: " + this.assignments.size());
        System.out.println("-----------");

    }

    public boolean thisCourseHasSolutionsInthisAss(String assCode)
    {
        boolean status=false;
        for(String key: registeredStudents.keySet())
        {
            status|=registeredStudents.get(key).hasSolutionInThisAss(this.code, assCode);
        }
        return status;
    }

    public boolean hasStudents()
    {
        return !registeredStudents.isEmpty();
    }


}
