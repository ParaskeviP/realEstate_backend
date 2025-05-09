package gr.hua.dit.ds.grp41.rentalmanagement.payload.request;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SignupRequest {
    @NotBlank(message="Username is mandatory")
    @Size(min=3, max=20)
    private String username;

    @NotBlank(message="Email is mandatory")
    @Email(message="Email should be valid")
    private String email;

    private Set<String> role;

    @NotBlank(message="Password is mandatory")
    @Size(min=2)
    private String password;


    @NotBlank(message = "Name is Mandatory")
    private String fname;

    @NotBlank(message = "Last name is Mandatory")
    private String lname;


    private String phoneNumber;

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username=username;
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
        this.password=password;
    }

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role=role;
    }
}