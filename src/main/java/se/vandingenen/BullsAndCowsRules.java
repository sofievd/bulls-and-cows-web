package se.vandingenen;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@Component
public class BullsAndCowsRules {
    private int numberOfGuesses = 1;
    private String secret;

    @PostConstruct
    public void newGame(){
        secret = generateNumberOrWord();
    }

    /**
     * Returns a 4-digit number of type String.
     * An ArrayList containing digits from 0 to 9 is shuffled. The final number is obtained by concatenating
     * an empty String with digits at the first four indices of the shuffled Arraylist.
     *
     * @return a 4-digit number with different digits and of type String
     */
    public String generateNumberOrWord() {
        List<Integer> digits = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 0));
        Collections.shuffle(digits);

        // pick four unique digits
        System.out.println("number: ");
        System.out.println( "" + digits.get(0)+digits.get(1)+digits.get(2)+ digits.get(3));
        return ""+digits.get(0) + digits.get(1) + digits.get(2) + digits.get(3);
    }

    /**
     * This method goes through all the digits in the secret (or generated) number, checks if
     * these digits are present in the guessed number by getting their index.
     * If a digit has the same index as in the secret number, it is counted as a bull,
     * if the digit has a different index, it's a cow.
     * The number of bulls and cows is then saved in the form of a new object of record GuessEvaluation.
     *
     *
     * @param guess  This is the number (type String) which is guessed by the player.
     * @return a new object of record GuessEvaluation
     */
    public GuessEvaluation checkResult(String guess) {
        int numberAtIncorrectPosition = 0, numberAtCorrectPosition = 0;
        for (int i = 0; i < secret.length(); i++) {
            char digitInNumber = secret.charAt(i);

            int positionInGuess = guess.indexOf(digitInNumber);
            boolean digitPresentInGuess = (positionInGuess != -1);

            if (i == positionInGuess) {
                numberAtCorrectPosition++;
            } else if (digitPresentInGuess) {
                numberAtIncorrectPosition++;
            }
        }
        return new GuessEvaluation(numberAtCorrectPosition, numberAtIncorrectPosition);
    }

    /**
     * Returns a String of B (bulls) and C (cows) using the GuessEvaluation argument
     * which has the info about the number of bulls and number of cows.
     * <p>
     * Note that the Bs are concatenated with Cs using a comma.
     *
     * @param guessEvaluation It has the number of bulls and cows as its parameters.
     * @return A String of Bs (i.e. bulls) and Cs (i.e. cows)
     */
    public String showResult(GuessEvaluation guessEvaluation) {

        String result = "";
        for (int i = 0; i < guessEvaluation.valueCountAtCorrectPlace(); i++) {
            result = result + "B";
        }
        result = result + ",";
        for (int i = 0; i < guessEvaluation.valueCountAtIncorrectPlace(); i++) {
            result = result + "C";
        }
        return result;
    }

    /**
     * Returns a boolean depending upon whether the given argument matches the final goal (i.e. "BBBB,").
     * Note that if the argument does not match the final goal, the number of guesses increase by 1.
     *
     * @param guessResult It is a String of Bs and Cs which gets generated depending upon the player's input.
     * @return True only if the given argument matches the final goal i.e. "BBBB," .
     */

    public boolean isFinished(GuessEvaluation guessResult) {
        if (guessResult.valueCountAtCorrectPlace() != 4) {
            numberOfGuesses++;
            return true;
        }
        return false;
    }

    /**
     * Returns the number of guesses it takes to guess the generated secret number.
     *
     * @return The number of guesses.
     */
    public int getNumberOfGuesses() {
        return numberOfGuesses;
    }
    public String getSecret(){
        return secret;
    }
}

