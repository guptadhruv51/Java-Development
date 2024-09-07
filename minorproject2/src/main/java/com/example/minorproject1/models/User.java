package com.example.minorproject1.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails,Serializable{
    @Id
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Authority authority;
    @OneToOne(mappedBy = "user") //back reference (bidirectional relationship)
    @JsonIgnoreProperties("user")
    private Student student;

    @OneToOne(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private Admin admin;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList<>();
        /**
         * Enum-> String->Simple Granted Authority
         */
        authorities.add(new SimpleGrantedAuthority(this.authority.name()));
        return authorities;


    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
/**
 * Sparse Tables:
 * students: thousands
 * Admins: tens
 *
 * User table: username, password, authority, student_id, admin_id
 *
 *
 *
 *
 * student table: id, name, ........ username
 *
 *
 * admin table: id, name, username
 *
 */

/**
 * Association
 * User <-> Student (1:1)
 * User <-> Admin (1:1)
 *
 *Where can we store the foreign key?
 * Student and admin table (username as the foreign key)
 *
 */
