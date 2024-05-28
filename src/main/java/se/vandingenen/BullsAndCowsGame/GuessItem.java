package se.vandingenen.BullsAndCowsGame;

public class GuessItem {
    private int numOfGuesses;
    private String guess;
   private String result;

    public GuessItem(int numOfGuesses, String guess, String result) {
        this.numOfGuesses = numOfGuesses;
        this.guess = guess;
        this.result = result;
    }

    public int getNumOfGuesses() {
        return numOfGuesses;
    }

    public void setNumOfGuesses(int numOfGuesses) {
        this.numOfGuesses = numOfGuesses;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
