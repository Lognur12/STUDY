import java.util.Scanner;

public class Player {
    String name;

    static int Deck() {
        System.out.println("Введите количество палуб коробля(не более 3):");
        Scanner scan = new Scanner(System.in); // создали экземпляр класса Scanner (выделили память) и передали ему поток ввода от клавиатуры (System.in)
        String f;
        f = scan.nextLine(); // программа будет ждать, пока пользователь не нажмет Enter
        int e = Integer.parseInt(f);
        System.out.printf("Вы ввели: %s\n", e);
        if (e<= 3) {
            System.out.printf("палуб будет %s, Поле боя: \n", e);
        } else {
            System.out.println("Сказано: не более 3-х!!");
        }
        return e;
    }

    static int getShoot() {
        System.out.println("Стреляйте(введите номер ячейки поля):");
        Scanner scanner = new Scanner(System.in); // создали экземпляр класса Scanner (выделили память) и передали ему поток ввода от клавиатуры (System.in)
        String s;
        s = scanner.nextLine(); // программа будет ждать, пока пользователь не нажмет Enter
        int shoot = Integer.parseInt(s);
        System.out.printf("Вы ввели: %s\n", s);
        return shoot;
    }
}
