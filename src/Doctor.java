import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Doctor implements Serializable {
    private Map <String , Course> MyCourses =new HashMap();
    private static Scanner input=new Scanner(System.in);
    private String name;
    private String ID;
    private String email;

    private boolean hasThisCourse(String code)
    {
        return this.MyCourses.containsKey(code);
    }
    private void menuForCourseView()
    {
        System.out.println("1) List course assignments\n"+
                           "2) Create assignment\n"+
                           "3) View assignment\n"+
                           "4) Back ");
    }
    private int courseViewValidation()
    {
        menuForCourseView();
        int choice=input.nextInt();
        while(choice!=1 && choice!=2 && choice!=3 && choice!=4){
            System.out.println("invalid choice ... try again ");
            choice=input.nextInt();
        }
        return choice;
    }
    private void menuForAssignmentView()
    {
        System.out.println("1) show information\n"+
                           "2) show grades report\n"+
                           "3) list solutions\n"+
                           "4) View Solution\n"+
                           "5) update Total grade\n"+
                           "6) Back"
                );
    }
    private int assignmentViewValidation()
    {
        menuForAssignmentView();
        int choice=input.nextInt();
        while(choice!=1 && choice!=2 && choice!=3 && choice!=4 && choice!=5 && choice!=6){
            System.out.println("invalid choice ... try again ");
            choice=input.nextInt();
        }
        return choice;
    }
    private void menuForSolutionView()
    {
        System.out.println("1) show info\n"+
                           "2) set grads\n" +
                           "3) set comments\n"+
                           "4) Back");
    }
    private int validateForSolutionView()
    {
        menuForSolutionView();
        int choice = input.nextInt();
        while (choice != 1 && choice != 2 && choice != 3 && choice != 4) {
            System.out.println("invalid choice ... try again ");
            choice = input.nextInt();
        }
        return choice;
    }
    private void doctorMenu()
    {
        System.out.println(
                "1) List Courses\n" +
                "2) Create course\n" +
                "3) View Course\n" +
                "4) Log out"
        );
    }


    public void setEmail(String email){this.email=email; } /**in the form of mail address */
    public String getEmail(){return this.email; }
    public Doctor(String name, String id) {
        this.name=name;
        this.ID=id;
    }
    public String getID()
    {
        return this.ID;
    }
    public void createCourse(Data data)
    {
        System.out.println("enter Course code: ");String code=input.next();
        System.out.println("enter Course Name: ");String name=input.next();
        Course course=new Course(name, code);
        course.setDoctorName(this.name);
        MyCourses.put(code, course);
        System.out.println("need to add TAs ?  ... enter y for yes or n for No ");
        char choice=input.next().charAt(0);
        while(choice=='y')
        {
            data.showAvailableTAs();
            System.out.println("Enter TA's ID: ");
            String id=input.next();
            while(!data.hasTA(id)){
                System.out.println("wrong TA's ID.... try again ");
                id=input.next();
            }
            data.getTA(id).TackCourse(course);
            course.AddTA( data.getTA(id));
            System.out.println("need to add more TAs ?  ... enter y for yes or n for No ");
            choice=input.next().charAt(0);
        }
        course.setDoctorName(this.name);
        course.setDoctorId(this.ID);
        data.addCourse(course);

    }
    public void createAssignment(String courseCode, Data data)
    {
        System.out.println("Enter the Assignment code : "); String code=input.next();
        System.out.println("Enter the Total grade of this assignment: ");double grade=input.nextDouble();

        System.out.println("Enter the number of days : ");int day=input.nextInt();
        System.out.println("Enter the number of hours : ");int hour=input.nextInt();

        Assignment ass=new Assignment(code, grade);
        ass.setAssignmentTime(day, hour);
        this.MyCourses.get(courseCode).addAssignment(ass);
        data.updateCourse(MyCourses.get(courseCode));

    }
    public void listCourses()
    {
        if(!this.MyCourses.isEmpty()) {
            for (String key : MyCourses.keySet()) {
                String value = MyCourses.get(key).getName();
                System.out.println("Course Code: " + key + "  Course Name: " + value);
            }
        }
        else
            System.out.println("u have no courses ");
    }
    public int validateDoctorMenu()
    {
        doctorMenu();
        int choice=input.nextInt();
        while(choice!=1 && choice!=2 && choice!=3 && choice!=4 ){
            System.out.println("invalid choice ... try again ");
            choice=input.nextInt();
        }
        return choice;
    }
    public void viewCourse(Data data)
    {
        listCourses();
        int choice1;
        int choice2;
        int choice3;
        Assignment assignment=null;
        Course course=null;


        System.out.println("chose a course and type it's code: ");String code=input.next();
        while(!hasThisCourse(code)){
            System.out.println("wrong Course code try again ");code=input.next();
        }
        course=this.MyCourses.get(code);
        course.courseInfo();

        while (true)
        {
             choice1=courseViewValidation();

            if(choice1==1)course.listAssignments();
            else if(choice1==2)createAssignment(course.getCode(), data);
            else if(choice1==3)
                {
                    course.listAssignments();

                    System.out.println("Enter the Assignment code : ");String assCode=input.next();
                    while(!course.CourseHasThisAssignment(assCode)){
                        System.out.println("invalid assignment code try again ");assCode=input.next();
                    }
                     assignment=course.getAssignment(assCode);
                    while (true){
                        choice2=assignmentViewValidation();

                    if(choice2==1)assignment.showInfo();
                    else if(choice2==2)course.GradeReportForSpecificAss(assCode);
                    else if(choice2==3)course.ListSolutionsForSpecificAss(assCode);
                    if(choice2==4)
                        {
                            System.out.println("enter the student ID ");String stuID=input.next();
                            while(!course.StudentRegisterInThisCourse(stuID)){
                                System.out.println("Wrong id plz try again ");
                                stuID=input.next();
                            }
                            while(true){
                                 choice3= validateForSolutionView();

                              if(choice3==1)course.getStudent(stuID).solutionInfo(course.getCode(), stuID);
                              else if(choice3==2)
                              {
                                  System.out.println("set the student grade: ");
                                  double grade=input.nextDouble();
                                  while(grade>course.getAssignment(assCode).getTotalGrade()){
                                      System.out.println("Wrong grade plz enter grade less than or equal the total grade");
                                      grade=input.nextDouble();
                                  }
                                  course.getStudent(stuID).setGrade(course.getCode(), assCode, grade);
                              }
                              else if(choice3==3)
                              {
                                  System.out.println("type ur comment : ");
                                  String comment=input.nextLine();
                                  course.getStudent(stuID).setComment(course.getCode(), assCode, comment);
                              }
                              else
                                  break;
                        }
                        }
                    else if(choice2==5){
                        System.out.println("enter the new grade ");int newGrade=input.nextInt();
                        course.getAssignment(assCode).updateTotalGrade(newGrade);

                    }
                    else break;
                    }
                }
            else break;
        }
        this.MyCourses.replace(course.getCode(), course);

    }
    public void LogOut(Data data)
    {
      data.updateDoctor(this);
    }

    //public void getStatistics(){}


}
