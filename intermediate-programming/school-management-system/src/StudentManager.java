import java.io.*;
import java.util.*;

public class StudentManager {
    private static final String FILE_NAME = "students.csv";
    private static final List<Student> studentList = new ArrayList<>();

    public static List<Student> getStudentList() {
        return studentList;
    }

    public static void addStudent(Student student) {
        studentList.add(student);
    }

    public static void removeStudent(Student student) {
        studentList.remove(student);
    }

    public static void saveStudentList() {
        if (studentList.isEmpty())
            return;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student student : studentList) {
                writer.write(String.join(",", Arrays.asList(
                        student.getId(),
                        student.getFirstName(),
                        student.getMiddleName(),
                        student.getLastName(),
                        student.getSex(),
                        String.valueOf(student.getPwd()),
                        student.getInstitute(),
                        student.getProgram(),
                        String.valueOf(student.getYear()),
                        student.getSection())));
                writer.newLine();
            }
            System.out.print("\n[ Info ] Student list saved successfully.\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadStudentList() {
        File file = new File(FILE_NAME);
        if (!file.exists())
            return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                studentList.add(new Student(
                        data[0], data[1], data[2], data[3], data[4],
                        Boolean.parseBoolean(data[5]),
                        data[6], data[7],
                        Integer.parseInt(data[8]),
                        data[9]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
