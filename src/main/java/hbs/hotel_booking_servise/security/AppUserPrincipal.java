package hbs.hotel_booking_servise.security;

import hbs.hotel_booking_servise.domain.entity.User;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AppUserPrincipal implements UserDetails {

    private final User user;

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {

             return List.of(new SimpleGrantedAuthority(user.getRole().name()));

        }

        public Long getId(){
            return user.getId();
        }




        @Override
        public String getPassword() {
            return user.getPassword();
        }

        @Override
        public String getUsername() {
            return user.getName();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
