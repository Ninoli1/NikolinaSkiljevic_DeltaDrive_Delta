package com.app.DeltaDrive.service.impl;

import com.app.DeltaDrive.dto.LoginDTO;
import com.app.DeltaDrive.dto.UserDTO;
import com.app.DeltaDrive.mapper.UserDTOMapper;
import com.app.DeltaDrive.model.User;
import com.app.DeltaDrive.repository.UserRepository;
import com.app.DeltaDrive.service.AuthenticationService;
import com.app.DeltaDrive.service.JwtService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public String register(UserDTO userDTO){
        User user = userDTOMapper.mapUserDTOToUser(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.password()));

        User savedUser = userRepository.save(user);
        var jwt= jwtService.generateToken(savedUser);
        return jwt;
    }

    public String login(LoginDTO loginDTO){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.email(),
                        loginDTO.password()
                )
        );
        var foundUser= userRepository.findByEmail(loginDTO.email())
                .orElseThrow();
        var jwt= jwtService.generateToken(foundUser);
        return jwt;

    }
}
