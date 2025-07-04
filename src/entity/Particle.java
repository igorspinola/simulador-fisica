package entity;

public abstract class Particle {
    Vector position;
    Vector velocity;
    Vector acceleration;
    float mass;

    Particle(float x, float y, float mass) {
        this.position = new Vector(x, y);
        this.mass = mass;
        this.velocity = new Vector(0, 0);
        this.acceleration = new Vector(0,0);
    }
    Particle(float x, float y, float mass, float Vx, float Vy) {
        this.position = new Vector(x, y);
        this.mass = mass;
        this.velocity = new Vector(Vx, Vy);
        this.acceleration = new Vector(0,0);
    }

}
