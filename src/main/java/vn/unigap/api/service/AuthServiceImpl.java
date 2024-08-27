package vn.unigap.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.unigap.api.dto.in.AuthSignInDtoIn;
import vn.unigap.api.dto.in.AuthSignUpDtoIn;
import vn.unigap.api.dto.out.AuthDtoOut;
import vn.unigap.api.entity.CustomUserDetails;
import vn.unigap.api.entity.Role;
import vn.unigap.api.entity.User;
import vn.unigap.api.repository.RoleRepository;
import vn.unigap.api.repository.UserRepository;
import vn.unigap.common.exception.ApiException;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User signup(AuthSignUpDtoIn authSignUpDtoIn) {

        // Handle exists username
        if (userRepository.findByUsername(authSignUpDtoIn.getUsername()).isPresent()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Username already exists");
        }

        // Handle invalid role
        Role role = roleRepository.findByName(authSignUpDtoIn.getRole())
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Invalid role"));

        // Create a new record if passing validation
        User newUser = new User();
        newUser.setUsername(authSignUpDtoIn.getUsername().toLowerCase());
        newUser.setPassword(passwordEncoder.encode(authSignUpDtoIn.getPassword()));
        newUser.getRoles().add(role);
        userRepository.save(newUser);
        role.getUsers().add(newUser);

        return newUser;
    }

    @Override
    public AuthDtoOut signin(AuthSignInDtoIn authSignInDtoIn) {

        // Use AuthenticationManager to authenticate input
        Authentication authentication =  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authSignInDtoIn.getUsername(),
                        authSignInDtoIn.getPassword()));

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateToken(customUserDetails);

        return AuthDtoOut.builder().token(jwt).build();

    }

}
