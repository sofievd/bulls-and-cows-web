package se.vandingenen.BullsAndCowsGame;
/**
 * @param valueCountAtCorrectPlace   This saves the number of digits/letters at the correct position in the number/word
 *                                   guessed by the player.
 * @param valueCountAtIncorrectPlace This saves the number of digits/letters at the incorrect position in the number/word
 *                                   guessed by the player.
 * @author Sofie van Dingenen
 * @version 1.0
 * <p>
 *     <h2>GuessEvaluation</h2>
 * This record saves the number of correct and incorrect positions in a guess when compared to the secret/final goal.
 * @date 2024-01-29
 */
public record GuessEvaluation(int valueCountAtCorrectPlace, int valueCountAtIncorrectPlace) {
}
