package puzzle.game;

import puzzle.view.View;
import puzzle.view.ViewOfGame;

/**
 * Created by Moroz on 30.07.2017.
 */
public class Runner {
    public static void main(String[] args) {
        View view = new ViewOfGame();
        new GameController(view).start();
    }
}
