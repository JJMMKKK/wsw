package org.example.common;

import lombok.RequiredArgsConstructor;
import org.example.member.user.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class Logined {

    //마이페이지
    @PostMapping("/myPage")
    public String myPage(Principal principal) {
        if (principal != null) {
            return "logined/myPage";
        }
        return "redirect:/logout";
    }

    @PostMapping("/clothEditPage")
    public String clothEditPage(Principal principal) {
        if (principal != null) {
            return "logined/clothEditPage";
        }
        return "redirect:/logout";
    }

    @PostMapping("/changePasswordPage")
    public String changePasswordPage(Principal principal) {
        if (principal != null) {
            return "logined/changePasswordPage";
        }
        return "redirect:/logout";
    }

    @PostMapping("/changeEmailPage")
    public String changeEmailPage(Principal principal) {
        if (principal != null) {
            return "logined/changeEmailPage";
        }
        return "redirect:/logout";
    }

    @PostMapping("/logout")
    public String logout() {
        return "main";
    }
}
