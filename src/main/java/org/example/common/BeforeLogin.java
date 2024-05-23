package org.example.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BeforeLogin {

    @GetMapping("/main")
    public void main() {}

    @GetMapping("/loginPage")
    public String loginPage() {
        return "beforeLogin/loginPage";
    }

    @GetMapping("/registerPage")
    public String registerPage() {
        return "beforeLogin/registerPage";
    }

    @GetMapping("/findIdPage")
    public String findIdPage() {
        return "beforeLogin/findIdPage";
    }

    @GetMapping("/findPasswordPage")
    public String findPasswordPage() {
        return "beforeLogin/findPasswordPage";
    }

}
