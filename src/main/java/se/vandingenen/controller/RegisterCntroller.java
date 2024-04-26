package se.vandingenen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.vandingenen.PlayerService;

@Controller
public class RegisterCntroller {

    private PlayerService playerService;
    @Autowired
    public RegisterCntroller(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/register")
    public String showForm(){
        return "registration-form";
    }

    @PostMapping("/register/save")
    public String saveUser(@RequestParam("name") String name, @RequestParam("password") String password, Model model){
        if(playerService.userNameExists(name)){
            String message = "username already exists, choose another username!";
            model.addAttribute("message", message);
            return "registration-form";
        }
        else {
            playerService.register(name, password);
            return "game";
        }
    }
}
