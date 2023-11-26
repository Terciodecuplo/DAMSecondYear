package com.adfirstproject.models;

public class EmployeesInfoContainer {
    private int id;
    private String name;
    private String surname;
    private String email;

    public EmployeesInfoContainer(int id, String name, String surname, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public EmployeesInfoContainer(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }
}
