public class Menu {
    public static void display() {
        while (true) {
            System.out.print("""
                    ================================================================================
                        School Management System
                    ================================================================================
                    [ 1 ] Create a student
                    [ 2 ] Remove a student by ID
                    [ 3 ] Update a student by ID
                    [ 4 ] Search a student by ID
                    [ 5 ] Display all students by ID
                    [ 6 ] Remove a student by Name
                    [ 7 ] Update a student by Name
                    [ 8 ] Search students by Name
                    [ 9 ] Display all students by Name
                    [ 0 ] Save and exit
                    ================================================================================
                    """);

            switch (Utility.getIntInput("Enter your choice: ", 0, 9)) {
                case 1 -> StudentManager.createStudent();
                case 2 -> StudentManager.removeStudentById();
                case 3 -> StudentManager.updateStudentById();
                case 4 -> StudentManager.searchStudentById();
                case 5 -> StudentManager.displayStudentsById();
                case 6 -> StudentManager.removeStudentByName();
                case 7 -> StudentManager.updateStudentByName();
                case 8 -> StudentManager.searchStudentByName();
                case 9 -> StudentManager.displayStudentsByName();
                case 0 -> {
                    StudentManager.save();
                    return;
                }
            }
        }
    }
}
