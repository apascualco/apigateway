package com.apascualco.blog.security.service;

import com.apascualco.blog.persistence.model.entities.User;
import com.apascualco.blog.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserSecurityService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format("The email %s doesn't exist", email)));
        if(!user.isActive()) {
            throw new UsernameNotFoundException(email + " is disable");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                this.converteRolesToSimpleGrantedAuthority(user)
        );
    }

    private Set<SimpleGrantedAuthority> converteRolesToSimpleGrantedAuthority(User user) {
        return user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName())
                ).collect(Collectors.toSet());
    }

}
