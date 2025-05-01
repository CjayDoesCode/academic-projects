import java.util.*;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);

    public static void displayMenu() {
        while (true) {
            System.out.print("""
                    ================================================================================
                                                School Management System
                    ================================================================================
                    [ 1 ] Add a student
                    [ 2 ] Remove a student by ID
                    [ 3 ] Update a student by ID
                    [ 4 ] Search a student by ID
                    [ 5 ] List all students
                    [ 6 ] Save and exit
                    ================================================================================
                    Enter your choice:\s""");

            try {
                switch (Integer.parseInt(scanner.nextLine())) {
                    case 1:
                        addStudent(scanner);
                        break;
                    case 2:
                        removeStudent(scanner);
                        break;
                    case 3:
                        updateStudent(scanner);
                        break;
                    case 4:
                        searchStudent(scanner);
                        break;
                    case 5:
                        listStudents();
                        break;
                    case 6:
                        StudentManager.saveStudentList();
                        return;
                    default:
                        System.out.print("\n[ Error ] Input is not a valid choice.\n\n");
                        break;
                }
            } catch (Exception e) {
                System.out.print("\n[ Error ] Input is not a valid number.\n\n");
            }
        }
    }

    public static void addStudent(Scanner scanner) {
        Student student = new Student();
        System.out.print("\n[ Info ] Enter student details.\n\n");

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

        StudentManager.addStudent(student);
        System.out.print("\n[ Info ] Student added successfully.\n\n");
    }

    public static void removeStudent(Scanner scanner) {
        System.out.print("\nEnter ID: ");
        String id = scanner.nextLine();

        for (Student student : StudentManager.getStudentList()) {
            if (student.getId().equals(id)) {
                StudentManager.removeStudent(student);
                System.out.print("\n[ Info ] Student removed successfully.\n\n");
                return;
            }
        }

        System.out.print("\n[ Error ] Student could not be found.\n\n");
    }

    public static void updateStudent(Scanner scanner) {
        System.out.print("\nEnter ID: ");
        String id = scanner.nextLine();

        for (Student student : StudentManager.getStudentList()) {
            if (!student.getId().equals(id))
                continue;

            System.out.print("\n[ Info ] Enter new student details.\n\n");

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

            System.out.print("\n[ Info ] Student details updated successfully.\n\n");
            return;
        }

        System.out.print("\n[ Info ] Student could not be found.\n\n");
    }

    public static void searchStudent(Scanner scanner) {
        System.out.print("\nEnter ID: ");
        String id = scanner.nextLine();

        for (Student student : StudentManager.getStudentList()) {
            if (student.getId().equals(id)) {
                System.out.printf("\n%-7s  %-20s  %-6s  %-5s  %-9s  %-8s  %-4d  %-7s\n",
                        "ID", "Name", "Sex", "PWD", "Institute", "Program", "Year", "Section");

                System.out.printf("%-7s  %-20s  %-6s  %-5s  %-9s  %-8s  %-4d  %-7s\n\n",
                        student.getId(), student.getFullName(20), student.getSex(), student.getPwd(),
                        student.getInstitute(), student.getProgram(), student.getYear(), student.getSection());

                return;
            }
        }

        System.out.print("\n[ Info ] Student could not be found.\n\n");
    }

    public static void listStudents() {
        List<Student> studentList = StudentManager.getStudentList();

        if (studentList.isEmpty()) {
            System.out.printf("\n[ Info ] Student list is empty.\n\n");
            return;
        }

        System.out.print("""

                ================================================================================
                                                  Student List
                ================================================================================
                ID       Name                  Sex     PWD    Institute  Program   Year  Section
                ================================================================================
                """);

        for (Student student : studentList) {
            System.out.printf("%-7s  %-20s  %-6s  %-5s  %-9s  %-8s  %-4d  %-7s\n",
                    student.getId(), student.getFullName(20), student.getSex(), student.getPwd(),
                    student.getInstitute(), student.getProgram(), student.getYear(), student.getSection());
        }

        System.out.print("=".repeat(80) + "\n\n");
    }
}
