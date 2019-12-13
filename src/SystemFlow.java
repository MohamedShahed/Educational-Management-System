import java.util.Scanner;

public class SystemFlow {
    private Data data=new Data();
    private SystemData sysData=new SystemData();
    private Scanner input=new Scanner(System.in);


    private void loginMenu()
    {
        System.out.println("1) Admin \n" +
                           "2) Doctor \n" +
                           "3) TA \n" +
                           "4) Student \n" +
                           "5) Close System");
    }

    private int validateLoginMenu()
    {
        loginMenu();
        int choice=input.nextInt();
        while(choice!=1 && choice!=2 && choice!=3 && choice!=4 && choice!=5)
        {
            System.out.println("Wrong choice ... try again ");
            loginMenu();
            choice=input.nextInt();
        }
        return choice;
    }


    private String validateStudentID()
    {
        String id=input.next();
        while(!data.hasStudent(id))
        {
            System.out.println("Wrong ID try again ");
            id=input.next();
        }
        return id;

    }
    private String validateDoctorID()
    {
        String id=input.next();
        while(!data.hasDoctur(id))
        {
            System.out.println("Wrong ID try again ");
            id=input.next();
        }
        return id;

    }
    private void validateAdmin(){}
    private void validateTA(){}





    public void LogIn()
    {

       this.data=sysData.loadData();
       assert this.data !=null;


        boolean status=true;
        while(status)
        {
            int choice= validateLoginMenu();
            switch (choice)
            {
                case 2:
                    System.out.println("enter ur ID plz: ");
                    String drID=validateDoctorID();
                    Doctor dr=this.data.getDoctor(drID);
                    System.out.println("*********Welcome Dr: "+ dr.getName()+" ***********");
                    int doctorChoice;
                    while(true) {
                        doctorChoice = dr.validateDoctorMenu();
                        if (doctorChoice == 1) dr.listCourses();
                        else if (doctorChoice == 2) dr.createCourse(data);
                        else if (doctorChoice == 3) dr.viewCourse(data);
                        else {dr.LogOut(data);break;}
                    }
                    break;
                case 4:
                    System.out.println("enter ur ID plz: ");String stuID=this.validateStudentID();
                    Student student=data.getStudent(stuID);
                    int StuChoice;
                    while(true) {
                        StuChoice=student.choiceValidatoin();
                        if (StuChoice == 1) student.registerInCourse(data);
                        else if (StuChoice == 2) student.listAllCourses();
                        else if (StuChoice == 3) student.ViewCourse();
                        else if (StuChoice == 4) student.gradeReport();
                        else {
                            student.LogOut(data);
                            break;
                        }
                    }
                    break;
                case 5:
                    sysData.saveData(data);
                    status=false;
                    System.out.println("the System is terminated ");
                    break;

            }
        }
    }

    public static void main(String arg[])
    {
        SystemFlow run=new SystemFlow();
        run.LogIn();
    }
}
