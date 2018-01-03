package models;

public class Account {
    private String type;
    private String department;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String status;

    public Account(String type, String department, String firstName, String lastName, String username, String password, String status) {
        this.type = type;
        this.department = department;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public String getDepartment() {
        return department;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }
}
