package org.example.beforeLogin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.time.Instant;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BeforeLoginController {

    private final BeforeLoginService beforeLoginService;
    private final PasswordEncoder passwordEncoder;

    //회원가입
    @PostMapping("/saveUser")
    public String saveUser(MemberDTO memberDTO) {

        if(beforeLoginService.existByUsername(memberDTO.getUsername())){
            throw new RuntimeException();
        }
        if(beforeLoginService.existByEmail(memberDTO.getEmail())){
            throw new RuntimeException();
        }

        Instant now = Timestamp.from(Instant.now()).toInstant();
        memberDTO.setRegisterdate(now);
        memberDTO.setPassword(passwordEncoder.encode(memberDTO.getPassword()));

        beforeLoginService.registerUser(memberDTO);

        return "beforeLogin/loginPage";
    }

    //아이디 찾기
    @PostMapping("/findUsername")
    public ModelAndView findUsername(String email) {
        MemberDTO memberDTO = beforeLoginService.findByEmail(email);
        ModelAndView modelAndView = new ModelAndView();
        if(memberDTO == null){
            modelAndView.setViewName("beforeLogin/findIdPage");
            return modelAndView;
        }
            modelAndView.addObject("username", memberDTO.getUsername());
            modelAndView.setViewName("beforeLogin/viewUsername");
        return modelAndView;
    }

    //임시 비밀번호 발급
    @PostMapping("/findPassword")
    public String findPassword(String username, String email) {
        boolean success = beforeLoginService.findByUsernameAndEmail(username, email);
        if(!success){
            return "redirect:/findPasswordPage";
        }
        return "redirect:/viewChangedPassword";
    }
    @GetMapping("/viewChangedPassword")
    public String viewChangedPassword() {
        return "beforeLogin/viewChangedPassword";
    }
}
