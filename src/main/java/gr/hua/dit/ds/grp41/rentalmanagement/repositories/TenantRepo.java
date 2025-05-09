package gr.hua.dit.ds.grp41.rentalmanagement.repositories;

import gr.hua.dit.ds.grp41.rentalmanagement.entities.Tenant;
import gr.hua.dit.ds.grp41.rentalmanagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepo extends JpaRepository<Tenant, Integer> {
    Tenant getTenantByUser(User user);
}