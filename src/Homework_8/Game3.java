package Homework_8;

public class Game3 {
    public static int tryCount = 3;
    public static int magicNumber;
    public static MainWindow main = new MainWindow();

    public static void main(String[] args) {
        startTheGame();
    }

    public static void startTheGame() {
        generateMagicNum();
        main.setVisible(true);
    }

    public static void restartTheGame() {
        main.setVisible(false);
        generateMagicNum();
        tryCount = 3;
        main.resetButtons();
        main.setVisible(true);
    }

    public static void generateMagicNum() {
        magicNumber = (int) (Math.random() * 8 + 1);
    }
}

