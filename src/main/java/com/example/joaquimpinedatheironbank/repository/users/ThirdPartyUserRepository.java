package com.example.joaquimpinedatheironbank.repository.users;

import com.example.joaquimpinedatheironbank.entities.users.ThirdPartyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThirdPartyUserRepository extends JpaRepository<ThirdPartyUser, String> {

    ThirdPartyUser save(ThirdPartyUser thirdPartyUser);



    ThirdPartyUser findByEmail(String email);



}

