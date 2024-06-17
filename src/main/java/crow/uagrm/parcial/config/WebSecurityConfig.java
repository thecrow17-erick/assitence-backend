package crow.uagrm.parcial.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import crow.uagrm.parcial.security.JwtRequestFilter;
import crow.uagrm.parcial.utils.RoleName;
import lombok.AllArgsConstructor;


@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig {

  private JwtRequestFilter jwtRequestFilter;
  private AuthenticationProvider authenticationProvider;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
    return http
      .authorizeHttpRequests(authRequest -> 
        authRequest
        .requestMatchers("/user/**").hasAuthority(RoleName.ADMIN.name())
        .requestMatchers("/career/**").hasAnyAuthority(RoleName.ADMIN.name())
        .requestMatchers("/matter/**").hasAnyAuthority(RoleName.ADMIN.name())
        .requestMatchers("/module/**").hasAnyAuthority(RoleName.ADMIN.name())
        .requestMatchers("/classroom/**").hasAnyAuthority(RoleName.ADMIN.name())
        .requestMatchers("/management/**").hasAnyAuthority(RoleName.ADMIN.name())
        .requestMatchers("/type-period/**").hasAnyAuthority(RoleName.ADMIN.name())
        .requestMatchers("/period/**").hasAnyAuthority(RoleName.ADMIN.name())
        .requestMatchers("/group/**").hasAnyAuthority(RoleName.ADMIN.name())
        .requestMatchers("/workload/**").hasAnyAuthority(RoleName.ADMIN.name())
        .requestMatchers("/horary/**").hasAnyAuthority(RoleName.DOCENTE.name())
        .requestMatchers("/assists/**").hasAnyAuthority(RoleName.DOCENTE.name())
        .requestMatchers(HttpMethod.POST,"/auth/**").permitAll()
        .anyRequest().permitAll()
      )
      .sessionManagement(sessionManager->
        sessionManager 
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class
      )
      .csrf(csrf -> 
        csrf
        .disable()
      )
      .build();
  }

}
