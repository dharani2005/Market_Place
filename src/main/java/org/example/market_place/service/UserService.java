package org.example.market_place.service;

import org.example.market_place.domain.dto.UserDTOForm;
import org.example.market_place.domain.dto.UserDTOView;

public interface UserService {

    //register user
    UserDTOView register(UserDTOForm dtoForm);

    //Find user by email
    UserDTOView getByEmail(String email);

    //Authenticate user
    UserDTOView authenticateUser(UserDTOForm dtoForm);

    //Find products by user email and password
    UserDTOView findUserByEmailAndPassword(String email, String password);


}
