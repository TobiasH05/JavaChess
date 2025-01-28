package src;

import javax.swing.*;

public class ChessApp{
    public static void initWindow(){
        JFrame window = new JFrame("Chess");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GameLogic board = new GameLogic();
        window.add(board);
        window.addKeyListener(board);

        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                initWindow();
            }
        });
    }
}