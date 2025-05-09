package gr.hua.dit.ds.grp41.rentalmanagement.rest;

import gr.hua.dit.ds.grp41.rentalmanagement.entities.*;
import gr.hua.dit.ds.grp41.rentalmanagement.repositories.UserRepo;
import gr.hua.dit.ds.grp41.rentalmanagement.services.OwnerService;
import gr.hua.dit.ds.grp41.rentalmanagement.services.PropertyService;
import gr.hua.dit.ds.grp41.rentalmanagement.services.RequestService;
import gr.hua.dit.ds.grp41.rentalmanagement.services.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tenant")
public class TenantControllerRest {
    @Autowired
    private OwnerService ownerService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private TenantService tenantService;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private RequestService rentalRequestService;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/showOwnedProperties")
    public List<Property> showOwnedProperties(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userName = userDetails.getUsername();
        Optional<User> user = userRepo.findByUsername(userName);

        Tenant tenant = tenantService.getTenantByUser(user.get());

        List<Property> properties = propertyService.getPropertiesByTenant(tenant);
        return properties;
    }

    @GetMapping("/showProperties")
    public List<Property> showProperties(){
        List<Property> properties = propertyService.getAllProperties();
        Iterator<Property> iterator = properties.iterator();
        while (iterator.hasNext()) {
            Property property = iterator.next();
            if (!property.getIsApproved() || property.getTenant() != null) {
                iterator.remove();
            }
        }
        System.out.println(properties);
        return properties;
    }

    @GetMapping("/getMyRequests")
    public List<Request> getRequests(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userName = userDetails.getUsername();
        Optional<User> user = userRepo.findByUsername(userName);

        Tenant tenant = tenantService.getTenantByUser(user.get());

        List<Request> requests = requestService.getRequestsByTenant(tenant);
        System.out.println(requests);
        return requests;
    }

    @GetMapping("/getMyProfile")
    public Tenant getProfileDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userName = userDetails.getUsername();
        Optional<User> user = userRepo.findByUsername(userName);

        Tenant tenant = tenantService.getTenantByUser(user.get());

        return tenant;
    }

    @PostMapping("/makeRentalRequest/{property_id}")
    public ResponseEntity<String> makeRentalRequest(@PathVariable Integer property_id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userName = userDetails.getUsername();
        Optional<User> user = userRepo.findByUsername(userName);

        Tenant tenant = tenantService.getTenantByUser(user.get());
        Property property = propertyService.getById(property_id);
        Owner owner = property.getOwner();
        Request rentalRequest = new Request();

        rentalRequest.setTenant(tenant);
        rentalRequest.setProperty(property);
        rentalRequest.setOwner(owner);
        rentalRequest.setIsViewingApproved(false);
        rentalRequest.setIsRentalRequest(true);

        tenantService.saveRequest(rentalRequest);
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/makeViewingRequest/{property_id}")
    public ResponseEntity<String> makeViewingRequest(@PathVariable Integer property_id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userName = userDetails.getUsername();
        Optional<User> user = userRepo.findByUsername(userName);

        Tenant tenant = tenantService.getTenantByUser(user.get());
        Property property = propertyService.getById(property_id);
        Owner owner = property.getOwner();
        Request rentalRequest = new Request();

        rentalRequest.setTenant(tenant);
        rentalRequest.setProperty(property);
        rentalRequest.setOwner(owner);
        //rentalRequest.setIsViewingApproved(false);
        rentalRequest.setIsRentalRequest(false);
        tenantService.saveRequest(rentalRequest);
        return ResponseEntity.ok("OK");
    }
}
