package gr.hua.dit.ds.grp41.rentalmanagement.repositories;

import gr.hua.dit.ds.grp41.rentalmanagement.entities.Owner;
import gr.hua.dit.ds.grp41.rentalmanagement.entities.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepo extends JpaRepository<Owner, Integer> {
    Optional<User> findByFname(String fname);
    Boolean existsByPhonenumber(String phonenumber);
    Boolean existsById(int id);
    Boolean existsByEmail(String email);

    Owner getOwnerByUser(User user);
}