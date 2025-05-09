package gr.hua.dit.ds.grp41.rentalmanagement.rest;

import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import gr.hua.dit.ds.grp41.rentalmanagement.entities.Owner;
import gr.hua.dit.ds.grp41.rentalmanagement.entities.Tenant;
import gr.hua.dit.ds.grp41.rentalmanagement.services.OwnerService;
import gr.hua.dit.ds.grp41.rentalmanagement.services.TenantService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.hua.dit.ds.grp41.rentalmanagement.configs.JwtUtils;
import gr.hua.dit.ds.grp41.rentalmanagement.entities.Role;
import gr.hua.dit.ds.grp41.rentalmanagement.entities.User;
import gr.hua.dit.ds.grp41.rentalmanagement.payload.request.LoginRequest;
import gr.hua.dit.ds.grp41.rentalmanagement.payload.request.SignupRequest;
import gr.hua.dit.ds.grp41.rentalmanagement.payload.response.JwtResponse;
import gr.hua.dit.ds.grp41.rentalmanagement.payload.response.MsgResponse;
import gr.hua.dit.ds.grp41.rentalmanagement.repositories.RoleRepo;
import gr.hua.dit.ds.grp41.rentalmanagement.repositories.UserRepo;
import gr.hua.dit.ds.grp41.rentalmanagement.services.UserDetailsImpl;


@RestController
@RequestMapping("api/auth")
public class AuthController {
    AuthenticationManager authMan;
    UserRepo userRepo;
    RoleRepo roleRepo;
    BCryptPasswordEncoder passEncoder;
    JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authMan, UserRepo userRepo, RoleRepo roleRepo, BCryptPasswordEncoder passEncoder, JwtUtils jwtUtils) {
        this.authMan=authMan;
        this.userRepo=userRepo;
        this.roleRepo=roleRepo;
        this.passEncoder=passEncoder;
        this.jwtUtils=jwtUtils;
    }


    @Autowired
    private TenantService tenantService;

    @Autowired
    private OwnerService ownerService;

    @PostConstruct
    public void setup() {


        Role role_user=new Role("ROLE_TENANT");
        Role role_admin=new Role("ROLE_ADMIN");
        Role role_mod=new Role("ROLE_OWNER");

        roleRepo.updateOrInsert(role_user);
        roleRepo.updateOrInsert(role_admin);
        roleRepo.updateOrInsert(role_mod);


        if(!userRepo.existsByUsername("admin1")){
            Set<Role> roles=new HashSet<>();
            Role adminRole = roleRepo.findByName("ROLE_ADMIN")
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);
            User user = new User("admin1","admin@gmail.com", passEncoder.encode("123456"));
            user.setIsApproved(true);
            user.setRoles(roles);
            userRepo.save(user);
        }


    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        System.out.println("authentication");

        Authentication authentication=authMan.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        System.out.println("authentication: " + authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("post authentication");
        String jwt=jwtUtils.generateToken(authentication);
        System.out.println("jwt: " + jwt);

        UserDetailsImpl userDetails=(UserDetailsImpl) authentication.getPrincipal();
        List<String> roles=userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepo.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MsgResponse("Error: Username is already taken!"));
        }

        if (userRepo.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MsgResponse("Error: Email is already in use!"));
        }

        User user=new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                passEncoder.encode(signUpRequest.getPassword()));

        Set<Role> roles=new HashSet<>();


        if (signUpRequest.getRole() == null) { //if the user role is not defined, it is a client
            Role userRole = roleRepo.findByName("ROLE_TENANT")
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else { //if the user role is defined, it is either a doctor or an admin
            for (String roleName : signUpRequest.getRole()) {
                switch (roleName) {
                    case "ROLE_ADMIN":
                        Role adminRole = roleRepo.findByName("ROLE_ADMIN")
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    case "ROLE_OWNER":
                        Role ownerRole = roleRepo.findByName("ROLE_OWNER")
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(ownerRole);
                        break;
                    case "ROLE_TENANT":
                        Role tenantRole = roleRepo.findByName("ROLE_TENANT")
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(tenantRole);
                        break;
                    default:
                        throw new RuntimeException("Error: Invalid role: " + roleName);
                }
            }
        }

        user.setRoles(roles);
        userRepo.save(user); //saves the user

        if (signUpRequest.getRole().contains("ROLE_TENANT")){
            Tenant tenant = new Tenant(signUpRequest.getFname(),signUpRequest.getLname(),signUpRequest.getEmail());
            tenant.setUser(user);
            tenantService.saveTenant(tenant);
        }else if (signUpRequest.getRole().contains("ROLE_OWNER")){
            Owner owner = new Owner(signUpRequest.getFname(), signUpRequest.getLname(), signUpRequest.getEmail(), signUpRequest.getPhoneNumber());
            owner.setUser(user);
            ownerService.saveOwner(owner);
        }

        return ResponseEntity.ok(new MsgResponse("User registered successfully!"));
    }
}