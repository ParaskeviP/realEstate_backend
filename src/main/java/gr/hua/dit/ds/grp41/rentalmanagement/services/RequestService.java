package gr.hua.dit.ds.grp41.rentalmanagement.services;


import gr.hua.dit.ds.grp41.rentalmanagement.entities.Owner;
import gr.hua.dit.ds.grp41.rentalmanagement.entities.Property;
import gr.hua.dit.ds.grp41.rentalmanagement.entities.Request;
import gr.hua.dit.ds.grp41.rentalmanagement.entities.Tenant;
import gr.hua.dit.ds.grp41.rentalmanagement.repositories.RequestRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {

    @Autowired
    private RequestRepo requestRepository;

    @Transactional
    public void saveRequest(Request request) {
        requestRepository.save(request);
    }

    @Transactional
    public Request getById(Integer id){
        return requestRepository.findById(id).get();
    }

    @Transactional
    public void deleteRequest(Integer id){
        requestRepository.deleteById(id);
    }

    @Transactional
    public List<Request> getRequestsByOwner(Owner owner){
        return requestRepository.getRequestsByOwner(owner);
    }

    @Transactional
    public List<Request> getRequestsByTenant(Tenant tenant){
        return requestRepository.getRequestsByTenant(tenant);
    }
}
