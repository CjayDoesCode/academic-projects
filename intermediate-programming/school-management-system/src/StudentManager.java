import java.util.*;
import java.io.*;

public class StudentManager {
    private static final List<Student> studentList = new ArrayList<>();
    private static final String fileName = "students.csv";

    private static final String[] yearDomain = { "1", "2", "3", "4" };
    private static final String[] sexDomain = { "Male", "Female" };
    private static final String[] pwdDomain = { "Yes", "No" };
    private static final String[] programDomain = {
            "BPEd", "BTVTEd", "BAELS", "BSNEd", "BSPsych", "BSMath", // IEAS
            "BSCS", "BSIS", "ACT", "BLIS", // ICSLIS
            "BSTM", "BSEntrep", "BSA" // IBM
    };

    private static Student getStudentById(String id) {
        for (Student student : studentList)
            if (student.getId().equals(id))
                return student;

        return null;
    }

    private static Student getStudentByName(String name) {
        final List<Student> students = new ArrayList<>();

        studentList.forEach(student -> {
            if (student.getFullName().toLowerCase().contains(name.toLowerCase()))
                students.add(student);
        });

        if (students.size() == 0)
            return null;

        if (students.size() == 1)
            return students.get(0);

        System.out.println("\n[ Info ] Multiple students found. Choose one.\n");
        System.out.print("""
                ================================================================================
                    Student List
                ================================================================================
                ID       Name
                ================================================================================
                """);

        for (final Student student : students)
            System.out.printf("%-7s  %-71s\n", student.getId(), student.getFullName(71));

        System.out.printf("%s\n\n", "=".repeat(80));

        String id;
        do {
            id = Utility.getStringInput("Enter ID: ", 7);
            for (final Student student : students) {
                if (student.getId().equals(id)) {
                    return student;
                }
            }
            System.out.println("\n[ Error ] Student could not be found. Try Again.\n");
        } while (true);
    }

    private static void removeStudent(Student student) {
        if (student == null) {
            System.out.println("\n[ Error ] Student could not be found.\n");
            return;
        }

        studentList.remove(student);
        System.out.println("\n[ Info ] Student removed successfully.\n");
    }

    private static void updateStudent(Student student) {
        if (student == null) {
            System.out.println("\n[ Error ] Student could not be found.\n");
            return;
        }

        System.out.print("""

                ================================================================================
                    Student Details
                ================================================================================
                [ 1 ] ID
                [ 2 ] Name
                [ 3 ] Sex
                [ 4 ] PWD
                [ 5 ] Program
                [ 6 ] Year
                [ 7 ] Section
                [ 8 ] Cancel
                ================================================================================
                """);

        switch (Utility.getIntInput("Enter your choice: ", 1, 8)) {
            case 1 -> {
                String id;
                boolean exists;
                do {
                    id = Utility.getStringInput("ID: ", 7, true);
                    exists = getStudentById(id) != null;
                    if (exists)
                        System.out.println("\n[ Error ] Student ID already exists. Try Again.\n");
                } while (exists);
                student.setId(id);
            }
            case 2 -> {
                student.setFirstName(Utility.getStringInput("First Name: ", true));
                student.setMiddleName(Utility.getStringInput("Middle Name: ", true));
                student.setLastName(Utility.getStringInput("Last Name: ", true));
            }
            case 3 -> student.setSex(Utility.getStringInput("Sex (Male/Female): ", sexDomain));
            case 4 -> student.setPwd(Utility.getStringInput("PWD (Yes/No): ", pwdDomain));
            case 5 -> student.setProgram(Utility.getStringInput("Program: ", programDomain));
            case 6 -> student.setYear(Utility.getStringInput("Year: ", yearDomain));
            case 7 -> student.setSection(Utility.getStringInput("Section: ", 4, true));
            case 8 -> {
                return;
            }
        }

        System.out.println("\n[ Info ] Student detail updated successfully.\n");
    }

    private static void searchStudent(Student student) {
        if (student == null) {
            System.out.println("\n[ Error ] Student could not be found.\n");
            return;
        }

        System.out.print("""

                ================================================================================
                    Student Details
                ================================================================================
                ID       Name                               Sex     PWD  Program   Year  Section
                ================================================================================
                """);

        System.out.printf(
                "%-7s  %-33s  %-6s  %-5s  %-8s  %-4s  %-7s\n",
                student.getId(),
                student.getFullName(33),
                student.getSex(),
                student.getPwd(),
                student.getProgram(),
                student.getYear(),
                student.getSection());

        System.out.printf("%s\n\n", "=".repeat(80));
    }

