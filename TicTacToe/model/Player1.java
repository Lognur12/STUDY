package model;

import view.Field;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Player1 {
    public static String player1Name;
     public static String getNamePlayer1() throws IOException {
        System.out.println("Введите имя Игрока №2: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
         player1Name = reader.readLine();
        return player1Name;
    }
    public static void player1Step() throws IOException {
        System.out.println(player1Name + " делает ход.");
        int x = Player.getX();
        int y = Player.getY();
        while (Field.field[x][y] == '0' || Field.field[x][y] == 'X') {
            System.out.println("Уже сюда ходили. Еще раз.");
            x = Player.getX();
            y = Player.getY();
        }
        Field.field[x][y] = '0';
    }
}
