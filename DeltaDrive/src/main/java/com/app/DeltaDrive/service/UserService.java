package com.app.DeltaDrive.service;

import com.app.DeltaDrive.dto.UserDTO;
import com.app.DeltaDrive.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface UserService {

   public Optional<User> findByEmail(String email);
   public  User save(User user);

   boolean existsByEmail(String email);
}
