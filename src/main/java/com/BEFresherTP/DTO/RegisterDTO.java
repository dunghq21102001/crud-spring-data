package com.BEFresherTP.DTO;

import com.BEFresherTP.validation.validationAnotation.StrongPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterDTO {
    @NotBlank(message = "First name can't be blank")
    @Size(min = 3, max = 255, message = "First name should be between 3 and 255 characters")
    private String firstName;
    @NotBlank(message = "Last name can't be blank")
    @Size(min = 3, max = 255, message = "Last name should be between 3 and 255 characters")
    private String lastName;
    @NotBlank(message = "Email can't be blank")
    @Email(message = "Email is invalid")
    private String email;
    @NotBlank(message = "Password can't be blank")
    @Size(min = 5, max =  255, message = "Password must be between 5 and 255 characters")
    @StrongPassword
    private String password;

    public RegisterDTO() {
    }

    public RegisterDTO(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
