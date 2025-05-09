package gr.hua.dit.ds.grp41.rentalmanagement.services;

import gr.hua.dit.ds.grp41.rentalmanagement.entities.Owner;
import gr.hua.dit.ds.grp41.rentalmanagement.entities.Property;
import gr.hua.dit.ds.grp41.rentalmanagement.entities.User;
import gr.hua.dit.ds.grp41.rentalmanagement.repositories.OwnerRepo;
import gr.hua.dit.ds.grp41.rentalmanagement.repositories.PropertyRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {


    @Autowired
    private OwnerRepo ownerRepository;

    @Autowired
    private PropertyRepo propertyRepo;

    @Transactional
    public void saveOwner(Owner owner) {
        ownerRepository.save(owner);
    }

    @Transactional
    public void deleteOwner(Integer id){
        ownerRepository.deleteById(id);
    }

    @Transactional
    public List<Owner> getAllOwners(){
        return ownerRepository.findAll();
    }

    @Transactional
    public Owner getById(Integer id){
        return ownerRepository.findById(id).get();
    }

    @Transactional
    public void propertyInsertion(Property property, Owner owner){
        property.setOwner(owner);
        property.setIsApproved(false);
        propertyRepo.save(property);
    }

    @Transactional
    public Owner getOwnerByUser(User user){
        return ownerRepository.getOwnerByUser(user);
    }



}
