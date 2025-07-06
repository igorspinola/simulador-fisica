package entity;

import main.KeyHandler;
import main.SimulatorPanel;

import java.awt.*;

public class Square extends Particle {
    float side;
    private SimulatorPanel p;
    private KeyHandler keyH;

    public Square(float x, float y, float mass, float side, SimulatorPanel p, KeyHandler keyH) {
        super(x, y, mass);
        this.side = side;
        this.p = p;
        this.keyH = keyH;
        setDefaultValues();
    }
    public void setDefaultValues() {
        velocity.x = 25;
        velocity.y = 0;

    }
    public void update() {

        if(keyH.rightPressed) {
            this.position.x += this.velocity.x / 10;
        }

        if(keyH.leftPressed) {
            this.position.x -= this.velocity.x / 10;
        }


    }
    public void draw(Graphics2D g2) {
        g2.setColor(new Color(248,165,64,255));

        g2.fillRect((int)this.position.x, (int)this.position.y, (int)this.side, (int)this.side);
//        g2.fillRect(200, 200, screenWidth, tileSize);

    }

}
