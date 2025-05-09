package gr.hua.dit.ds.grp41.rentalmanagement.rest;

import gr.hua.dit.ds.grp41.rentalmanagement.entities.*;
import gr.hua.dit.ds.grp41.rentalmanagement.repositories.UserRepo;
import gr.hua.dit.ds.grp41.rentalmanagement.services.OwnerService;
import gr.hua.dit.ds.grp41.rentalmanagement.services.PropertyService;
import gr.hua.dit.ds.grp41.rentalmanagement.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/owner")
public class OwnerControllerRest {

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/newProperty")
    public ResponseEntity<String> PropertyInsertion(@RequestBody Property property){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userName = userDetails.getUsername();
        Optional<User> user = userRepo.findByUsername(userName);
        Owner owner = ownerService.getOwnerByUser(user.get());
        property.setApproved(false);
        ownerService.propertyInsertion(property,owner);
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/allOwners")
    public List<Owner> getAllOwners(){
        return ownerService.getAllOwners();
    }

    @GetMapping("/getById/{ownerid}")
    public Owner getById(@PathVariable Integer ownerid) {
//        if (ownerid == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body("Owner with ID: " + ownerid + "does not exist.");
//        }
        return ownerService.getById(ownerid);
    }

    @DeleteMapping("/delOwner/{ownerid}")
    public ResponseEntity<String> deleteOwner(@PathVariable Integer ownerid) {
        //ΠΡΕΠΕΙ ΠPΩΤΑ ΝΑ ΣΒΗΣΕΙ ΤΑ ΑΚΙΝΗΤΑ ΠΟΥ ΕΧΕΙ
        Owner owner = ownerService.getById(ownerid);
//        if (ownerid == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body("Owner with ID: " + ownerid + "does not exist.");
//        }
        ownerService.deleteOwner(ownerid);
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/saveOwner")
    public ResponseEntity<String> saveOwner(@RequestBody Owner owner){
        ownerService.saveOwner(owner);
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/approveViewingRequest/{requestid}")
    public ResponseEntity<String> approveViewingRequest(@PathVariable Integer requestid){
        Request request = requestService.getById(requestid);
        request.setIsViewingApproved(true);
        requestService.saveRequest(request);
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/approveRentalRequest/{requestid}")
    public ResponseEntity<String> approveRentalRequest(@PathVariable Integer requestid){
        Request request = requestService.getById(requestid);
        Tenant tenant = request.getTenant();
        Property property = request.getProperty();
        property.setTenant(tenant);

        propertyService.saveProperty(property);

        requestService.deleteRequest(requestid);

        return ResponseEntity.ok("OK");
    }

    @GetMapping("/getMyProfile")
    public Owner getProfileDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userName = userDetails.getUsername();
        Optional<User> user = userRepo.findByUsername(userName);

        Owner owner = ownerService.getOwnerByUser(user.get());

        return owner;
    }

    @GetMapping("/getRequests")
    public List<Request> getRequests(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userName = userDetails.getUsername();
        Optional<User> user = userRepo.findByUsername(userName);

        Owner owner = ownerService.getOwnerByUser(user.get());

        List<Request> requests = requestService.getRequestsByOwner(owner);
        return requests;

    }

    @PostMapping("/deleteRequest/{requestid}")
    public ResponseEntity<String> deleteRequest(@PathVariable Integer requestid){
        Request request = requestService.getById(requestid);
        requestService.deleteRequest(request.getId());

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
}