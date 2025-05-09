package gr.hua.dit.ds.grp41.rentalmanagement.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Schema(description = "Model for Request")
public class Request {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer Id;

    @Column
    private Boolean isViewingApproved;

    @Column
    private Boolean isRentalRequest;

    public void getProperty(Property property) {
        this.property = property;
    }

    public void getTenant(Tenant tenant ) {this.tenant = tenant;}

    public void getOwner(Owner owner) {this.owner = owner;}

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Owner owner;

    @ManyToOne
    @JoinColumn(name = "property_id", referencedColumnName = "id")
    private Property property;

    @ManyToOne
    @JoinColumn(name = "tenant_id", referencedColumnName = "id")
    private Tenant tenant;

    public Request() {
    }

    public Request(Integer id) {
        Id = id;
        this.isViewingApproved = false;
        this.isRentalRequest = false;
    }

    public Integer getId() {return Id;}

    public void setId(Integer id) {Id = id;}

    public Boolean getIsViewingApproved() {return isViewingApproved;}

    public void setIsViewingApproved(Boolean isApproved) {this.isViewingApproved = isApproved;}

    public Boolean getApproved() {return isViewingApproved;}

    public void setApproved(Boolean approved) {
        isViewingApproved = approved;}

    public Boolean getIsRentalRequest() {return isRentalRequest;}

    public void setIsRentalRequest(Boolean rentalRequest) {isRentalRequest = rentalRequest;}

    public Owner getOwner() {return owner;}

    public void setOwner(Owner owner) {this.owner = owner;}

    public Property getProperty() {return property;}

    public void setProperty(Property property) {this.property = property;}

    public Tenant getTenant() {return tenant;}

    public void setTenant(Tenant tenant) {this.tenant = tenant;}

    @Override
    public String toString() {
        return "RentalRequest{" +
                "Id=" + Id +
                ", isApproved=" + isViewingApproved +
                ", isRentalRequest=" + isRentalRequest +
                '}';
    }
}
