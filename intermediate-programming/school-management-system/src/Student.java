public class Student {
    private String id = "";
    private String firstName = "";
    private String middleName = "";
    private String lastName = "";
    private String sex = "";
    private Boolean pwd = false;
    private String institute = "";
    private String program = "";
    private int year = 0;
    private String section = "";

    public Student() {
    }

    public Student(String id, String firstName, String middleName, String lastName,
            String sex, Boolean pwd, String institute, String program, int year, String section) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.sex = sex;
        this.pwd = pwd;
        this.institute = institute;
        this.program = program;
        this.year = year;
        this.section = section;
    }

    // Getters
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

    public Boolean getPwd() {
        return pwd;
    }

    public String getInstitute() {
        return institute;
    }

    public String getProgram() {
        return program;
    }

    public int getYear() {
        return year;
    }

    public String getSection() {
        return section;
    }

    // Setters
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

    public void setPwd(Boolean pwd) {
        this.pwd = pwd;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setSection(String section) {
        this.section = section;
    }

    // Methods
    public String getFullName() {
        return getFullName(Integer.MAX_VALUE);
    }

    public String getFullName(int max) {
        String fullName = (!lastName.isEmpty() ? lastName : "")
                + (!firstName.isEmpty() ? ", " + firstName : "")
                + (!middleName.isEmpty() ? " " + middleName : "");

        return (((fullName.length() <= max)) ? fullName : fullName.substring(0, max - 3) + "...");
    }
}