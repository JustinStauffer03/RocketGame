import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class AsteroidPanel extends JPanel {

    final int PANEL_WIDTH = 1000, PANEL_HEIGHT = 500;
    private int DELAY = 5;
    private Rocket rocket;
    private Timer t;
    ArrayList<Asteroid> roids;
    AsteroidPanel here;
    private int score = 0;

    private double ScoreIncrease = 10.0;
   private int count = 0;

   private double probability = 0.003;


    public AsteroidPanel() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.BLACK);
        here = this;

        count = 0;
        roids = new ArrayList<>();
        int y = (int)(Math.random() * PANEL_HEIGHT);
        roids.add(new Asteroid(AsteroidPanel.this, AsteroidSize.SMALL, PANEL_WIDTH, y));//STARTS SMALL
        rocket = new Rocket(this, 0, PANEL_HEIGHT -290); // Positions the rocket at the bottom middle of the screen
        t = new Timer(DELAY, event -> {
            if (Math.random() < probability) {
                int tempY = (int)(Math.random() * PANEL_HEIGHT);
                AsteroidSize[] sizes = AsteroidSize.values();
                AsteroidSize randomSize = sizes[(int) (Math.random() * sizes.length)];
                roids.add(new Asteroid(AsteroidPanel.this, randomSize, PANEL_WIDTH, tempY));
            }
            rocket.update();
            rocket.updatelasers();

            for (Asteroid roid : roids) {
                roid.translate(-1, 0);
            }
            checkCollisions();
            checkRocketCollision();
            here.repaint();
        });
        t.start();
        here.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                rocket.fire();
            }
        });
        here.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        rocket.setdX(-1);
                        break;
                    case KeyEvent.VK_RIGHT:
                        rocket.setdX(1);
                        break;
                    case KeyEvent.VK_UP:
                        rocket.setdY(-1);
                        break;
                    case KeyEvent.VK_DOWN:
                        rocket.setdY(1);
                        break;
                }
            }
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_RIGHT:
                        rocket.setdX(0);
                        break;
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_DOWN:
                        rocket.setdY(0);
                        break;
                }
            }
        });
        here.setFocusable(true);
        here.requestFocusInWindow();
    }
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        for (Asteroid roid : roids) {
            roid.draw(g2);
        }
        rocket.draw(g2);
        rocket.drawlasers(g2);

    }
    public void checkCollisions() {
        ArrayList<Laser> lasersToRemove = new ArrayList<>();
        ArrayList<Asteroid> asteroidsToRemove = new ArrayList<>();
        for (Laser laser : new ArrayList<>(rocket.getLasers())) {
            Rectangle laserBounds = laser.getBounds();
            for (Asteroid asteroid : new ArrayList<>(roids)) {
                Rectangle asteroidBounds = new Rectangle(asteroid.getX() , asteroid.getY(), asteroid.getWidth(), asteroid.getHeight());
                if (laserBounds.intersects(asteroidBounds)) {
                    lasersToRemove.add(laser);
                    score = score + (int)ScoreIncrease;
                    //This section is for increasing speed of meteors as they are shot along with increasing score as the game gets more challenging, the asteroids are worth more
                    probability = probability + 0.00025;
                    ScoreIncrease = ScoreIncrease * 1.05;
                    asteroidsToRemove.add(asteroid);
                }
            }
        }
        rocket.getLasers().removeAll(lasersToRemove);
        roids.removeAll(asteroidsToRemove);
    }
    public void checkRocketCollision() {
        Rectangle rocketBounds = rocket.getBounds();
        for (Asteroid asteroid : roids) {
            Rectangle asteroidBounds = new Rectangle(asteroid.getX(), asteroid.getY(), asteroid.getWidth()-10, asteroid.getHeight()-28);
            if (rocketBounds.intersects(asteroidBounds)) {
                t.stop();
                JOptionPane.showMessageDialog(this, "Game Over! Score: " + score, "Collision!", JOptionPane.WARNING_MESSAGE);
                System.exit(0);
                break;
            }
        }
    }
}











