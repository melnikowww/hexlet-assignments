package exercise;

// BEGIN
public class Circle {
    protected Point point;
    protected int radius;
    final double PI = 3.1415;

    public Circle(Point point, int radius) {
        this.point = point;
        this.radius = radius;
    }
    public int getRadius() {
        return this.radius;
    }
    public double getSquare() throws NegativeRadiusException {
        if (this.radius < 0) {
            throw new NegativeRadiusException("\n");
        } else {
            return PI * this.radius * this.radius;
        }
    }
}
// END
