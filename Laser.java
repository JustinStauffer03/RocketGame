import java.awt.geom.Line2D;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Laser implements MoveableShape {
    private Line2D.Double line;

    private int dx; // laser speed

    public Laser(int x, int y) {

        this.line = new Line2D.Double(x, y, x+10, y );
        this.dx = 5;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.draw(line);
    }

    public void translate(int dx, int dy) {
        line.setLine(line.getX1() + dx, line.getY1() , line.getX2() + dx, line.getY2());
    }

    public void move() {
        translate(dx, 0);
    }


    public boolean isOffScreen(int width) {
        return line.getX1() > width || line.getX2() > width;

    }

    public Rectangle getBounds() {
        int width = Math.abs((int) line.getX2() - (int) line.getX1());
        int height = Math.abs((int) line.getY2() - (int) line.getY1());
        return new Rectangle((int) line.getX1(), (int) line.getY1() - height, width, height + 2); // +2 for visibility
    }
}

