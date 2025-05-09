public class Menu {
    private final StudentManager studentManager;

    public Menu(StudentManager studentManager) {
        this.studentManager = studentManager;
    }

    public void display() {
        System.out.print(
            """
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
            """
        );
        
        final int input = Utility.getIntInput("Enter your choice: ",1, 6);
        switch (input) {
            case 1 -> addStudent();
            case 2 -> removeStudent(Menu.getIdInput());
            case 3 -> updateStudent(Menu.getIdInput());
            case 4 -> searchStudent(Menu.getIdInput());
            case 5 -> listStudents();
            case 6 -> this.studentManager.saveStudentList();
        }
        
        if (input != 6) this.display();
    }

    public static void printInfo(String info) {
        System.out.printf("%n[ Info ] %s%n%n", info);
    }

    public static void printError(String error) {
        System.out.printf("%n[ Error ] %s%n%n", error);
    }

    public static String getIdInput() {
        return Utility.getStringInput("ID: ");
    }

    public void addStudent() {
        Menu.printInfo("Enter student details");

        final Student student = this.studentManager.createStudent();
        this.studentManager.addStudent(student);

        Menu.printInfo("Student added successfully.");
    }

    public void removeStudent(String id) {
        final Student student = this.studentManager.getStudentById(id);

        if (student == null) {
            Menu.printError("Student could not be found.");
        } else {
            this.studentManager.removeStudent(student);
            Menu.printInfo("Student removed successfully.");
        }
    }

    public void updateStudent(String id) {
        final Student student = this.studentManager.getStudentById(id);

        if (student == null) {
            Menu.printInfo("Student could not be found.");
        } else {
            Menu.printInfo("Enter new student details.");
            final Student updatedStudent = this.studentManager.createStudent();

            this.studentManager.removeStudent(student);
            this.studentManager.addStudent(updatedStudent);
            Menu.printInfo("Student details update successfully.");
        }
    }

    public void searchStudent (String id) {
        final Student student = this.studentManager.getStudentById(id);

        if (student == null) {
            Menu.printInfo("Student could not be found.");
        } else {
            System.out.printf(
                "%n%-7s  %-20s  %-6s  %-5s  %-9s  %-8s  %-4s  %-7s%n",
                "ID", "Name", "Sex", "PWD", "Institute", "Program", "Year", "Section"
            );

            System.out.printf(
                "%-7s  %-20s  %-6s  %-5s  %-9s  %-8s  %-4d  %-7s%n%n",
                student.getId(), student.getFullName(20), student.getSex(), student.getPWD(),
                student.getInstitute(), student.getProgram(), student.getYear(), student.getSection()
            );
        }
    }

    public void listStudents() {
        if (this.studentManager.getStudentList().isEmpty()) {
            Menu.printInfo("Student list is empty.");
            return;
        }

        System.out.print(
            """
            ================================================================================
                                                Student List
            ================================================================================
            ID       Name                  Sex     PWD    Institute  Program   Year  Section
            ================================================================================
            """
        );

        for (final Student student : this.studentManager.getStudentList()) {
            System.out.printf(
                "%-7s  %-20s  %-6s  %-5s  %-9s  %-8s  %-4d  %-7s%n",
                student.getId(), student.getFullName(20), student.getSex(), student.getPWD(),
                student.getInstitute(), student.getProgram(), student.getYear(), student.getSection()
            );
        }

        System.out.printf("%s%n%n", "=".repeat(80));
    }
}
