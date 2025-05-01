import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManager studentManager = new StudentManager();
        Scanner scanner = new Scanner(System.in);
        int choice;
        while (true) {
            System.out.printf("""
                    ================================================================================
                                                School Management System
                    ================================================================================
                    [ 1 ] Add a student
                    [ 2 ] Remove a student by ID
                    [ 3 ] Update a student by ID
                    [ 4 ] List all students
                    [ 5 ] Save and exit
                    ================================================================================
                    Enter your choice:\s""");

            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.printf("\n[ Error ] Input is an invalid integer.\n\n");
                scanner.nextLine();
                return;
            }

            switch (choice) {
                case 1:
                    studentManager.addStudent(scanner);
                    break;
                case 2:
                    studentManager.removeStudent(scanner);
                    break;
                case 3:
                    studentManager.updateStudent(scanner);
                    break;
                case 4:
                    studentManager.listStudents();
                    break;
                case 5:
                    studentManager.save();
                    System.exit(0);
                default:
                    System.out.printf("\n[ Error ] %d is an invalid choice.\n\n", choice);
            }
        }
    }
}
