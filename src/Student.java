import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Student implements Serializable {
    private static Scanner input=new Scanner(System.in);
    private Map<String , Course> MyCourses=new HashMap<>();
    private String name;
    private String ID;
    private String userName;
    private String password;
    private String fullName;
    private String email;

    private boolean hasThisCourse(String code)
    {
        return  this.MyCourses.containsKey(code);
    }
    private String ValidateCourseCode()
    {
        String code=input.next();
        while (!this.hasThisCourse(code))
        {
            System.out.println("Wrong code try again : ");
            code=input.next();
        }
        return code;
    }
    private void menuForViewCourse()
    {
        System.out.println("1) submit Assignment solution\n"+"2) unregister from Course\n3) show Dr comment \n4)back ");
    }
    private int validateMenuForViewCourse(){
        menuForViewCourse();
        int choice=input.nextInt();
        while(choice!=1 && choice!=2 && choice!=3 && choice!=4){
            System.out.println("Wrong choice try again ");
            choice=input.nextInt();
        }
        return choice;
    }
    private void studentMenu()
    {
        System.out.println(
                "1) Register in course\n"+
                "2) List My Courses\n"+
                "3) View a course\n"+
                "4) Grades Report\n"+
                "5) Log out"
        );
    }
    private String validateCourseCode(Data data)
    {
        String code=input.next();
        while(!data.hasCourse(code))
        {
            System.out.println("Wrong ID try again ");
            code=input.next();
        }
        return code;

    }
    private double TotalStudentGradesInAllAssignments()
    {
        double total=0;

        for (String name: MyCourses.keySet()){
            String key = name;
            Course value = MyCourses.get(name);
            total+=value.TotalStudentGrade();
        }
        return total;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email)  /**in the email format */
    {
        this.email = email;
    }
    public String getID()
    {
        return this.ID;
    }
    public Student(String name, String id)
    {
        this.name=name;
        this.ID=id;
    }
    public int choiceValidatoin()
    {
        studentMenu();
        int choice=input.nextInt();
        while(choice!=1 && choice!=2 && choice!=3 && choice!=4 && choice!=5)
        {
            System.out.println("wrong choice try again \n=========================== ");
            studentMenu();
            choice=input.nextInt();
        }
        return choice;
    }
    public void ViewCourse()
    {
        this.listAllCourses();
        System.out.println("enter the course code: ");
        String courseCode= ValidateCourseCode();
        System.out.println("Course Name: " + this.MyCourses.get(courseCode).getName() + "  Course Code: " + this.MyCourses.get(courseCode).getCode());
        if(this.MyCourses.get(courseCode).hasAssignments()){
            this.MyCourses.get(courseCode).listAssignmentsForStudents();
        }
        else
            System.out.println("you have No assignments in this course ");
        System.out.println("------------------------------------------------\n");

        int choice=validateMenuForViewCourse();
        String assCode="";
        if(choice==1)
        {
            if(this.MyCourses.get(courseCode).hasAssignments())
                {
                System.out.println("enter the Assignment code : "); assCode=input.next();
                while (!MyCourses.get(courseCode).CourseHasThisAssignment(assCode)){
                    System.out.println("wrong code plz try again : ");
                    assCode=input.next();
                }
                MyCourses.get(courseCode).getAssignment(assCode).submitSolution();
               }
            else
                System.out.println("u have no assignments in this course...");
        }
        else if(choice==2)
        {
            MyCourses.get(courseCode).removeStudent(this.ID);
            MyCourses.remove(this);

        }
        else if(choice==3)
        {
            if(this.MyCourses.get(courseCode).getAssignment(assCode).isSubmitted())
                System.out.println(this.MyCourses.get(courseCode).getAssignment(assCode).getComment());
        }
        else return;

    }
    public void registerInCourse(Data data)
    {
        data.showAvilableCourses(this.ID);
       System.out.println("enter the course code : ");
       String courseCode=validateCourseCode(data);
       this.MyCourses.put(courseCode, data.getCourse(courseCode));
        data.getCourse(courseCode).registerStudent(this);
        System.out.println("Done\n");
    }
    public void listAllCourses()
    {
        if(!this.MyCourses.isEmpty()) {
            for (String name : MyCourses.keySet()) {
                String key = name;
                String value = MyCourses.get(name).getName();
                System.out.println(key + " " + value);
            }
            System.out.println("-----------------------------\n");
        }
        else
            System.out.println("u did't registered in any courses yet...");
    }
    public void gradeReport()
    {
        for (String name: MyCourses.keySet()){
            String key = name;
            Course value = MyCourses.get(name);
            System.out.println(key + " " + value.getName() +
                    " -  Total " + value.getNumberOfAssignments() +
                    " assignments " + " Grade " + TotalStudentGradesInAllAssignments() +
                    " /" + value.getSumOfAllAssignmentsTotalGrades());
        }
    }
    public void LogOut(Data data)  /**update status of this student*/
    {
        data.removeStudent(this.ID);
        data.addStudent(this);
    }
    public void courseAssignmentsReport(String CourseCode, String AssignmentCode)
    {
        Course course=this.MyCourses.get(CourseCode);
        System.out.println("student Name: " +this.name);
        for(int i=1; i<=course.getNumberOfAssignments(); i++)
        {
            System.out.println("\tAss-"+i +"  "+course.getAssignment(AssignmentCode).getStudentGrade() +
                    "  from " +
                    course.getAssignment(AssignmentCode).getTotalGrade());
        }
    }
    public void getSolutions(String CourseCode, String assignmentCode)
    {
        System.out.println("student Name: " +this.name + "   ID: "+ this.ID);
        if(this.MyCourses.get(CourseCode).getAssignment(assignmentCode).getSubmitionStatus())
            System.out.println("his solution is : \n"+ this.MyCourses.get(CourseCode).getAssignment(assignmentCode).getSolution());
        else
            System.out.println("he did't submit his solution yet :)'");

    }
    public void setGrade(String CourseCode, String assignmentCode, double grade)
    {
        this.MyCourses.get(CourseCode).getAssignment(assignmentCode).setGrade(grade);
    }
    public void setComment(String CourseCode, String assignmentCode, String comment)
    {
        this.MyCourses.get(CourseCode).getAssignment(assignmentCode).setComment(comment);
    }
    public void solutionInfo(String CourseCode, String assignmentCode)
    {
        System.out.println("Student name: "+ this.name+ "his/her ID is : "+ this.ID+ "his/her Email is : "+this.email+
                "\nhis/her solution is : ");
        this.MyCourses.get(CourseCode).getAssignment(assignmentCode).getSolution();
    }
    public boolean hasSolutionInThisAss(String courseCode , String assCode)
    {
        return this.MyCourses.get(courseCode).getAssignment(assCode).getSubmitionStatus();
    }
}

