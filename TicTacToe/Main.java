import controller.EngineGame;
import view.Field;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        EngineGame.process();
        Field.endGame(Field.checkWin());
        Field.showField();
    }
}
