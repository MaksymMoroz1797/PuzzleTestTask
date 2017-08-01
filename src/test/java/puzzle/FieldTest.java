package puzzle;

import puzzle.logic.Cell;
import puzzle.logic.Field;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * Created by Moroz on 30.07.2017.
 */
public class FieldTest {

    private static final int SIZE_OF_ARRAY = 16;

    private static Cell[] cells = new Cell[SIZE_OF_ARRAY];

    static {
        cells[0] = Cell.factory(13); cells[1] = Cell.factory(2); cells[2] = Cell.factory(10); cells[3] = Cell.factory(3);

        cells[4] = Cell.factory(1); cells[5] = Cell.factory(12); cells[6] = Cell.factory(8); cells[7] = Cell.factory(4);

        cells[8] = Cell.factory(5); cells[9] = Cell.factory(16); cells[10] = Cell.factory(9); cells[11] = Cell.factory(6);

        cells[12] = Cell.factory(15); cells[13] = Cell.factory(14); cells[14] = Cell.factory(11); cells[15] = Cell.factory(7);
    }

    /*static {
        cells[0] = Cell.factory(1); cells[1] = Cell.factory(2); cells[2] = Cell.factory(3); cells[3] = Cell.factory(4);

        cells[4] = Cell.factory(5); cells[5] = Cell.factory(6); cells[6] = Cell.factory(7); cells[7] = Cell.factory(16);

        cells[8] = Cell.factory(9); cells[9] = Cell.factory(10); cells[10] = Cell.factory(11); cells[11] = Cell.factory(8);

        cells[12] = Cell.factory(13); cells[13] = Cell.factory(14); cells[14] = Cell.factory(15); cells[15] = Cell.factory(12);
    }*/

    private Field preparedFieldToTest() {
        java.lang.reflect.Field field1 = null;
        java.lang.reflect.Field field2 = null;
        try {
            field1 = Field.class.getDeclaredField("cellsOfField");
            field2 = Field.class.getDeclaredField("indexOfEmpty");
        } catch (NoSuchFieldException e) { /* Nuthing */ }

        field1.setAccessible(true);
        field2.setAccessible(true);
        Field preparedField = new Field();
        try {
            field1.set(preparedField, cells.clone());
            field2.set(preparedField, 9);
        } catch (IllegalAccessException e) { /* Nuthing */ }
        return preparedField;
    }

    @org.junit.Test
    public void isCorrectFieldTest() throws Exception {
        Method method = Field.class.getDeclaredMethod("isCorrectField");
        method.setAccessible(true);
        Field instance = preparedFieldToTest();
        boolean result = (boolean) method.invoke(instance);
        assertTrue(result);
    }

    @org.junit.Test
    public void move() {
        Field instance = preparedFieldToTest();
        Cell cellReplaced = Cell.factory(12);
        instance.move(cellReplaced);
        Cell[] cells1 = instance.getCurrentField();
        final int changedCellIndex = 5;
        Cell changedCell = cells1[changedCellIndex];
        Cell emptyCell = Cell.factory(16);
        assertEquals(changedCell, emptyCell);
    }

    @org.junit.Test(expected = NullPointerException.class)
    public void move1() throws Exception {
        Field instance = new Field();//System.out.println(instance);
        Cell cellReplaced = null;
        instance.move(cellReplaced);
    }

    @org.junit.Test
    public void move2() {
        Field instance = preparedFieldToTest();
        Cell cellReplaced = Cell.factory(11);
        boolean success = !instance.move(cellReplaced);
        Cell[] cells1 = instance.getCurrentField();
        final int changedCellIndex = 9;
        Cell changedCell = cells1[changedCellIndex];
        Cell emptyCell = Cell.factory(16);
        boolean result = success && changedCell.equals(emptyCell);
        assertTrue(result);
    }

    @org.junit.Test
    public void move3() {
        Field instance = preparedFieldToTest();
        Cell cellReplaced = Cell.factory(1);
        boolean success = !instance.move(cellReplaced);
        assertTrue(success);
    }

    @org.junit.Test
    public void hasWonTest() throws NoSuchFieldException, IllegalAccessException {
        Cell[] testCells = new Cell[SIZE_OF_ARRAY];
        for (int i = 0; i < testCells.length; i++)
            testCells[i] = Cell.factory(i + 1);
        Field instance = new Field();
        java.lang.reflect.Field cellsField = Field.class.getDeclaredField("cellsOfField");
        cellsField.setAccessible(true);
        cellsField.set(instance, testCells);
        boolean result = instance.hasWan();
        assertTrue(result);
    }

    @org.junit.Test
    public void hasWonTest1() throws NoSuchFieldException, IllegalAccessException {
        Field instance = new Field();
        java.lang.reflect.Field cellsField = Field.class.getDeclaredField("cellsOfField");
        cellsField.setAccessible(true);
        cellsField.set(instance, cells.clone());
        boolean result = instance.hasWan();
        assertFalse(result);
    }

    @org.junit.Test
    public void equals() {
        Field instance1 = preparedFieldToTest();
        Field instance2 = preparedFieldToTest();
        assertEquals(instance1, instance2);
    }
}