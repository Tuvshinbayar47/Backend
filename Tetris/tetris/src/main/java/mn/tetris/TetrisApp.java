package mn.tetris;

import javax.swing.*;

public class TetrisApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tetris Game Panel");
        GamePanel gamePanel = new GamePanel();

        frame.add(gamePanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

