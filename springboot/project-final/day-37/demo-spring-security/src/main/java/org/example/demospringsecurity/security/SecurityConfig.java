package org.example.demospringsecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity (
    securedEnabled = true,
    jsr250Enabled = true
)
public class SecurityConfig {
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    String[] PUBLIC_PATH = {"/a", "/b"};

    http.authorizeHttpRequests(authorizeRequests -> {
//      authorizeRequests.requestMatchers("/").permitAll();
//      authorizeRequests.requestMatchers(PUBLIC_PATH).permitAll();
//      authorizeRequests.requestMatchers("/user").hasRole("USER");
//      authorizeRequests.requestMatchers("/admin").hasRole("ADMIN");
//      authorizeRequests.requestMatchers("/blogs").hasAnyRole("ADMIN", "USER");
//      authorizeRequests.requestMatchers( HttpMethod.GET, "/movies").hasAnyRole("ADMIN", "USER");
//      authorizeRequests.anyRequest().authenticated();

      authorizeRequests.anyRequest().permitAll();
    });

    http.formLogin(formLogin -> {
      formLogin.defaultSuccessUrl("/", true);
    });

    http.logout(logout -> {
      logout.logoutSuccessUrl("/");
    });
    return http.build();
  }

  @Bean
  public UserDetailsService users() {
    // The builder will ensure the passwords are encoded before saving in memory
    UserBuilder users = User.withDefaultPasswordEncoder();
    UserDetails user = users
        .username("user")
        .password("123")
        .roles("USER")
        .build();
    UserDetails admin = users
        .username("admin")
        .password("123")
        .roles("USER", "ADMIN")
        .build();
    return new InMemoryUserDetailsManager(user, admin);
  }
}
