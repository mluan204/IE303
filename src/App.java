import javax.swing.*;

public class App {
    public static void main(String[] args) throws Exception {
        JFrame mainWindown = new JFrame("Flappy Bird");

        int boardWidth = 360;
        int boardHeight = 640;
        mainWindown.setSize(boardWidth, boardHeight);

        // Nằm giữa màn hình
        mainWindown.setLocationRelativeTo(null);

        // Không cho phép thay đổi kích thước
        mainWindown.setResizable(false);

        FlappyBird flappyBird = new FlappyBird();
        mainWindown.add(flappyBird);

        mainWindown.setVisible(true);
        mainWindown.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}