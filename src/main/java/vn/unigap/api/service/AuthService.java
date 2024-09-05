package vn.unigap.api.service;

import vn.unigap.api.dto.in.AuthLogInDtoIn;
import vn.unigap.api.dto.in.AuthSignUpDtoIn;
import vn.unigap.api.dto.out.AuthDtoOut;
import vn.unigap.api.entity.User;


/** Service interface for handling user authentication, including signup and login operations. */
public interface AuthService {

  User signup(AuthSignUpDtoIn authSignUpDtoIn);

  AuthDtoOut login(AuthLogInDtoIn authLogInDtoIn);

}
