public class Vector {
    float x;
    float y;

    Vector() {}

    Vector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void add(Vector v) {
       this.x += v.x;
       this.y += v.y;
    }

    public void subtract(Vector v) {
        this.x -= v.x;
        this.y -= v.y;
    }

    public void scale(float n) {
        this.x *= n;
        this.y *= n;
    }

    public Vector rotate(float angle) {
        Vector result = new Vector();
        result.x = (float) (x * Math.cos(angle) - y * Math.sin(angle)) ;
        result.y = (float) (x * Math.sin(angle) + y * Math.cos(angle)) ;
        return result;
    }

    public float magnitude() {
        return (float) (Math.sqrt(x * x + y * y));
    }

    public float magnitudeSquared() {
        return x * x + y * y;
    }

    public Vector normalize() {
       float length = this.magnitude();
       if (length != 0.0) {
           this.x /= length;
           this.y /= length;
       }
       return this;
    }

    public Vector unitVector() {
        Vector result = new Vector();
        float length = this.magnitude();
        if (length != 0.0) {
            result.x = this.x / length;
            result.y = this.y / length;
        }
        return result;
    }

    public Vector normalVector() {
        return new Vector(-y, x).normalize();
    }

    float dot(Vector v) {
        return (x * v.x) + (y * v.y);
    }

    float cross(Vector v) {
        return (x * v.y) - (y * v.x);
    }
}
