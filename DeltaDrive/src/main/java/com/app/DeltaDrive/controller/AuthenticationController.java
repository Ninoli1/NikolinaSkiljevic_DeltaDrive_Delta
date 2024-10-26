package com.app.DeltaDrive.controller;


import com.app.DeltaDrive.dto.LoginDTO;
import com.app.DeltaDrive.dto.UserDTO;
import com.app.DeltaDrive.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {


    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDto){

        return  ResponseEntity.ok( authenticationService.register(userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO){

        return  ResponseEntity.ok(authenticationService.login(loginDTO));
    }

}
