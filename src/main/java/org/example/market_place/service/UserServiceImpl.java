package org.example.market_place.service;

import org.example.market_place.domain.dto.UserDTOForm;
import org.example.market_place.domain.dto.UserDTOView;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Override
    public UserDTOView register(UserDTOForm dtoForm) {
        return null;
    }

    @Override
    public UserDTOView getByEmail(String email) {
        return null;
    }
}
