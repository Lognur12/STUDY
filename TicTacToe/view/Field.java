package view;

import model.Player;
import model.Player1;

public class Field {
    public static char[][] field = new char[3][3];

    public static void initField() {
        System.out.println("Поле игры размером 3*3(x*y):");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[j][i] = '*';
            }
        }
    }
    public static void showField() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(field[j][i]);
            }
            System.out.println();
        }
    }
    public static void endGame(char win) {
        if (win == 'X') {
            System.out.println(Player.playerName + " ты выиграл!");
        }
        if (win == '0') {
            System.out.println(Player1.player1Name + " выиграл!");
        }
        if (win == '!' && !emptyField()) {
            System.out.println("Увы, ничья!");
        }
    }
    public static boolean emptyField() {
        boolean zero = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == '*') {
                    zero = true;
                    break;
                }
            }
        }
        return zero;
    }
    public static char checkWin() {
        char win = '!';
        for (int i = 0; i < 3; i++) {
            if (field[i][0] == field[i][1] && field[i][1] == field[i][2] && field[i][0] != '*') {
                win = field[i][0];
                break;
            }
        }
        if (win == '!') {
            for (int i = 0; i < 3; i++) {
                if (field[0][i] == field[1][i] && field[1][i] == field[2][i] && field[0][i] != '*') {
                    win = field[0][i];
                    break;
                }
            }
        }
        if (win == '!') {
            if (field[0][0] == field[1][1] && field[1][1] == field[2][2] && field[0][0] != '*') {
                win = field[0][0];
            }
        }
        if (win == '!') {
            if (field[0][2] == field[1][1] && field[1][1] == field[2][0] && field[0][2] != '*') {
                win = field[0][2];
            }
        }
        return win;
    }
}
