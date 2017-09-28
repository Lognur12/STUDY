package controller;

import model.*;
import view.Field;
import java.io.IOException;

public class EngineGame {
    public static void process() throws IOException {
        switch (Player.whoPlay()) {
            case 2:
                Player.getNamePlayer();
                Robot.robotName();
                Field.initField();
                while (true) {
                    Field.showField();
                    Player.playerStep();
                    if (Field.checkWin() != '!' || !Field.emptyField()) {
                        break;
                    }
                    Field.showField();
                    Robot.Move();
                    if (Field.checkWin() != '!' || !Field.emptyField()) {
                        break;
                    }
                }
                break;
            case 1:
                Player.getNamePlayer();
                Player1.getNamePlayer1();
                Player.hello();
                Field.initField();
                while (true) {
                    Field.showField();
                    Player.playerStep();
                    if (Field.checkWin() != '!' || !Field.emptyField()) {
                        break;
                    }
                    Field.showField();
                    Player1.player1Step();
                    if (Field.checkWin() != '!' || !Field.emptyField()) {
                        break;
                    }
                }
                break;
        }
    }
}
