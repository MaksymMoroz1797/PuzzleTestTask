package puzzle.game;

import puzzle.view.View;
import puzzle.logic.Cell;
import puzzle.logic.Field;
import puzzle.logic.InputDTO;
import puzzle.logic.OutputDTO;

/**
 * Created by Moroz on 30.07.2017.
 */
public class GameController {

    private View view;

    public GameController(View view) {
        this.view = view;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void start() {
        Field field = new Field();
        boolean continueGame = true;
        OutputDTO outputDTO = new OutputDTO();
        Cell[] cellsOfFieldTemp = field.getCurrentField();
        outputDTO.setField(cellsOfFieldTemp);
        this.view.output(outputDTO);

        do {
            InputDTO inputDTO = this.view.input();
            Cell movedCell = inputDTO.getCell();
            boolean isRestart = inputDTO.isRestart();
            boolean isGameOver = inputDTO.isGameOver();
            outputDTO = new OutputDTO();
            if (movedCell != null) {
                boolean success = field.move(movedCell);
                if (success) {
                    Cell[] cellsOfField = field.getCurrentField();
                    outputDTO.setField(cellsOfField);
                    if (field.hasWan()) {
                        outputDTO.setHasWon(true);
                        continueGame = false;
                    }
                }
                else outputDTO.setIncorrectArguments(true);
            }
            else if (isRestart) {
                field.restart();
                Cell[] cellsOfField = field.getCurrentField();
                outputDTO.setField(cellsOfField);
            }
            else if (isGameOver) {
                outputDTO.setGameOver(true);
                continueGame = false;
            }
            this.view.output(outputDTO);

        } while (continueGame);
    }
}

















