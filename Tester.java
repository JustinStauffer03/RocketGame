import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Tester {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Blast them");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AsteroidPanel panel = new AsteroidPanel();
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
        panel.requestFocusInWindow();
    }





}
