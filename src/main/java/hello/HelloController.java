package hello;

import hello.model.Roles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String getHello(Model model, HttpServletRequest request) {
        boolean isAdmin = request.isUserInRole(Roles.ROLE_ADMIN.toString());
        model.addAttribute("isAdmin", isAdmin);
        return "hello";
    }

    @GetMapping("/secure")
    public String getSecure(Model model, HttpServletRequest request) {
        return "secure";
    }
}
