package com.egabi.Mapper;

import com.egabi.Main.User;
import com.egabi.DTO.AuthDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    AuthDTO userToAuthDTO(User user);
    User authDTOToUser(AuthDTO dto);
}