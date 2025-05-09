import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class StudentManager {
    private final List<Student> studentList;
    private final String fileName;
    private final String separator;

    public StudentManager(List<Student> studentList, String fileName, String separator) {
        this.studentList = studentList;
        this.fileName = fileName;
        this.separator = separator;
    }

    public Student createStudent() {
        final Student student = new Student();
        student.setId(Utility.getStringInput("ID: "));
        student.setFirstName(Utility.getStringInput("First Name: "));
        student.setMiddleName(Utility.getStringInput("Middle Name: "));
        student.setLastName(Utility.getStringInput("Last Name: "));
        student.setSex(Utility.getStringInput("Sex (Male/Female): "));
        student.setPWD(Utility.getBooleanInput("PWD (y/n): "));
        student.setInstitute(Utility.getStringInput("Institute: "));
        student.setProgram(Utility.getStringInput("Program: "));
        student.setYear(Utility.getIntInput("Year: ", 1, 4));
        student.setSection(Utility.getStringInput("Section: "));

        return student;
    }

    public List<Student> getStudentList() {
        return this.studentList;
    }

    public void addStudent(Student student) {
        this.studentList.add(student);
    }

    public void removeStudent(Student student) {
        this.studentList.remove(student);
    }

    public Student getStudentById(String id) {
        for (final Student student : this.studentList) {
            if (student.getId().equals(id)) return student;
        }

        return null;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void saveStudentList() {
        if (this.studentList.isEmpty()) return;

        try (final BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileName))) {
            for (final Student student : this.studentList) {
                writer.write(String.join(this.separator, Arrays.asList(
                    student.getId(),
                    student.getFirstName(),
                    student.getMiddleName(),
                    student.getLastName(),
                    student.getSex(),
                    String.valueOf(student.getPWD()),
                    student.getInstitute(),
                    student.getProgram(),
                    String.valueOf(student.getYear()),
                    student.getSection()
                )));

                writer.newLine();
            }
            System.out.print("\n[ Info ] Student list saved successfully.\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings({"CallToPrintStackTrace", "UnnecessaryTemporaryOnConversionFromString"})
    public void loadStudentList() {
        final File file = new File(this.fileName);
        if (!file.exists()) return;

        try (final BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                final String[] data = line.split(this.separator);
                
                studentList.add(new Student(
                    data[0], data[1], data[2], data[3], data[4],
                    Boolean.parseBoolean(data[5]),
                    data[6], data[7],
                    Integer.parseInt(data[8]),
                    data[9]
                ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
