package puzzle;

import puzzle.view.ViewOfGame;
import puzzle.logic.Cell;
import puzzle.logic.InputDTO;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * Created by Moroz on 30.07.2017.
 */
public class ViewOfGameTest {

    private InputDTO invokeMethod(String str) {
        Method method = null;
        InputDTO resInputDTO = null;
        try {
            method = ViewOfGame.class.getDeclaredMethod("parseDTO", String.class);
        } catch (NoSuchMethodException e) { /* Nuthing */ }
        method.setAccessible(true);
        ViewOfGame instance = new ViewOfGame();
        try {
            resInputDTO = (InputDTO) method.invoke(instance, str);
        } catch (InvocationTargetException e) {
            /* Nuthing */
        } catch (IllegalAccessException e) { /* Nuthing */ }
        return resInputDTO;
    }

    @org.junit.Test
    public void parseDTO1() {
        String arg = "12";
        InputDTO instance = invokeMethod(arg);
        Cell testCell = Cell.factory(12);
        Cell resCell = instance.getCell();
        boolean result = resCell.equals(testCell) &&
                !instance.isRestart() && !instance.isGameOver();
        assertTrue(result);
    }

    @org.junit.Test
    public void parseDTO2() {
        String arg = "restart";
        InputDTO instance = invokeMethod(arg);
        boolean result = instance.isRestart() &&
                !instance.isGameOver() && instance.getCell() == null;
        assertTrue(result);
    }

    @org.junit.Test
    public void parseDTO3() {
        String arg = "end";
        InputDTO instance = invokeMethod(arg);
        boolean result = instance.isGameOver() &&
                !instance.isRestart() && instance.getCell() == null;
        assertTrue(result);
    }

    @org.junit.Test
    public void parseDTO4() {
        String arg = "/z";
        InputDTO instance = invokeMethod(arg);
        assertNull(instance);
    }

    @org.junit.Test
    public void parseDTO5() {
        String arg = null;
        InputDTO instance = invokeMethod(arg);
        assertNull(instance);
    }
}