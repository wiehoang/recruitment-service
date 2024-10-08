package vn.unigap.config.auth;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import vn.unigap.api.service.CustomUserDetailsService;
import vn.unigap.config.logging.LoggingFilter;


/** Configure Security Filter and initialize security components. */
@Slf4j
@EnableMethodSecurity
@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

  private final CustomUserDetailsService userService;
  private final JwtAuthFilter jwtAuthFilter;
  private final LoggingFilter loggingFilter;
  private final JwtAuthEntryPoint jwtAuthEntryPoint;

  /** Configures SecurityFilterChain. */
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(request -> request
                    .requestMatchers("/auth/**", "/swagger-ui/**", "/api-docs/**")
                    .permitAll().anyRequest().authenticated())
            .exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthEntryPoint))
            .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
            .authenticationProvider(authenticationProvider());
    http.addFilterBefore(loggingFilter, UsernamePasswordAuthenticationFilter.class);
    http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public AuthenticationManager authenticationManager(
          AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  /** Configures AuthenticationProvider. */
  @Bean
  public DaoAuthenticationProvider authenticationProvider() {

    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userService);
    authProvider.setPasswordEncoder(passwordEncoder());

    return authProvider;
  }


}
