package gr.hua.dit.ds.grp41.rentalmanagement.payload.response;

import java.util.List;

public class JwtResponse {
    private String token;
    private String type="Bearer";
    private int id;
    private String username;
    private String email;
    private List<String> roles;

    public JwtResponse(String token, int id, String username, String email, List<String> roles) {
        this.token=token;
        this.id=id;
        this.username=username;
        this.email=email;
        this.roles=roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token=token;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String type) {
        this.type=type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id=id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email=email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username=username;
    }

    public List<String> getRoles() {
        return roles;
    } 
}