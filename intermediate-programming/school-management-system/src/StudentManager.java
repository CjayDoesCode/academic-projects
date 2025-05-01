import java.util.Scanner;
import java.util.ArrayList;

public class StudentManager {
    // fields
    private ArrayList<Student> studentList;

    // constructor
    public StudentManager() {
        studentList = new ArrayList<Student>();
        // load studentList
    }

    // methods
    public void addStudent(Scanner scanner) {
        System.out.print("\nEnter ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter Middle Name: ");
        String middleName = scanner.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter Sex (Male/Female): ");
        String sex = scanner.nextLine();

        System.out.print("Enter PWD (True/False): ");
        Boolean pwd = Boolean.parseBoolean(scanner.nextLine());

        System.out.print("Enter Institute: ");
        String institute = scanner.nextLine();

        System.out.print("Enter Program: ");
        String program = scanner.nextLine();

        System.out.print("Enter Year: ");
        int year = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Section: ");
        String section = scanner.nextLine();

        Student student = new Student(id, firstName, middleName, lastName, sex, pwd, institute, program, year, section);
        studentList.add(student);

        System.out.print("\n[ Info ] Student added successfully.\n\n");
    }

    public void removeStudent(Scanner scanner) {

    }

    public void updateStudent(Scanner scanner) {

    }

    public void listStudents() {
        if (studentList.isEmpty()) {
            System.out.printf("\n[ Info ] Student list is empty.\n\n");
            return;
        }

        System.out.printf("""

                ================================================================================
                                                  Student List
                ================================================================================
                ID       Name                  Sex     PWD    Institute  Program   Year  Section
                ================================================================================
                """);

        for (Student student : studentList) {
            System.out.printf("%-7s  %-20s  %-6s  %-5s  %-9s  %-8s  %-4d  %-7s\n",
                    student.getId(),
                    student.getFullName(20),
                    student.getSex(),
                    student.getPwd(),
                    student.getInstitute(),
                    student.getProgram(),
                    student.getYear(),
                    student.getSection());

        }

        System.out.printf("================================================================================\n\n");
    }

    public void save() {

    }
}
