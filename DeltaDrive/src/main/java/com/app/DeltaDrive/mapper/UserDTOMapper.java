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
                user.getBirthday().toString(),
                user.getRole().name()

        );
    }

    public User mapUserDTOToUser(UserDTO userDTO) {

        Date birthday= parseDate(userDTO.birthday());
        Role role = parseRole(userDTO.role());

        return new User(
                userDTO.firstName(),
                userDTO.lastName(),
                userDTO.email(),
                userDTO.password(),
                birthday,
                role
        );
    }

    private Date parseDate(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy.");
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + dateStr, e);
        }
    }

    private Role parseRole(String roleStr) {
        try {
            return Role.valueOf(roleStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role: " + roleStr, e);
        }
    }
}
