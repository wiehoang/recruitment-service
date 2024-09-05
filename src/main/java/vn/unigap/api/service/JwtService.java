package vn.unigap.api.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import vn.unigap.api.entity.CustomUserDetails;


/** Service interface for managing authentication and authorization operations. */
@Service
public interface JwtService {

  String generateToken(CustomUserDetails customUserDetails);

  String extractUsername(String token);

  boolean isTokenValid(String token, UserDetails userDetails);

}
