package se.vandingenen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GameController {
    @Autowired
    BullsAndCowsRules rules;

    @GetMapping("/")
    public String showStartPage(){
        return "index";
    }
    @GetMapping("/startGame")
    public String showGame(){
        return "game";
    }
    @PostMapping("/game")
    public String startGame(Model model, @RequestParam("input") int guess){
        Map<Integer, String > resultMap = rules.getMap();
        String result = rules.makeGuess(guess);

        model.addAttribute("resultMap", resultMap);
        model.addAttribute("nGuesses", rules.getNumberOfGuesses());
        model.addAttribute("result", result);
        return "game";
    }
}
