package puzzle.logic;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Moroz on 29.07.2017.
 */
public class Field {

    private static final int SIZE_OF_CELLS_ARRAY = 16;

    private Cell[] cellsOfField = new Cell[SIZE_OF_CELLS_ARRAY];
    // To reduce the use of the method getIndexOfCell
    private int indexOfEmpty;

    public Field() {
        init();
    }

    private void init() {
        Random rand = new Random();
        do {
            for (int i = 0; i < cellsOfField.length; i++)
                cellsOfField[i] = Cell.factory(i + 1);
            indexOfEmpty = 15;
            int n = SIZE_OF_CELLS_ARRAY;
            while (n > 1) {
                int r = rand.nextInt(n--);
                switchCells(n, r);
            }
        } while (!isCorrectField());
        final Cell emptyCell = Cell.factory(16);
        indexOfEmpty = getIndexOfCell(emptyCell);
    }

    /**
     * Swap elements in the cellsOfField field
     * @param i
     * @param j
     */
    private void switchCells(int i, int j) {
        Cell temp = cellsOfField[i];
        cellsOfField[i] = cellsOfField[j];
        cellsOfField[j] = temp;
    }

    /**
     * Not all randomised fields are correct
     * @return isCorrect
     */
    private boolean isCorrectField() {
        int countInversions = 0;
        for (int i = 0; i < cellsOfField.length - 1; ++i) {
            for (int j = i + 1; j < cellsOfField.length; j++) {
                if (cellsOfField[j].getNumber() < cellsOfField[i].getNumber())
                    countInversions++;
            }
        } //System.out.println(countInversions + " " + this.indexOfEmpty);
        boolean result = (countInversions + (this.indexOfEmpty / 4 + 1)) % 2 == 0;
        return result;
    }

    private int getIndexOfCell(Cell cell) {
        int resultIndex = 0;
        for (int i = 0; i < cellsOfField.length; ++i)
            if (cellsOfField[i].equals(cell)) {
                resultIndex = i;
                break;
            }
        return resultIndex;
    }

    /**
     * We can swap only neighbor cells
     * @param indexX
     * @param indexY
     * @return can swap
     */
    private boolean checkSwitchable(int indexX, int indexY) {
        int x_1 = indexX % 4;
        int y_1 = indexX / 4;
        int x_2 = indexY % 4;
        int y_2 = indexY / 4; //System.out.println(x_1 + " " + y_1 + "\n" + x_2 + " " + y_2);
        boolean result = !(Math.abs(x_1 - x_2) > 1 || Math.abs(y_1 - y_2) > 1 ||
                (Math.abs(x_1 - x_2) == 1 && Math.abs(y_1 - y_2) == 1));
        return result;
    }

    /**
     * Checks the winning of the field
     * @return isWin
     */
    public boolean hasWan() {
        boolean result = true;
        for (int i = 0; i < cellsOfField.length; ++i) {
            Cell temp1 = Cell.factory(i + 1);
            Cell temp2 = cellsOfField[i];
            if (!temp1.equals(temp2)) {
                result = false;
                i = cellsOfField.length;
            }
        }
        return result;
    }

    public void restart() {
        init();
    }

    /**
     * Method swap choosed number with empty cell
     * @param cell
     * @return success of moving
     * @throws NullPointerException
     */
    public boolean move(Cell cell) throws NullPointerException {
        if (cell == null)
            throw new NullPointerException();
        int emptyIndex = this.indexOfEmpty;
        int currentIndex = getIndexOfCell(cell);
        if (!checkSwitchable(emptyIndex, currentIndex))
            return false;
        switchCells(emptyIndex, currentIndex);
        this.indexOfEmpty = currentIndex;
        return true;
    }
    // not used method
    public boolean move(int x, int y) {
        if (x < 1 || x > 4 | y < 1 || y > 4)
            return false;
        int empty = this.indexOfEmpty;
        --x;
        --y;
        int currentIndex = y * 4 + x;//System.out.println("111  " + indexOfEmpty);
        if (!checkSwitchable(empty, currentIndex))
            return false;
        switchCells(empty, currentIndex);
        this.indexOfEmpty = currentIndex;
        return true;
    }

    public Cell[] getCurrentField() {
        Cell[] result = new Cell[SIZE_OF_CELLS_ARRAY];

        for (int i = 0; i < SIZE_OF_CELLS_ARRAY; ++i)
            result[i] = cellsOfField[i];
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Field field = (Field) o;

        if (indexOfEmpty != field.indexOfEmpty) return false;
        return Arrays.equals(cellsOfField, field.cellsOfField);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(cellsOfField);
        result = 31 * result + indexOfEmpty;
        return result;
    }

    @Override
    public String toString() {
        StringBuffer strRepresentation = new StringBuffer();
        strRepresentation.append("Field{\n");
        for (int i = 0; i < cellsOfField.length; ++i) {
            if (i > 0 && i % 4 == 0) strRepresentation.append("\n");
            String strTempNum = String.format("%-9s", cellsOfField[i]);
            strRepresentation.append(strTempNum);
        }
        strRepresentation.append('}');
        return strRepresentation.toString();
    }

}


