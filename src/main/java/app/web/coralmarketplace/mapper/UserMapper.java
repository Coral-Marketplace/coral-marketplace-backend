package app.web.coralmarketplace.mapper;

import org.mapstruct.Mapper;

import app.web.coralmarketplace.dto.UserDto;
import app.web.coralmarketplace.model.User;

@Mapper
public interface UserMapper {

    UserDto mapEntityToDto(User user);

    User mapDtoEntity(UserDto userDto);

}
