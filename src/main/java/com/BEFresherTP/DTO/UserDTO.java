package com.BEFresherTP.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDTO {
    private int userID;
    @NotBlank(message = "First name can't be blank")
    @Size(min = 3, max = 255, message = "First name should be between 3 and 255 characters")
    private String firstName;
    @NotBlank(message = "Last name can't be blank")
    @Size(min = 3, max = 255, message = "Last name should be between 3 and 255 characters")
    private String lastName;
    @NotBlank(message = "Email can't be blank")
    @Size(min = 3, max = 255, message = "Email should be between 3 and 255 characters")
    @Email(message = "Email is invalid")
    private String email;
    private String gender;
    private int age;
    private String password;

    public UserDTO() {
        super();
    }

    public UserDTO(int userID, String firstName, String lastName, String email, String gender, int age, String password) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
