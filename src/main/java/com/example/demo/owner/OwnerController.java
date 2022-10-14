package com.example.demo.owner;

import com.example.demo.entity.Owner;
import com.example.demo.owner.dto.SignUpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;


    @PreAuthorize("isAnonymous()")
    @GetMapping("/login")
    public String Login(){
        return "owner/login";
    }

    @GetMapping("/new")
    public String signUp(SignUpDto signUpDto){
        return "owner/sign_up";
    }

    @PostMapping("/new")
    public String signUp(@Validated SignUpDto signUpDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "owner/sign_up";
        } else if (!(signUpDto.getPassword().equals(signUpDto.getPassword2()))) {
            bindingResult.rejectValue("password2","passwordInCorrect","패스워드가 다릅니다");
            return "owner/sign_up";
        }

        try {
            ownerService.save(signUpDto);
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            bindingResult.reject("duplicated","이미 등록된 아이디입니다.");
            return "owner/sign_up";
        }catch (Exception e){
            return "owner/sign_up";
        }
        return"owner/success";
    }
}
