package se.vandingenen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class GameController {
    @Autowired
    GameService service;

    @GetMapping("/")
    public String showStartPage() {
        return "index";
    }

    @GetMapping("/startGame")
    public String showGame() {
        return "game";
    }

    @PostMapping("/game")
    public String startGame(Model model, @RequestParam("input") String guess) {
        ArrayList<GuessItem> list = service.getGuessResultNumofGueses();
        String resultToShow = service.makeGuess(guess);

        model.addAttribute("result", resultToShow);
        model.addAttribute("resultList", list);

        return "game";
    }

    @GetMapping("/startNewGame")
    public String startNewGame() {
        service.startNewGame();
        return "game";
    }
}
