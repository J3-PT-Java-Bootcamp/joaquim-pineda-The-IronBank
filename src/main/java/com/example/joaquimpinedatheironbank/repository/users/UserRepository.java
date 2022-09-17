package com.example.joaquimpinedatheironbank.repository.users;

import com.example.joaquimpinedatheironbank.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, String> {

    User save(User user);



    User findByEmail(String email);


    @Query(value = "SELECT * FROM user  WHERE email = :email", nativeQuery = true)
    User findIfEmailExists(@Param ("email") String email);
}

