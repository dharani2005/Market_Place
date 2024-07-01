package org.example.market_place.converter;

import org.example.market_place.domain.dto.UserDTOView;
import org.example.market_place.domain.entity.User;

public interface UserConverter {
    UserDTOView toUserDto(User entity);

    User toUserEntity(UserDTOView dto);
}
