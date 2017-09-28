package model;

import view.Field;

import java.util.Random;

public class Robot {
    public static String robotName;
    public static String robotName() {
        System.out.println("Привет, " + Player.playerName + " я твой противник: Робот R2D2");
        robotName = "R2D2";
        return robotName;
    }
    public static void Move() {
        Random random = new Random();
        System.out.println("Сейчас сходил "+robotName);
        int x = random.nextInt(3);
        int y = random.nextInt(3);
        while (Field.field[x][y] == '0' || Field.field[x][y] == 'X') {
            x = random.nextInt(3);
            y = random.nextInt(3);
        }
        Field.field[x][y] = '0';
    }
}
