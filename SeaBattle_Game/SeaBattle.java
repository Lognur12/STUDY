
public class SeaBattle {
    public static void main(String[] args) {
        Field field = new Field();
        Player player = new Player();
        Ship ship = new Ship();

        field.init();
        field.setShip();

        do {
            field.showField();
            int shoot = player.getShoot();
            field.doShot(shoot);
        }
        while (field.isNotGameOver());
        //while (true);
    }
}
