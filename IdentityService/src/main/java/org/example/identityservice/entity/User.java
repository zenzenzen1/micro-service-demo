package org.example.identityservice.entity;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
// import org.example.identityservice.enums.Role;

@Entity
@Data
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "users")
@Builder
@AllArgsConstructor
// @NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String username;
    private String password;
    // private String firstName;
    // private String lastName;
    // private LocalDate dob;

    @ManyToMany
    private Set<Role> roles;
}
