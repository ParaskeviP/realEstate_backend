package gr.hua.dit.ds.grp41.rentalmanagement.rest;

import gr.hua.dit.ds.grp41.rentalmanagement.entities.Property;
import gr.hua.dit.ds.grp41.rentalmanagement.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyControllerRest {
    @Autowired
    private PropertyService propertyService;

    @GetMapping("/allProperties")
    public List<Property> getAllProperties() {
        return propertyService.getAllProperties();
    }

    @GetMapping("/search")
    public List<Property> searchProperties(
            @RequestParam(required = false) Integer roomNum,
            @RequestParam(required = false) Integer bedNum,
            @RequestParam(required = false) Integer bathNum,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) Long price,
            @RequestParam(required = false) Boolean petsAllowed,
            @RequestParam(required = false) Boolean hasGarden,
            @RequestParam(required = false) Boolean hasBalcony,
            @RequestParam(required = false) Boolean hasHeating,
            @RequestParam(required = false) Boolean isFurnished,
            @RequestParam(required = false) Integer renovationYear
    ) {
        return propertyService.searchProperties(roomNum, bedNum, bathNum, city, size, price, petsAllowed,
                hasGarden, hasBalcony, hasHeating, isFurnished, renovationYear);
    }

    @GetMapping("/getById/{propertyid}")
    public Property getById(@PathVariable Integer propertyid) {
        return propertyService.getById(propertyid);
    }

    @GetMapping("/getByRoomNumber/{roomNum}")
    public List<Property> getByRoomNumber(@PathVariable Integer roomNum) {
        return propertyService.getByRoomNumber(roomNum);
    }

    @GetMapping("/getByBedNumber/{bedNum}")
    public List<Property> getByBedNumber(@PathVariable Integer bedNum) {
        return propertyService.getByBedNumber(bedNum);
    }

    @GetMapping("/getByBathNumber/{bathNum}")
    public List<Property> getByBathNumber(@PathVariable Integer bathNum) {
        return propertyService.getByBathNumber(bathNum);
    }

    @GetMapping("/getByCity/{city}")
    public List<Property> getByCity(@PathVariable String city) {
        return propertyService.getByCity(city);
    }

    @GetMapping("/getByStreet/{street}")
    public List<Property> getByStreet(@PathVariable String street) {return propertyService.getByStreet(street);}

    @GetMapping("/getBySize/{size}")
    public List<Property> getBySize(@PathVariable Integer size) {
        return propertyService.getBySize(size);
    }

    @GetMapping("/getByPrice/{price}")
    public List<Property> getByPrice(@PathVariable Long price) {
        return propertyService.getByPrice(price);
    }

    @GetMapping("/getByPetsAllowed/{petsAllowed}")
    public List<Property> getByPetsAllowed(@PathVariable Boolean petsAllowed) {return propertyService.getByPetsAllowed(petsAllowed);}

    @GetMapping("/getByHasGarden/{hasGarden}")
    public List<Property> getByHasGarden(@PathVariable Boolean hasGarden) {return propertyService.getByHasGarden(hasGarden);}

    @GetMapping("/getByHasBalcony/{hasBalcony}")
    public List<Property> getByHasBalcony(@PathVariable Boolean hasBalcony) {return propertyService.getByHasBalcony(hasBalcony);}

    @GetMapping("/getByHasHeating/{hasHeating}")
    public List<Property> getByHasHeating(@PathVariable Boolean hasHeating) {return propertyService.getByHasHeating(hasHeating);}
    @GetMapping("/getByHasAC/{hasAC}")
    public List<Property> getByHasAC(@PathVariable Boolean hasAC) {return propertyService.getByHasAC(hasAC);}

    @GetMapping("/getByIsFurnished/{isFurnished}")
    public List<Property> getByIsFurnished(@PathVariable Boolean isFurnished) {return propertyService.getByIsFurnished(isFurnished);}

    @GetMapping("/getByRenovYear/{renovationYear}")
    public List<Property> getByRenovationYear(@PathVariable Integer renovationYear) {return propertyService.getByRenovationYear(renovationYear);}

    @GetMapping("/getByBuiltYear/{builtYear}")
    public List<Property> getByBuiltYear(@PathVariable Integer builtYear) {return propertyService.getByBuiltYear(builtYear);}

}