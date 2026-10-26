package com.pk.Student_Management_project.configure;

import com.pk.Student_Management_project.Service.MyUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityFilterConfg {

    private final MyUserDetailService userDetailsService;

    @Bean
    public SecurityFilterChain springSecurityFilter(HttpSecurity http) throws Exception {

        return http
                .csrf(customizer -> customizer.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/api/user/register").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())

                .build();

    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user1 = User
//                .withDefaultPasswordEncoder()
//                .username("Pavan")
//                .password("Pavan@123")
//                // .roles("User")
//                .build();
//
//        return new InMemoryUserDetailsManager(user1);
//    }
//   @Bean
//   public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder(12);
//   }

    @Bean
    public AuthenticationProvider authenticationProvider(MyUserDetailService userDetailsService) {
            DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
            provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
            //provider.setUserDetailsService(userDetailsService);
            return provider;
        }


}