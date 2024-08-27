package vn.unigap.api.service;

import vn.unigap.api.dto.in.AuthSignInDtoIn;
import vn.unigap.api.dto.in.AuthSignUpDtoIn;
import vn.unigap.api.dto.out.AuthDtoOut;
import vn.unigap.api.entity.User;

public interface AuthService {
    User signup(AuthSignUpDtoIn authSignUpDtoIn);
    AuthDtoOut signin(AuthSignInDtoIn authSignInDtoIn);
}
