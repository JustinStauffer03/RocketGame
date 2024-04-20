import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class Rocket implements MoveableShape{
    private ImageIcon rocket;
    private JPanel canvas;
    private int x, y;
    private int dx, dy;
    private ArrayList<Laser> lasers;
    public Rocket (JPanel canvas, int x, int y) {
        this.canvas = canvas;
        rocket = new ImageIcon("rocket.png");
        this.x = x; //canvas.getWidth()/2;
        this.y = y; //(int)(Math.random() * canvas.getHeight());'
        this.dx = 0;
        this.dy = 0;
        lasers = new ArrayList<>();

    }
    public void draw(Graphics2D g2) {
        rocket.paintIcon(canvas, g2, x, y);
    }
    public void translate(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY(){
        return y;
    }
    public void setdX(int dx){
        this.dx = dx;
    }
    public void setdY(int dy){
        this.dy = dy;
    }
    public int getdX() {
        return dx;
    }
    public int getdY(){
        return dy;
    }
    public int getWidth(){return rocket.getIconWidth();}

    public int getHeight() {
        return rocket.getIconHeight();
    }
    public Rectangle getBounds() {return new Rectangle(x, y, rocket.getIconWidth()-25, rocket.getIconHeight()-25);
    }
    public void update() {
        translate(dx, dy);
    }
    // fire away!
    public void fire() {
        int fireX = x + rocket.getIconWidth();
        int fireY = y + rocket.getIconHeight() / 2;
        lasers.add(new Laser(fireX, fireY));
    }
    public List<Laser> getLasers() {
        return lasers;
    }
    public void updatelasers() {
        Iterator<Laser> it = lasers.iterator();
        while (it.hasNext()) {
            Laser l = it.next();
            l.move();
            if (l.isOffScreen(canvas.getWidth())) {
                it.remove();
            }
        }
    }
    public void drawlasers(Graphics2D g2) {
        for (Laser p : lasers) {
            p.draw(g2);
        }
    }
}



