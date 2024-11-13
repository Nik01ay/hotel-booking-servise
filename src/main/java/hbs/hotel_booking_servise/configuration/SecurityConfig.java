package hbs.hotel_booking_servise.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity,
                                                       UserDetailsService userDetailsService,
                                                       PasswordEncoder passwordEncoder) throws Exception {
        var authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService);

        var authProvider = new DaoAuthenticationProvider(passwordEncoder);
        authProvider.setUserDetailsService(userDetailsService);

        authenticationManagerBuilder.authenticationProvider(authProvider);

        return authenticationManagerBuilder.build();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity, AuthenticationManager authenticationManager) throws Exception {
        httpSecurity.authorizeHttpRequests((auth) ->
                        auth.requestMatchers(HttpMethod.POST, "/api/v1/user").anonymous()
                                .requestMatchers(HttpMethod.GET,"/api/v1/user").hasAuthority("ADMIN" )
                                .requestMatchers(HttpMethod.GET,"/api/v1/booking").hasAuthority("ADMIN" )

                                .requestMatchers(HttpMethod.PUT,"/api/v1/hotel").hasAuthority("ADMIN" )
                                .requestMatchers(HttpMethod.DELETE,"/api/v1/hotel/*").hasAuthority("ADMIN" )
                                .requestMatchers(HttpMethod.POST,"/api/v1/hotel").hasAuthority("ADMIN" )

                                .requestMatchers(HttpMethod.PUT,"/api/v1/room").hasAuthority("ADMIN" )
                                .requestMatchers(HttpMethod.DELETE,"/api/v1/room/*").hasAuthority("ADMIN" )
                                .requestMatchers(HttpMethod.POST,"/api/v1/room").hasAuthority("ADMIN" )
                                .requestMatchers("/api/v1/statistic").hasAuthority("ADMIN" )

                               .anyRequest().authenticated()
                )


                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationManager(authenticationManager);

        return httpSecurity.build();
    }


}
