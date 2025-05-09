package gr.hua.dit.ds.grp41.rentalmanagement.payload.request;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String pass;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username=username;
    }

    public String getPassword() {
        return pass;
    }

    public void setPassword(String pass) {
        this.pass=pass;
    }
}