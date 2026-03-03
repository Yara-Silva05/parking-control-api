package com.api.parkingcontrol.configs.security;

import com.api.parkingcontrol.models.UserModel;
import com.api.parkingcontrol.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    //vai buscar na base de dados qual que é o usuario correspondente a esse UserName(devemos garantir q seja unico)

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserModel userModel = userRepository.findByUserName(userName)
                .orElseThrow(()-> new UsernameNotFoundException("User not found with UserName: " + userName));
        return new User(
                userModel.getUsername(),
                userModel.getPassword(), true, true, true, true,
                userModel.getAuthorities());
    }
}
