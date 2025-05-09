import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        final StudentManager studentManager = new StudentManager(new ArrayList<>(), "students.csv", ",");
        studentManager.loadStudentList();

        final Menu menu = new Menu(studentManager);
        menu.display();
    }
}
