package gr.hua.dit.ds.grp41.rentalmanagement.rest;

import gr.hua.dit.ds.grp41.rentalmanagement.entities.*;
import gr.hua.dit.ds.grp41.rentalmanagement.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminControllerRest {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private UserService userService;
    @Autowired
    private OwnerService ownerService;
    @Autowired
    private TenantService tenantService;


    @GetMapping("/getUsersFalse")
    public List<User> getUsersFalse() {
        List<User> users = userService.getUsers();
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getIsApproved()) {
                iterator.remove();
            }
        }
        System.out.println(users);
        return users;
    }


    @GetMapping("/getNonApprovedProperties")
    public List<Property> getNonApprovedProperties() {
        List<Property> propertiesFalse = propertyService.getAllProperties();
        Iterator<Property> iterator = propertiesFalse.iterator();
        while (iterator.hasNext()) {
            Property property = iterator.next();
            if (property.getIsApproved()) {
                iterator.remove();
            }
        }

        return propertiesFalse;
    }

    @PostMapping("/approveProperty/{propertyid}")
    public ResponseEntity<String> approveProperty(@PathVariable Integer propertyid){
        Property property = propertyService.getById(propertyid);
        property.setIsApproved(true);
        propertyService.saveProperty(property);
        return ResponseEntity.ok("OK");
    }

    @DeleteMapping("/deleteProperty/{propertyid}")
    public ResponseEntity<String> deleteProperty(@PathVariable Integer propertyid) {
        Property property = propertyService.getById(propertyid);
        if (propertyid == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Property with ID: " + property + "does not exist.");
        }
        propertyService.deleteProperty(propertyid);
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/approveUser/{userid}")
    public ResponseEntity<String> approveUser(@PathVariable Long userid){
        User user = userService.getById(userid);
        user.setIsApproved(true);
        userService.saveUser(user);
        return ResponseEntity.ok("OK");
    }

    @DeleteMapping("/declineUser/{userid}")
    public ResponseEntity<String> declineUser(@PathVariable Long userid) {
        User user = userService.getById(userid);
        if (userid == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with ID: " + user + "does not exist.");
        }
        if(user.getRoles().contains("ROLE_OWNER")) {
            ownerService.deleteOwner(userid.intValue());
        } else if (user.getRoles().contains("ROLE_TENANT")) {
            tenantService.deleteTenant(userid.intValue());
        }
        userService.deleteUser(userid);
        return ResponseEntity.ok("OK");
    }
}