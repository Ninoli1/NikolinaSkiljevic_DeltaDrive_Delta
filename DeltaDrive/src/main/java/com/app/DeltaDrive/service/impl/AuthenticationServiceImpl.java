package com.app.DeltaDrive.service.impl;

import com.app.DeltaDrive.dto.LoginDTO;
import com.app.DeltaDrive.dto.UserDTO;
import com.app.DeltaDrive.mapper.UserDTOMapper;
import com.app.DeltaDrive.model.User;
import com.app.DeltaDrive.repository.UserRepository;
import com.app.DeltaDrive.service.AuthenticationService;
import com.app.DeltaDrive.service.JwtService;
import com.app.DeltaDrive.service.UserService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final UserDTOMapper userDTOMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public String register(UserDTO userDTO){
            if (userService.findByEmail(userDTO.email()).isPresent()) {
                throw new IllegalArgumentException("Email already exists");
            }

            User user = userDTOMapper.mapUserDTOToUser(userDTO);
            user.setPassword(passwordEncoder.encode(userDTO.password()));

            User savedUser = userService.save(user);
            var jwt = jwtService.generateToken(savedUser);
            return jwt;
    }

    public String login(LoginDTO loginDTO){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.email(),
                        loginDTO.password()
                )
        );
        var foundUser= userService.findByEmail(loginDTO.email())
                .orElseThrow(() -> new NoSuchElementException("User with email " + loginDTO.email() + " not found"));;
        var jwt= jwtService.generateToken(foundUser);
        return jwt;

    }

    public String findLoggedInEmail(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        }

        return null;
    }
}
