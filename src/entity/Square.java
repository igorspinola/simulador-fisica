package entity;

import main.KeyHandler;
import main.SimulatorPanel;

import java.awt.*;

public class Square extends Particle {
    float side;
    private SimulatorPanel p;
    private KeyHandler keyH;

    //variáveis para posição
    float initialX;
    float initialY;
    double vx;
    double vy;
    double timeElapsed;
    boolean launched;

    public Square(float x, float y, float mass, float side, SimulatorPanel p, KeyHandler keyH) {
        super(x, y, mass);
        this.side = side;
        this.p = p;
        this.keyH = keyH;
        this.initialX = x;
        this.initialY = y;
        this.launched = false;
        setDefaultValues();
    }


    public void setDefaultValues() {
        velocity.x = 20;
        velocity.y = 0;
        acceleration.y = 10;
        this.standardize();

    }

    public void launch (double velocity, double angleDegrees, float height){
        this.initialX = this.position.x;
        this.initialY = height;

        double angleRad = Math.toRadians(angleDegrees);
        this.vx = velocity * Math.cos(angleRad);
        this.vy = velocity * Math.sin(angleRad);

        this.timeElapsed = 0;
        this.launched = true;

        // Ajusta posição vertical inicial
        this.position.x = initialX;
        this.position.y = initialY;
    }

    public void update() {
        if (launched) {
            double g = 9.8;
            float scale = 10f;
            timeElapsed += 1.0 / p.getFPS();

            this.position.x = (float) (initialX + vx * timeElapsed * scale);
            this.position.y = (float) (p.screenHeight - (initialY + vy * timeElapsed - 0.5 * g * timeElapsed * timeElapsed) * scale - side);

            if (this.position.y >= p.groundLevelY() - side) {
                this.position.y = p.screenHeight - side;
                this.launched = false;
            }
        } else {
            if(keyH.rightPressed) {
                this.position.x += 3;
            }
            if(keyH.leftPressed) {
                this.position.x -= 3;
            }
        }
    }


    public void draw(Graphics2D g2) {
        g2.setColor(new Color(248,165,64,255));

        g2.fillRect((int)this.position.x, (int)this.position.y, (int)this.side, (int)this.side);
//        g2.fillRect(200, 200, screenWidth, tileSize);

    }

}
