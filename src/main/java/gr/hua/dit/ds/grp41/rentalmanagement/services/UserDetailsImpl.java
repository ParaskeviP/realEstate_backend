package gr.hua.dit.ds.grp41.rentalmanagement.services;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import gr.hua.dit.ds.grp41.rentalmanagement.entities.User;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;
    private int id;
    private String username;
    private String email;
    private Collection<? extends GrantedAuthority> auths;

    @JsonIgnore
    private String password;

    public UserDetailsImpl(int id, String username, String email, String password,
                           Collection<? extends GrantedAuthority> auths) {
        this.id=id;
        this.username=username;
        this.email=email;
        this.password=password;
        this.auths=auths;
    }

    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return auths;
    }
    
    public int getId() {
        return id;
    }
    
    public String getEmail() {
        return email;
    }
    
    @Override
    public String getPassword() {
        return password;
    }
    
    @Override
    public String getUsername() {
        return username;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return true;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if(o == null || getClass() != o.getClass()) {
            return false;
        }

        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities=user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    
        return 
            new UserDetailsImpl (
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authorities
            );
    }
}