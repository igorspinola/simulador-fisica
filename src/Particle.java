public class Particle {
    Vector position;
    float mass;

    Particle(float x, float y, float mass) {
        this.position = new Vector(x, y);
        this.mass = mass;
    }

}
