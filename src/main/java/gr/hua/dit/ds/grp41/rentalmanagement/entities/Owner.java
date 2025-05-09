package gr.hua.dit.ds.grp41.rentalmanagement.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
@Schema(description = "Model for Owner")
public class Owner {

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

    @Column(name="phoneNumber")
    private String phonenumber;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name="owner_property",
            joinColumns = @JoinColumn(name="owner_id"),
            inverseJoinColumns = @JoinColumn(name="property_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"owner_id", "propert_id"})
    )
    private List<Property> properties;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="user_id")
    private User user;


    public Owner() {
    }

    public Owner(String fname, String lname, String email, String phonenumber) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phonenumber = phonenumber;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getFname() {return fname;}

    public void setFname(String fname) {this.fname = fname;}

    public String getLname() {return lname;}

    public void setLname(String lname) {this.lname = lname;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getPhonenumber() {return phonenumber;}

    public void setPhonenumber(String phonenumber) {this.phonenumber = phonenumber;}

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", phonenumber='"+ phonenumber +
                '}';
    }
}