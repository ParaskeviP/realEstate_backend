package gr.hua.dit.ds.grp41.rentalmanagement.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import gr.hua.dit.ds.grp41.rentalmanagement.entities.Role;
import gr.hua.dit.ds.grp41.rentalmanagement.entities.User;
import gr.hua.dit.ds.grp41.rentalmanagement.repositories.RoleRepo;
import gr.hua.dit.ds.grp41.rentalmanagement.repositories.UserRepo;
import jakarta.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepo userRepo;
    private RoleRepo roleRepo;
    private BCryptPasswordEncoder passEncoder;

    public UserDetailsServiceImpl(UserRepo userRepo, RoleRepo roleRepo, BCryptPasswordEncoder passEncoder) {
        this.userRepo=userRepo;
        this.roleRepo=roleRepo;
        this.passEncoder=passEncoder;
    }

    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> opt=userRepo.findByUsername(username);

        if(opt.isEmpty()) {
            throw new UsernameNotFoundException("User with username: " + username +" not found !");
        } else {
            User user=opt.get();

            return UserDetailsImpl.build(user);
        }
    }

    @Transactional
    public int saveUser(User user) {
        String passwd=user.getPassword();
        String encodedPassword=passEncoder.encode(passwd);
        user.setPassword(encodedPassword);

        Role role=roleRepo.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        user=userRepo.save(user);
        return user.getId();
    }

    @Transactional
    public int updateUser(User user) {
        user=userRepo.save(user);
        return user.getId();
    }

    @Transactional
    public Object getUsers() {
        return userRepo.findAll();
    }

    public Object getUser(Long userId) {
        return userRepo.findById(userId).get();
    }

    @Transactional
    public void updateOrInsertRole(Role role) {
        roleRepo.updateOrInsert(role);
    }
}