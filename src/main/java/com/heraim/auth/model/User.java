package com.heraim.auth.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table( name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @NotNull( message = "Username cannot be null")
    public String username;
    @NotNull( message = "Email cannot be null")
    @Email( message = "Email is not valid")
    @Column(unique = true)
    public String email;
    @NotNull( message = "Password cannot be null")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    public String password;
}
