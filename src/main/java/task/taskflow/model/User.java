package task.taskflow.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity  // Mark this class as a JPA entity
public class User implements UserDetails {

    @Id  // Define the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Define the strategy for primary key generation
    private Long id;

    private String email;
    private String password;
    private String name;
    private String surname;
    private String role;

    // Getter for the id (important for JPA relationships)
    public Long getId() {
        return id;
    }

    // Standard getters and setters for the other fields
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getRole() {
        return role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // Adapt based on your security requirements
    }

    @Override
    public String getUsername() {
        return email;  // Assuming email is used as the username
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
}
