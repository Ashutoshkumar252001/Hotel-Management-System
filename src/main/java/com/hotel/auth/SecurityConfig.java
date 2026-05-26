package com.hotel.auth;

import com.hotel.enums.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig { // this is the core of integration

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/register","/register-user","/css/**","/js/**").permitAll()

                        // Only write operations require admin roles (new, save, edit, delete)
                        .requestMatchers("/admin/hotel/**")
                        .hasAnyRole(Role.ADMIN.getRoleName())

                        .requestMatchers("/admin/room/**")
                        .hasAnyRole(Role.ADMIN.getRoleName())

                        .requestMatchers("/booking/list",
                                                   "/booking/delete/**")
                        .hasRole("ADMIN")

                        .requestMatchers("/customer/list")
                        .hasRole("ADMIN")


                        .requestMatchers("/hotel/list/",
                                                   "/hotel/find/**",
                                                   "/room/list/",
                                                   "/room/find**"

                        )

                        .hasAnyRole("USER","ADMIN")


                        .requestMatchers(
                                "/customer/new**",

                                "/customer/save**"
                                )
                        .hasRole("USER")

                        .requestMatchers(
                                "/admin/**"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                "/booking/my-bookings/**"
                        ).hasRole("USER")

                        .requestMatchers(
                                "/booking/new/**"
                        ).hasRole("USER")


                        .requestMatchers(
                                "/booking/new",
                                "/booking/save",
                                "/booking/edit/**",
                                "/booking/find/**",
                                "/booking/my-bookings**",
                                "/booking/delete/**"

                        )
                        .hasAnyRole("USER")



                        // List, view, find pages -- any authenticated user
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .exceptionHandling(ex -> ex
                        .accessDeniedPage("/access-denied")
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}

