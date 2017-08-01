package puzzle.logic;

/**
 * Created by Moroz on 30.07.2017.
 */
public class OutputDTO {

    private Cell[] field = null;

    private boolean incorrectArguments = false;

    private boolean gameOver = false;

    private boolean hasWon = false;

    public Cell[] getField() {
        return field;
    }

    public void setField(Cell[] field) {
        this.field = field.clone();
    }

    public boolean isIncorrectArguments() {
        return incorrectArguments;
    }

    public void setIncorrectArguments(boolean incorrectArguments) {
        this.incorrectArguments = incorrectArguments;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isHasWon() {
        return hasWon;
    }

    public void setHasWon(boolean hasWon) {
        this.hasWon = hasWon;
    }
}
