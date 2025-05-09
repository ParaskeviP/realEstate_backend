package gr.hua.dit.ds.grp41.rentalmanagement.repositories;

import gr.hua.dit.ds.grp41.rentalmanagement.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String roleName);

    default Role updateOrInsert(Role role) {
        Role existing_role = findByName(role.getName()).orElse(null);
        if (existing_role != null) {
            return existing_role;
        } else {
            return save(role);
        }
    }
}