    private static void displayStudents(List<Student> studentList) {
        if (studentList.isEmpty()) {
            System.out.println("\n[ Info ] Student list is empty.\n");
            return;
        }

        System.out.print("""

                ================================================================================
                    Student List
                ================================================================================
                ID       Name                               Sex     PWD  Program   Year  Section
                ================================================================================
                """);

        for (final Student student : studentList) {
            System.out.printf("%-7s  %-33s  %-6s  %-3s  %-8s  %-4s  %-7s\n",
                    student.getId(),
                    student.getFullName(33),
                    student.getSex(),
                    student.getPwd(),
                    student.getProgram(),
                    student.getYear(),
                    student.getSection());
        }

        System.out.printf("%s\n\n", "=".repeat(80));
    }

    public static void createStudent() {
        final Student student = new Student();

        System.out.println("\n[ Info ] Enter student details.\n");

        String id;
        boolean exists;
        do {
            id = Utility.getStringInput("ID: ", 7, true);
            exists = getStudentById(id) != null;
            if (exists)
                System.out.println("\n[ Error ] Student ID already exists. Try Again.\n");
        } while (exists);
        student.setId(id);

        student.setFirstName(Utility.getStringInput("First Name: ", true));
        student.setMiddleName(Utility.getStringInput("Middle Name: ", false));
        student.setLastName(Utility.getStringInput("Last Name: ", true));
        student.setSex(Utility.getStringInput("Sex (Male/Female): ", sexDomain));
        student.setPwd(Utility.getStringInput("PWD (Yes/No): ", pwdDomain));
        student.setProgram(Utility.getStringInput("Program: ", programDomain));
        student.setYear(Utility.getStringInput("Year: ", yearDomain));
        student.setSection(Utility.getStringInput("Section: ", 4, true));

        studentList.add(student);
        System.out.println("\n[ Info ] Student created successfully.\n");
    }

    public static void removeStudentById() {
        removeStudent(getStudentById(Utility.getStringInput("\nID: ", 7, true)));
    }

    public static void removeStudentByName() {
        removeStudent(getStudentByName(Utility.getStringInput("\nName: ", true)));
    }

    public static void updateStudentById() {
        updateStudent(getStudentById(Utility.getStringInput("\nID: ", 7, true)));
    }

    public static void updateStudentByName() {
        updateStudent(getStudentByName(Utility.getStringInput("\nName: ", true)));
    }

    public static void searchStudentById() {
        searchStudent(getStudentById(Utility.getStringInput("\nID: ", 7, true)));
    }

    public static void searchStudentByName() {
        searchStudent(getStudentByName(Utility.getStringInput("\nName: ", true)));
    }

    public static void displayStudentsById() {
        final List<Student> sortedList = new ArrayList<>(studentList);
        sortedList.sort(Comparator.comparing(Student::getId));
        displayStudents(sortedList);
    }

    public static void displayStudentsByName() {
        final List<Student> sortedList = new ArrayList<>(studentList);
        sortedList.sort(Comparator.comparing(Student::getFullName));
        displayStudents(sortedList);
    }

    public static void save() {
        final List<Student> sortedList = new ArrayList<>(studentList);
        sortedList.sort(Comparator.comparing(Student::getId));

        try (final BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (final Student student : sortedList) {
                writer.write(String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s%n",
                        student.getId(),
                        student.getFirstName(),
                        student.getMiddleName(),
                        student.getLastName(),
                        student.getSex(),
                        student.getPwd(),
                        student.getProgram(),
                        student.getYear(),
                        student.getSection()));
            }

            System.out.println("\n[ Info ] Student list saved successfully.");
        } catch (Exception e) {
            System.out.println("\n[ Error ] Student list could not be saved.");
        }
    }

    public static void load() {
        final File file = new File(fileName);

        try (final BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                final String[] data = line.split(",");
                studentList.add(new Student(
                        data[0],
                        data[1],
                        data[2],
                        data[3],
                        data[4],
                        data[5],
                        data[6],
                        data[7],
                        data[8]));
            }

            System.out.println("\n[ Info ] Student list loaded successfully.\n");
        } catch (Exception e) {
            System.out.println("\n[ Error ] Student list could not be loaded.\n");
        }
    }
}
