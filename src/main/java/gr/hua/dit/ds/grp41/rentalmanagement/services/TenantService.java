package gr.hua.dit.ds.grp41.rentalmanagement.services;

import gr.hua.dit.ds.grp41.rentalmanagement.entities.Request;
import gr.hua.dit.ds.grp41.rentalmanagement.entities.Tenant;
import gr.hua.dit.ds.grp41.rentalmanagement.entities.User;
import gr.hua.dit.ds.grp41.rentalmanagement.repositories.RequestRepo;
import gr.hua.dit.ds.grp41.rentalmanagement.repositories.TenantRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TenantService {

    @Autowired
    private RequestRepo rentalRequestRepo;

    @Autowired
    private TenantRepo tenantRepo;

    @Transactional
    public Tenant getById(Integer id){
        return tenantRepo.findById(id).get();
    }

    @Transactional
    public void saveRequest(Request rentalRequest){
        rentalRequestRepo.save(rentalRequest);
    }

    @Transactional
    public void saveTenant(Tenant tenant){
        tenantRepo.save(tenant);
    }

    @Transactional
    public Tenant getTenantByUser(User user){
        return tenantRepo.getTenantByUser(user);
    }

    @Transactional
    public void deleteTenant(Integer id){
        tenantRepo.deleteById(id);
    }
}
