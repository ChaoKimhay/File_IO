package ReadingWritingDataToFile;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int option;
        CourseServiceImp obj = new CourseServiceImp();
        do{
            View.menu();
            option = new Scanner(System.in).nextInt();
            switch(option) {
                case 1:
                {
                    obj.addNewCourse();
                }
                break;
                case 2:
                {
                    obj.getAllCourse();
                }
                break;
                case 3:
                {
                    obj.getCourseById();
                }
                break;
                case 0:
                {
                    System.out.println("Exit");
                }
                break;
                default:
                    System.out.println("Invalid option");
                    break;
            }

        }while(option != 0);

    }
}
