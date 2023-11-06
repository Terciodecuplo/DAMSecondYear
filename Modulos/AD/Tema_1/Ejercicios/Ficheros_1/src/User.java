public class User {
    private String name;
    private String surname;
    private String phoneNumber;
    private String idNumber;
    private int age;

    public User(String name, String surname, String phoneNumber, String idNumber, int age) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.idNumber = idNumber;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public int getAge() {
        return age;
    }
}
