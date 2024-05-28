package se.vandingenen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.vandingenen.service.GameService;
import se.vandingenen.BullsAndCowsGame.GuessItem;

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
        return "game/game-guess-form";
    }

    @PostMapping("/game")
    public String startGame(Model model, @RequestParam("number") String guess) {
        ArrayList<GuessItem> list = service.getGuessResultNumofGueses();
        String resultToShow = service.makeGuess(guess);

        model.addAttribute("result", resultToShow);
        model.addAttribute("resultList", list);

        return "game/game-guess-form";
    }

    @GetMapping("/startNewGame")
    public String startNewGame() {
        service.startNewGame();
        return "game/game-guess-form";
    }

}
