package com.app.DeltaDrive.service.impl;

import com.app.DeltaDrive.dto.UserDTO;
import com.app.DeltaDrive.mapper.UserDTOMapper;
import com.app.DeltaDrive.model.User;
import com.app.DeltaDrive.repository.UserRepository;
import com.app.DeltaDrive.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public  User save(User user){
        try {
            return userRepository.save(user);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error while saving user " + e.getMessage(), e);
        }
    }

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }
}
