package com.app.DeltaDrive.service;

import com.app.DeltaDrive.dto.UserDTO;
import org.springframework.stereotype.Service;


public interface UserService {

   public  UserDTO findByEmail(String email);
   public  UserDTO save(UserDTO userDTO);
}
