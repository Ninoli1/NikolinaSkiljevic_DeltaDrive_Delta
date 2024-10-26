package com.app.DeltaDrive.service.impl;

import com.app.DeltaDrive.dto.UserDTO;
import com.app.DeltaDrive.exception.UserNotFoundException;
import com.app.DeltaDrive.mapper.UserDTOMapper;
import com.app.DeltaDrive.model.User;
import com.app.DeltaDrive.repository.UserRepository;
import com.app.DeltaDrive.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;
    public UserDTO findByEmail(String email){
        return userRepository.findByEmail(email)
                .map(userDTOMapper::mapUserToUserDTO)
                .orElseThrow(() -> new UserNotFoundException("User with email " + email + " not found"));


    }
    public  UserDTO save(UserDTO userDTO){
        User user = userDTOMapper.mapUserDTOToUser(userDTO);
        User savedUser = userRepository.save(user);

        return userDTOMapper.mapUserToUserDTO(savedUser);
    }
}
