package gr.hua.dit.ds.grp41.rentalmanagement.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;


@Entity
@Table
@Schema(description = "Model for Property")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String city;

    @Column
    private String street;

    @Column
    private int streetNum;

    @Column
    private long price;

    @Column
    private int size;

    @Column
    private int roomNum;

    @Column
    private int bathNum;

    @Column
    private int bedNum;

    @Column
    private boolean isFurnished;

    @Column
    private boolean hasParking;

    @Column
    private boolean hasBalcony;

    @Column
    private boolean hasGarden;

    @Column
    private boolean hasHeating;

    @Column
    private boolean hasAC;

    @Column
    private boolean petsAllowed;

    @Column
    private int builtYear;

    @Column
    private int renovationYear;

    @Column
    private Boolean isApproved;

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    private Owner owner;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tenant_id", referencedColumnName = "id")
    private Tenant tenant;

    public Property(int id, String city, String street, int streetNum, long price, int size, int roomNum, int bathNum, int bedNum, boolean isFurnished, boolean hasParking, boolean hasBalcony, boolean hasGarden, boolean hasHeating, boolean hasAC, boolean petsAllowed, int builtYear, int renovationYear) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.streetNum = streetNum;
        this.price = price;
        this.size = size;
        this.roomNum = roomNum;
        this.bathNum = bathNum;
        this.bedNum = bedNum;
        this.isFurnished = isFurnished;
        this.hasParking = hasParking;
        this.hasBalcony = hasBalcony;
        this.hasGarden = hasGarden;
        this.hasHeating = hasHeating;
        this.hasAC = hasAC;
        this.petsAllowed = petsAllowed;
        this.builtYear = builtYear;
        this.renovationYear = renovationYear;
        this.isApproved = false;
        this.tenant = null;
    }

    public Property() {
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getCity() {return city;}

    public void setCity(String city) {this.city = city;}

    public String getStreet() {return street;}

    public void setStreet(String street) {this.street = street;}

    public int getStreetNum() {return streetNum;}

    public void setStreetNum(int streetNum) {this.streetNum = streetNum;}

    public long getPrice() {return price;}

    public void setPrice(long price) {this.price = price;}

    public int getSize() {return size;}

    public void setSize(int size) {this.size = size;}

    public int getRoomNum() {return roomNum;}

    public void setRoomNum(int roomNum) {this.roomNum = roomNum;}

    public int getBathNum() {return bathNum;}

    public void setBathNum(int bathNum) {this.bathNum = bathNum;}

    public int getBedNum() {return bedNum;}

    public void setBedNum(int bedNum) {this.bedNum = bedNum;}

    public boolean isFurnished() {return isFurnished;}

    public void setFurnished(boolean furnished) {isFurnished = furnished;}

    public boolean isHasParking() {return hasParking;}

    public void setHasParking(boolean hasParking) {this.hasParking = hasParking;}

    public boolean isHasBalcony() {return hasBalcony;}

    public void setHasBalcony(boolean hasBalcony) {this.hasBalcony = hasBalcony;}

    public boolean isHasGarden() {return hasGarden;}

    public void setHasGarden(boolean hasGarden) {this.hasGarden = hasGarden;}

    public boolean isHasHeating() {return hasHeating;}

    public void setHasHeating(boolean hasHeating) {this.hasHeating = hasHeating;}

    public boolean isHasAC() {return hasAC;}

    public void setHasAC(boolean hasAC) {this.hasAC = hasAC;}

    public boolean isPetsAllowed() {return petsAllowed;}

    public void setPetsAllowed(boolean petsAllowed) {this.petsAllowed = petsAllowed;}

    public int getBuiltYear() {return builtYear;}

    public void setBuiltYear(int builtYear) {this.builtYear = builtYear;}

    public int getRenovationYear() {return renovationYear;}

    public void setRenovationYear(int renovationYear) {this.renovationYear = renovationYear;}

    public Owner getOwner() {return owner;}

    public Boolean getIsApproved() {return isApproved;}

    public void setIsApproved(Boolean isApproved) {this.isApproved = isApproved;}

    public Boolean getApproved() {return isApproved;}

    public void setApproved(Boolean approved) {isApproved = approved;}

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    @Override
    public String toString() {
        return "Property{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", streetNum=" + streetNum +
                ", price=" + price +
                ", size=" + size +
                ", roomNum=" + roomNum +
                ", bathNum=" + bathNum +
                ", bedNum=" + bedNum +
                ", isFurnished=" + isFurnished +
                ", hasParking=" + hasParking +
                ", hasBalcony=" + hasBalcony +
                ", hasGarden=" + hasGarden +
                ", hasHeating=" + hasHeating +
                ", hasAC=" + hasAC +
                ", petsAllowed=" + petsAllowed +
                ", builtYear=" + builtYear +
                ", renovationYear=" + renovationYear +
                ", isApproved=" + isApproved +
                '}';
    }
}

