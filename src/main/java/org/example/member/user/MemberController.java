package org.example.member.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.beforeLogin.MemberVO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    //비밀번호 변경
    @PostMapping("/changePassword")
    public String changePasswordPage(Principal principal, String password) {
        String username = principal.getName();
        boolean updateComplete = memberService.updatePassword(username, password);
        if(updateComplete){
            return "redirect:/myPage";
        }
        return "redirect:/logout";
    }

    //회원 탈퇴
    @PostMapping("/withdrawUser")
    public String withdrawUser(Principal principal) {
        String username = principal.getName();
        memberService.withdrawUser(username);
        return "redirect:/logout";
    }

}
