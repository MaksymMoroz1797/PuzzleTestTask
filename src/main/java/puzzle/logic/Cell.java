package puzzle.logic;

/**
 * Created by Moroz on 30.07.2017.
 */
public class Cell {
    /**
     * Use factory to avoid incorrectly initialized objects
     * @param num
     * @return object of Cell, nyll if incorrect arguments
     */
    public static Cell factory(int num) {
        if (num < 1 || num > 16)
            return null;
        Cell instance = new Cell(num);
        return instance;
    }

    private int number;

    private Cell(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        return number == cell.number;
    }

    @Override
    public int hashCode() {
        return 31 * number;
    }

    @Override
    public String toString() {
        String strRepresentation = this.number == 16 ?
                "X" : Integer.toString(this.number);
        return "Cell{" + strRepresentation + '}';
    }
}
