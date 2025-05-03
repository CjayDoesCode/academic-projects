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
        Utility.setStudentFields(scanner, student);
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
            Utility.setStudentFields(scanner, student);

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
