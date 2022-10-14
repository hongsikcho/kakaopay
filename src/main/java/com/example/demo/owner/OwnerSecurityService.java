package com.example.demo.owner;

import com.example.demo.entity.Owner;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnerSecurityService implements UserDetailsService {

    private final OwnerRepository ownerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Owner> loginOwner = ownerRepository.findByLoginId(username);

        if(loginOwner.isEmpty()){
            throw new RuntimeException("찾을 수 없는 아이디입니다.");
        }

        Owner owner = loginOwner.get();
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ADMIN"));

        return new User(owner.getLoginId(), owner.getPassword(), authorityList);
    }
}
