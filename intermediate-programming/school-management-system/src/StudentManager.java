import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class StudentManager {
    // fields
    private ArrayList<Student> studentList;
    static String fileName = "students.csv";

    // constructor
    public StudentManager() {
        studentList = new ArrayList<Student>();
        // load studentList
    }

    // methods
    private void addStudent(Student student) {
        this.studentList.add(student);
    }

    public void addStudent(Scanner scanner) {
        System.out.print("\nEnter ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter Middle Name: ");
        String middleName = scanner.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter Sex (Male/Female): ");
        String sex = scanner.nextLine();

        System.out.print("Enter PWD (True/False): ");
        Boolean pwd = Boolean.parseBoolean(scanner.nextLine());

        System.out.print("Enter Institute: ");
        String institute = scanner.nextLine();

        System.out.print("Enter Program: ");
        String program = scanner.nextLine();

        System.out.print("Enter Year: ");
        int year = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Section: ");
        String section = scanner.nextLine();

        Student student = new Student(id, firstName, middleName, lastName, sex, pwd, institute, program, year, section);
        this.addStudent(student);

        System.out.print("\n[ Info ] Student added successfully.\n\n");
    }

    public void removeStudent(Scanner scanner) {
        if (studentList.isEmpty()) {
            System.out.printf("\n[ Info ] Student list is empty.\n\n");
            return;
        }

        System.out.print("\nEnter ID: ");
        String id = scanner.nextLine();

        for (Student student : studentList) {
            if (student.getId().equals(id)) {
                studentList.remove(student);
                System.out.print("\n[ Info ] Student removed successfully.\n\n");
                return;
            }
        }

        System.out.print("\n[ Info ] Student not found.\n\n");
    }

    public void updateStudent(Scanner scanner) {
        if (studentList.isEmpty()) {
            System.out.printf("\n[ Info ] Student list is empty.\n\n");
            return;
        }

        System.out.print("\nEnter ID: ");
        String id = scanner.nextLine();

        for (Student student : studentList) {
            if (student.getId().equals(id)) {
                System.out.print("\n[ Info ] Student found. Enter new details.\n\n");

                System.out.print("Enter ID: ");
                student.setId(scanner.nextLine());

                System.out.print("Enter First Name: ");
                student.setFirstName(scanner.nextLine());

                System.out.print("Enter Middle Name: ");
                student.setMiddleName(scanner.nextLine());

                System.out.print("Enter Last Name: ");
                student.setLastName(scanner.nextLine());

                System.out.print("Enter Sex (Male/Female): ");
                student.setSex(scanner.nextLine());

                System.out.print("Enter PWD (True/False): ");
                student.setPwd(Boolean.parseBoolean(scanner.nextLine()));

                System.out.print("Enter Institute: ");
                student.setInstitute(scanner.nextLine());

                System.out.print("Enter Program: ");
                student.setProgram(scanner.nextLine());

                System.out.print("Enter Year: ");
                student.setYear(Integer.parseInt(scanner.nextLine()));

                System.out.print("Enter Section: ");
                student.setSection(scanner.nextLine());

                System.out.print("\n[ Info ] Student details updated successfully.\n\n");

                return;
            }
        }

        System.out.print("\n[ Info ] Student not found.\n\n");
    }

    public void searchStudent(Scanner scanner) {
        if (studentList.isEmpty()) {
            System.out.printf("\n[ Info ] Student list is empty.\n\n");
            return;
        }

        System.out.print("\nEnter ID: ");
        String id = scanner.nextLine();

        for (Student student : studentList) {
            if (student.getId().equals(id)) {
                System.out
                        .print("\nID       Name                  Sex     PWD    Institute  Program   Year  Section\n");

                System.out.printf("%-7s  %-20s  %-6s  %-5s  %-9s  %-8s  %-4d  %-7s\n\n",
                        student.getId(),
                        student.getFullName(20),
                        student.getSex(),
                        student.getPwd(),
                        student.getInstitute(),
                        student.getProgram(),
                        student.getYear(),
                        student.getSection());

                return;
            }
        }

        System.out.print("\n[ Info ] Student not found.\n\n");
    }

    public void listStudents() {
        if (studentList.isEmpty()) {
            System.out.printf("\n[ Info ] Student list is empty.\n\n");
            return;
        }

        System.out.printf("""

                ================================================================================
                                                  Student List
                ================================================================================
                ID       Name                  Sex     PWD    Institute  Program   Year  Section
                ================================================================================
                """);

        for (Student student : studentList) {
            System.out.printf("%-7s  %-20s  %-6s  %-5s  %-9s  %-8s  %-4d  %-7s\n",
                    student.getId(),
                    student.getFullName(20),
                    student.getSex(),
                    student.getPwd(),
                    student.getInstitute(),
                    student.getProgram(),
                    student.getYear(),
                    student.getSection());

        }

        System.out.printf("================================================================================\n\n");
    }

    public void loadFromFile() {
        File file = new File(fileName);
        boolean fileExists = file.exists();

        if (fileExists) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String studentEntry;
                boolean isFirstLine = true;
                while ((studentEntry = reader.readLine()) != null) {
                    if (isFirstLine) {
                        isFirstLine = false;
                        continue; // Skip the header line
                    }
                    String[] studentData = parseCsvLine(studentEntry);
                    boolean pwd = studentData[5].equals("true");
                    int year = Integer.parseInt(studentData[8]);

                    Student student = new Student(
                            studentData[0],
                            studentData[1],
                            studentData[2],
                            studentData[3],
                            studentData[4],
                            pwd,
                            studentData[6],
                            studentData[7],
                            year,
                            studentData[9]);

                    this.addStudent(student);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void save() {
        File file = new File(fileName);

        if (this.studentList.isEmpty()) {
            if (file.exists()) {
                if (file.delete()) {
                    System.out.println("\n[ Info ] Student list is empty. Existing file deleted.\n");
                } else {
                    System.out.println("\n[ Warning ] Student list is empty, but failed to delete existing file.\n");
                }
            } else {
                System.out.println("\n[ Info ] Student list is empty. No file to delete.\n");
            }
            return;
        }

        try (FileWriter writer = new FileWriter(file, false)) {
            writer.write("id,first_name,middle_name,last_name,sex,pwd,institute,program,year,section");

            this.studentList.forEach(student -> {
                try {
                    writer.write(String.format("\n%s,\"%s\",\"%s\",\"%s\",%s,%s,\"%s\",\"%s\",%d,\"%s\"",
                            student.getId(),
                            student.getFirstName(),
                            student.getMiddleName(),
                            student.getLastName(),
                            student.getSex(),
                            student.getPwd(),
                            student.getInstitute(),
                            student.getProgram(),
                            student.getYear(),
                            student.getSection()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            System.out.println("\n[ Info ] Student list saved successfully.\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String[] parseCsvLine(String line) {
        ArrayList<String> fields = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean inQuotes = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {
                if (inQuotes && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    sb.append('"');
                    i++;
                } else {
                    inQuotes = !inQuotes;
                }
            } else if (c == ',' && !inQuotes) {
                fields.add(sb.toString());
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }
        fields.add(sb.toString());
        return fields.toArray(new String[0]);
    }
}
