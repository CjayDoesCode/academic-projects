public class Student {
    private String id = "";
    private String firstName = "";
    private String middleName = "";
    private String lastName = "";
    private String sex = "";
    private String pwd = "";
    private String program = "";
    private String year = "";
    private String section = "";

    public Student() {
    }

    public Student(
            String id,
            String firstName,
            String middleName,
            String lastName,
            String sex,
            String pwd,
            String program,
            String year,
            String section) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.sex = sex;
        this.pwd = pwd;
        this.program = program;
        this.year = year;
        this.section = section;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSex() {
        return sex;
    }

    public String getPwd() {
        return pwd;
    }

    public String getProgram() {
        return program;
    }

    public String getYear() {
        return year;
    }

    public String getSection() {
        return section;
    }

    public String getFullName() {
        return getFullName(Integer.MAX_VALUE);
    }

    public String getFullName(int max) {
        final String fullName = String.format("%s, %s %s", lastName, firstName, middleName).trim();
        return (fullName.length() <= max ? fullName : String.format("%s...", fullName.substring(0, max - 3)));
    }
}