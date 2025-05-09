package gr.hua.dit.ds.grp41.rentalmanagement.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table
@Schema(description = "Model for Tenant")
public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "firstName")
    private String fname;

    @Column(name = "lastName")
    private String lname;

    @Column(name = "email")
    private String email;

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getFname() {return fname;}

    public void setFname(String fname) {this.fname = fname;}

    public String getLname() {return lname;}

    public void setLname(String lname) {this.lname = lname;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public Tenant() {
    }

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tenant(String fname, String lname, String email) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Tenant{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
