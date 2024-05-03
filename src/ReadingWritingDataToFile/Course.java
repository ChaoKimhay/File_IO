package ReadingWritingDataToFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.nocrala.tools.texttablefmt.Table;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Course{
    private Integer id;
    private String courseName;
    private boolean courseAvailable;

    public boolean getCourseAvailable() {
        return true;
    }
}

interface CourseService{
    abstract void addNewCourse();
    abstract void getAllCourse();
    abstract void getCourseById();
}

class CourseServiceImp implements CourseService{
    @Override
    public void addNewCourse() {
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.print("[+] Insert course title : ");
        String title = new Scanner(System.in).nextLine();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("course.csv", true));
            Course course = new Course();
            Random random = new Random();
            course.setId( random.nextInt(99) + 10);
            course.setCourseName(title);
            course.setCourseAvailable(true);
            String data = course.getId() + "," + course.getCourseName() + "," + LocalDate.now() + "," + LocalDate.of(2024,05,15) + "," + course.getCourseAvailable() + "\n";
            writer.write(data);
            writer.close();
            System.out.println("\t [+] Success!");

        } catch (Exception ignored) {}
    }
    @Override
    public void getAllCourse() {
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        try {
            BufferedReader reader = new BufferedReader(new FileReader("course.csv"));
            Table table = new Table(5);
            for (int i = 0; i < 5; i++) {
                table.setColumnWidth(i, 20, 20);
            }
            table.addCell("CourseID");
            table.addCell("CourseName");
            table.addCell("CourseStartDate");
            table.addCell("CourseEndDate");
            table.addCell("CourseAvailable");
            String data;
            while ((data = reader.readLine()) != null) {
                String[] result = data.split(",");
                for (int i = 0; i < 5; i++) {
                    table.addCell(result[i], 1);
                }
            }
            System.out.println(table.render());
        } catch (Exception ignored) {}
    }

    @Override
    public void getCourseById() {
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.print("Enter course ID: ");
        int searchId = new Scanner(System.in).nextInt();
        boolean found = false;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("course.csv"));
            Table table = new Table(5);
            for (int i = 0; i < 5; i++) {
                table.setColumnWidth(i, 20, 20);
            }
            table.addCell("CourseID");
            table.addCell("CourseName");
            table.addCell("CourseStartDate");
            table.addCell("CourseEndDate");
            table.addCell("CourseAvailable");
            String data;
            while ((data = reader.readLine()) != null) {
                String[] result = data.split(",");
                if (Integer.parseInt(result[0]) == searchId) {
                    for (int i = 0; i < 5; i++) {
                        table.addCell(result[i], 1);
                    }
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("ID not found : " + searchId);
            } else {
                System.out.println(table.render());
            }
            reader.close();
        } catch (Exception ignored) {}
    }
}
class View{
    public static void menu(){
        System.out.println("==============>> \uD83D\uDCCB MENU \uD83D\uDCCB <<==============");
        System.out.println("\t\t\t 1. Add New Course");
        System.out.println("\t\t\t 2. Get All Course");
        System.out.println("\t\t\t 3. Get Course By Id");
        System.out.println("\t\t\t 0. Exit");
        System.out.println("================================");
        System.out.print("[+] Insert the option : ");
    }
}