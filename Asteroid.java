import javax.swing.*;
import java.awt.*;

public class Asteroid implements MoveableShape {
    private ImageIcon meteor;
    private int width, height;
    private JPanel canvas;
    private int x, y;
    private AsteroidSize size;
    public Asteroid (JPanel canvas,AsteroidSize size,int x, int y) {
        this.canvas = canvas;
        this.x = x; //canvas.getWidth()/2;
        this.y = y; //(int)(Math.random() * canvas.getHeight());
        this.size = size;
        this.height = size.getHeight();
        this.width = size.getWidth();
        this.meteor = scaleImageIcon(new ImageIcon("meteor.png"), size.getWidth(), size.getHeight());
    }
    private ImageIcon scaleImageIcon(ImageIcon icon, int width, int height) {
        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }
    @Override
    public void draw(Graphics2D g2) {
        meteor.paintIcon(canvas, g2, x, y);
    }
    @Override
    public void translate(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
    public int getX() {
        return x;
    }
    public int getY(){
        return y;
    }
    public int getWidth(){return meteor.getIconWidth();}
    public int getHeight() {
        return meteor.getIconHeight();
    }
    public Rectangle getBounds(int x, int y, int width, int height) {
        return new Rectangle(x, y, width, height);
    }
}
