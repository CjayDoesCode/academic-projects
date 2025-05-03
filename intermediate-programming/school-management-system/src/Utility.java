import java.util.*;

public class Utility {
    public static void setStudentFields(Scanner scanner, Student student) {
        System.out.print("ID: ");
        student.setId(scanner.nextLine());

        System.out.print("First Name: ");
        student.setFirstName(scanner.nextLine());

        System.out.print("Middle Name: ");
        student.setMiddleName(scanner.nextLine());

        System.out.print("Last Name: ");
        student.setLastName(scanner.nextLine());

        System.out.print("Sex (Male/Female): ");
        student.setSex(scanner.nextLine());

        System.out.print("PWD (True/False): ");
        student.setPwd(Boolean.parseBoolean(scanner.nextLine()));

        System.out.print("Institute: ");
        student.setInstitute(scanner.nextLine());

        System.out.print("Program: ");
        student.setProgram(scanner.nextLine());

        System.out.print("Year: ");
        student.setYear(Integer.parseInt(scanner.nextLine()));

        System.out.print("Section: ");
        student.setSection(scanner.nextLine());
    }
}
