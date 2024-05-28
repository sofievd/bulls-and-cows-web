package se.vandingenen.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import se.vandingenen.service.PlayerService;

import java.io.IOException;

@Controller
public class LoginController {
    @Autowired
    private PlayerService playerService;
    @GetMapping("/login")
    public String showlogin(){
        return "registration/login-form";
    }

    @GetMapping("/success")
    public void loginRedirect(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException{
        String role = authResult.getAuthorities().toString();
        if(role.contains("PLAYER")){
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() +"/startGame"));
        }
    }

    @GetMapping("/access-denied")
    public String showAccesDenied(){
        return "registration/access-denied";
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
}
