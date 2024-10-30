package com.app.DeltaDrive.model;

import com.app.DeltaDrive.model.enums.Role;
import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="users")
@NoArgsConstructor

public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "First Name should not be blank!")
    private String firstName;

    @NotBlank(message = "Last Name should not be blank!")
    private String lastName;

    @NotBlank(message = "Email should not be blank!")
    @Email
    @Column(unique = true)
    private String email;
    private String password;

    @Pattern(regexp = "^\\d{2}\\.\\d{2}\\.\\d{4}\\.$", message = "Please enter the date in the format dd.MM.yyyy.")
    @NotBlank(message = "Birthday should not be blank!")
    private String birthday;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String firstName, String lastName, String email, String password,String birthday,Role role){
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.password=password;
        this.birthday=birthday;
        this.role=role;

    }





    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
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
