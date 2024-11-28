package com.sbsec.Modeles;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;


@Table(name = "users")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    long id;

    @Column(nullable = false, unique = true)
    String userName;

    @Setter
    @Column(unique = true, nullable = false)
    String email;

    @Column(nullable = false)
    String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns =  @JoinColumn(name = "role_id"))
    Set<Role> roles=new HashSet<>();


}