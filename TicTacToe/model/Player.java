package model;

import view.Field;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Player {
    public static String playerName;
    public static int whoPlay() throws IOException {
        System.out.println("**********КРЕСТИКИ НОЛИКИ**********");
        System.out.println("Укажите кто будет противник(1-человек, 2 - робот.):");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(reader.readLine());
        while (a != 1 && a != 2) {
            System.out.println("Введите 1 или 2");
            a = Integer.parseInt(reader.readLine());
        }
        return a;
    }
    public static String getNamePlayer() throws IOException {
        System.out.println("Введите имя Игрока №1: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        playerName = reader.readLine();
        return playerName;
    }
    public static void playerStep() throws IOException {
        System.out.println(playerName + " делает ход.");
        int x = getX();
        int y = getY();
        while (Field.field[x][y] == '0' || Field.field[x][y] == 'X') {
            System.out.println("Уже сюда ходили. Еще раз.");
            x = getX();
            y = getY();
        }
        Field.field[x][y] = 'X';
    }
    public static void hello()  {
        System.out.println(playerName + " и "+ Player1.player1Name + ", добро пожаловать в игру Крестики нолики!");
        System.out.println("Игроки делают ход по очереди.");
    }
    public static int getX() throws IOException {
        System.out.println("Введите координаты по X:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int x = Integer.parseInt(reader.readLine());
        while (x < 0 || x > 2) {
            System.out.println("Неправльная координата X, еще раз введите X:");
            x = Integer.parseInt(reader.readLine());
        }
        return x;
    }
    public static int getY () throws IOException {
        System.out.println("Теперь по Y:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int y = Integer.parseInt(reader.readLine());
        while (y < 0 || y > 2) {
            System.out.println("Неправльная координата Y, еще раз введите Y:");
            y = Integer.parseInt(reader.readLine());
        }
        return y;
    }
}
