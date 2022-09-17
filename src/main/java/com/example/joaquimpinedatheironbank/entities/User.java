package com.example.joaquimpinedatheironbank.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    private String UUID;
    private String name;
    private String email;
    private String token;

    @OneToMany
    @JoinColumn(name = "id")
    public List<Account> accounts;
}
