package com.example.demo.owner;

import com.example.demo.entity.Owner;
import com.example.demo.owner.dto.SignUpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final PasswordEncoder passwordEncoder;

    public void save(SignUpDto signUpDto) {
        ownerRepository.save(
                Owner.builder()
                        .loginId(signUpDto.getId())
                        .name(signUpDto.getName())
                        .password(passwordEncoder.encode(signUpDto.getPassword()))
                        .number(signUpDto.getNumber())
                        .build()
        );
    }
}
