import java.io.Serializable;
import java.io.*;

public class SystemData implements Serializable {

    private transient FileInputStream fileStream ;
    private transient ObjectInputStream os;

    public  Data loadData()/**load all data from file*/
    {
        try {
            this.fileStream= new FileInputStream("systemData.ser");
            this.os = new ObjectInputStream(fileStream);
            Data re=(Data)os.readObject();
            os.close();
            fileStream.close();
            fileStream=null;
            os=null;
            return re;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        System.out.println("didn't loaded ");
        return new Data();
    }

    public void saveData(Data data)/**save all data in file*/
    {
        try{
            FileOutputStream fs=new FileOutputStream("systemData.ser");
            ObjectOutputStream os=new ObjectOutputStream(fs);
            os.writeObject(data);
            os.flush();
            os.close();
            fs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }







    public static void main(String arg[]) {

        Data data=new Data();
        SystemData sd=new SystemData();

        Student s=new Student("mohamed", "111");
        Student ss=new Student("saber", "222");

        Doctor dr=new Doctor("hashem", "333");

        TA ta=new TA("hesham", "555");
        data.addTA(ta);
        Course course=new Course("algorithm", "444");
        data.addStudent(s);
        data.addStudent(ss);
        data.addDr(dr);
        data.addCourse(course);
        sd.saveData(data);


    }


}
