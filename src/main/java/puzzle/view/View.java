package puzzle.view;

import puzzle.logic.InputDTO;
import puzzle.logic.OutputDTO;

/**
 * Created by Moroz on 29.07.2017.
 */
public interface View {
    void output(OutputDTO dto);
    InputDTO input();
}
