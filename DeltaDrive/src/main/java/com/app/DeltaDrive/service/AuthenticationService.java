package com.app.DeltaDrive.service;

import com.app.DeltaDrive.dto.LoginDTO;
import com.app.DeltaDrive.dto.UserDTO;

public interface AuthenticationService {

    public String register(UserDTO userDTO);
    public  String login(LoginDTO userDTO);

    public String findLoggedInEmail();
}
