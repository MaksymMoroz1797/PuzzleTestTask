package puzzle.logic;

/**
 * Created by Moroz on 30.07.2017.
 */
public class InputDTO {

    private Cell cell = null;

    private boolean isRestart = false;

    private boolean isGameOver = false;

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public boolean isRestart() {
        return isRestart;
    }

    public void setRestart(boolean restart) {
        isRestart = restart;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

}
