package com.example.Staff.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
public class Staff {

    @Id
    @NotBlank(message = "Code must not be empty")
    private String code;

    @NotBlank(message = "Employee name must not be empty")
    private String empName;

    @NotBlank(message = "Employee address must not be empty")
    private String empAdd;

    @NotBlank(message = "NIC must not be empty")
    private String nic;

    @NotNull(message = "Salary must not be null")
    @Min(value = 0, message = "Salary must be at least 0")
    private int salary;

    @NotNull(message = "Age must not be null")
    @Min(value = 18, message = "Age must be at least 18")
    private int age;

    @NotBlank(message = "Occupation must not be empty")
    private String occupation;

    @NotBlank(message = "Email must not be empty")
    @Email(message = "Email should be valid")
    private String email;

    // Getters and setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpAdd() {
        return empAdd;
    }

    public void setEmpAdd(String empAdd) {
        this.empAdd = empAdd;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Staff(String code, String empName, String empAdd, String nic, int salary, int age, String occupation, String email) {
        this.code = code;
        this.empName = empName;
        this.empAdd = empAdd;
        this.nic = nic;
        this.salary = salary;
        this.age = age;
        this.occupation = occupation;
        this.email = email;
    }

    public Staff() {
        // Default constructor
    }
}