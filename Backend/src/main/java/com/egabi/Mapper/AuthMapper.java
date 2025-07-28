package com.egabi.Mapper;

import com.egabi.User;
import com.egabi.DTO.AuthDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AuthMapper {
  AuthMapper INSTANCE = Mappers.getMapper(AuthMapper.class);

  AuthDTO userToUserDTO(User user);
  User userDTOToUser(AuthDTO userDTO);
}
