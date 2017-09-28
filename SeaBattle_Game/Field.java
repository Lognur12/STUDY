import java.util.Random;

public class Field {
    public static final int SIZE = 10;
    char[] cells = new char[SIZE];
    Random random = new Random();
    int p = random.nextInt(SIZE);
    int e = Player.Deck();

    void init() {
        for (int i = 0; i < cells.length; i++) {
            cells[i] = '.';
        }
    }

    void showField() {
        System.out.println(cells);
        System.out.println();
    }

    int doShot(int shoot) {
                switch (cells[shoot]) {
                    case '.':
                        System.out.println("Промах");
                        cells[shoot] = '*';
                        break;
                    case '*':
                        System.out.println("Уже стреляли");
                        break;
                    case 'X':
                        System.out.println("Супер! Корабль Подбит!");
                            cells[shoot] = '^';
                        int q=0;
                            q++;
                        showField();
                        return q;
                           // break;
                    default:
                        System.out.println("ERROR");
                }
        return shoot;
    }

    boolean isNotGameOver() {
        return q >= e;
    }

    void setShip() {
        for (int i = 1; i <=e; i++) {
            cells[p] = 'X';
            p++;
                    }
           }
}