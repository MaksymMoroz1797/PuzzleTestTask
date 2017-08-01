package puzzle.view;

import puzzle.logic.Cell;
import puzzle.logic.InputDTO;
import puzzle.logic.OutputDTO;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Moroz on 29.07.2017.
 */
public class ViewOfGame implements View {

    private static final String MESSAGE_INCORRECT_ARGUMENT =
            "Incorrect argument";
    private static final String MESSAGE_TYPE_PLEACE =
            "Type argument pleace";
    private static final String MESSAGE_GAME_OVER =
            "Game over";
    private static final String MESSAGE_WIN =
            "You has won!";
    private static final String MESSAGE_INSTRUCTION =
            "Hi" + "\nPlease read the instructions.\n" +
                    "Empty cell is denoted as 'X'\n" +
                    "To select a cell for swap, enter its number\n" +
                    "To restart, enter 'restart'\n" +
                    "To finish puzzle, enter 'end'";

    private boolean wasInstruction = false;

    /**
     * Build DTO by string data
     * @param strData
     * @return InputDTO, null if incorrect argument
     */
    private InputDTO parseDTO(String strData) {
        if (strData == null)
            return null;
        final String regexpNumber = "[0-9]+";
        final String regexpRestart = "restart";
        final String regexpGemeOver = "end";
        Pattern patternOfNumber = Pattern.compile(regexpNumber);
        Matcher matcherOfNumber = patternOfNumber.matcher(strData);
        if (matcherOfNumber.find()) {
            String resultStrNamber =
                    strData.substring(matcherOfNumber.start(), matcherOfNumber.end());
            int number = Integer.parseInt(resultStrNamber);
            Cell resCell = Cell.factory(number);
            if (resCell == null)
                return null;
            InputDTO resDTO = new InputDTO();
            resDTO.setCell(resCell);
            return resDTO;
        }
        Pattern patternOfRestart = Pattern.compile(regexpRestart);
        Matcher restartMatcher = patternOfRestart.matcher(strData);
        if (restartMatcher.find()) {
            InputDTO resDTO = new InputDTO();
            resDTO.setRestart(true);
            return resDTO;
        }
        Pattern patternOfGameOver = Pattern.compile(regexpGemeOver);
        Matcher endMatcher = patternOfGameOver.matcher(strData);
        if (endMatcher.find()) {
            InputDTO resDTO = new InputDTO();
            resDTO.setGameOver(true);
            return resDTO;
        }
        return null;
    }

    @Override
    public void output(OutputDTO outputDTO) {
        if (!this.wasInstruction) {
            System.out.println(MESSAGE_INSTRUCTION);
            this.wasInstruction = true;
        }
        System.out.println();
        boolean isIncorrectArguments = outputDTO.isIncorrectArguments();
        if (isIncorrectArguments) {
            System.out.println(MESSAGE_INCORRECT_ARGUMENT);
            return;
        }
        boolean isGameOver = outputDTO.isGameOver();
        if (isGameOver) {
            System.out.println(MESSAGE_GAME_OVER);
            return;
        }
        boolean hasWon = outputDTO.isHasWon();
        if (hasWon) {
            System.out.println(MESSAGE_WIN);
            return;
        }
        Cell[] field = outputDTO.getField();
        Cell emptyCell = Cell.factory(16);
        StringBuffer strOutput = new StringBuffer();
        strOutput.append("Field:\n");
        for (int i = 0; i < field.length; ++i) {
            if (i > 0 && i % 4 == 0) strOutput.append("\n");
            String strTemp = emptyCell.equals(field[i]) ? "X  " :
                    String.format("%-3s", field[i].getNumber());
            strOutput.append(strTemp);
        }
        System.out.println(strOutput.toString());
    }

    @Override
    public InputDTO input() {
        Scanner scanner = new Scanner(System.in);
        String inputStr;
        InputDTO resultDTO = null;
        System.out.println(MESSAGE_TYPE_PLEACE);
        while (resultDTO == null) {
            inputStr = scanner.nextLine();
            resultDTO = this.parseDTO(inputStr);
            if (resultDTO == null)
                System.out.println("\n" + MESSAGE_INCORRECT_ARGUMENT +
                        "\n" + MESSAGE_TYPE_PLEACE);
        }
        return resultDTO;
    }
}
