package se.vandingenen.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import se.vandingenen.BullsAndCowsGame.BullsAndCowsRules;
import se.vandingenen.BullsAndCowsGame.GuessEvaluation;
import se.vandingenen.BullsAndCowsGame.GuessItem;

import java.util.*;

@Service
@SessionScope
public class GameService{
    @Autowired
    BullsAndCowsRules rules;

    private ArrayList<GuessItem> guessResultNumofGueses;
    private String secret;

    @PostConstruct
    public void newGame(){
        guessResultNumofGueses = new ArrayList<>();
        secret = rules.getSecret();
    }

    public String makeGuess(String guess){
        GuessEvaluation evaluation = rules.checkResult(guess);
        String result = rules.showResult(evaluation);
        addResultAndGuess(rules.getNumberOfGuesses(), guess, result);
        if(rules.isFinished(evaluation)){
            return "wrong, try again!";
        }else {
            return "correct!";
        }
    }

    public boolean finished(String resultToShow){
        if(!resultToShow.equalsIgnoreCase("correct!")){
            return false;
        }
        else {
            return true;
        }

    }
    public void startNewGame(){
        guessResultNumofGueses.clear();
        newGame();
    }
    public void addResultAndGuess(int numofGuesses, String guess, String result){
        GuessItem guessItem = new GuessItem(numofGuesses, guess, result);
        guessResultNumofGueses.add(guessItem);

    }

    public ArrayList<GuessItem> getGuessResultNumofGueses() {
        return guessResultNumofGueses;
    }

    public void setGuessResultNumofGueses(ArrayList<GuessItem> guessResultNumofGueses) {
        this.guessResultNumofGueses = guessResultNumofGueses;
    }

}