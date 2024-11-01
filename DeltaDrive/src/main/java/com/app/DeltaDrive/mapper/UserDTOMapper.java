package com.app.DeltaDrive.mapper;

import com.app.DeltaDrive.dto.UserDTO;
import com.app.DeltaDrive.model.User;
import com.app.DeltaDrive.model.enums.Role;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserDTOMapper {


    public UserDTO mapUserToUserDTO(User user) {
        return new UserDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getBirthday().toString()

        );
    }

    public User mapUserDTOToUser(UserDTO userDTO) {


        //Role role = parseRole(userDTO.role());
        Role role= Role.PASSENGER;
        return new User(
                userDTO.firstName(),
                userDTO.lastName(),
                userDTO.email(),
                userDTO.password(),
                userDTO.birthday(),
                role
        );
    }



}
