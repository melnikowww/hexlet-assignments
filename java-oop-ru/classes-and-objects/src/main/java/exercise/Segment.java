package exercise;
// BEGIN
public class Segment {

    private Point start;
    private Point stop;

    public Segment(Point first, Point second) {
        this.start = first;
        this.stop = second;
    }

    public Point getBeginPoint() {
        return this.start;
    }

    public Point getEndPoint() {
        return this.stop;
    }

    public Point getMidPoint() {
        int newX = (this.start.getX() + this.stop.getX()) / 2;
        int newY = (this.start.getY() + this.stop.getY()) / 2;
        return new Point(newX, newY);
    }
}
// END
