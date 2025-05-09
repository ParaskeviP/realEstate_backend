package gr.hua.dit.ds.grp41.rentalmanagement.repositories;

import gr.hua.dit.ds.grp41.rentalmanagement.entities.Owner;
import gr.hua.dit.ds.grp41.rentalmanagement.entities.Property;
import gr.hua.dit.ds.grp41.rentalmanagement.entities.Request;
import gr.hua.dit.ds.grp41.rentalmanagement.entities.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepo extends JpaRepository<Request, Integer> {
    List<Request> getRequestsByOwner(Owner owner);
    List<Request> getRequestsByTenant(Tenant tenant);
}