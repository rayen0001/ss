package com.sbsec.Modeles;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Table(name = "roles")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Enumerated(EnumType.STRING)
    ERole name;
}
