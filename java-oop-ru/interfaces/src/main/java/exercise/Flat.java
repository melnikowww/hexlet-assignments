package exercise;

// BEGIN
public class Flat implements Home {
    double area;
    double balconyArea;
    int floor;

    public Flat(double area, double balcony, int floor) {
        this.area = area;
        this.balconyArea = balcony;
        this.floor = floor;
    }

    @Override
    public double getArea() {
        return area + balconyArea;
    }

    @Override
    public String toString() {
        return "Квартира площадью " + this.getArea() + " метров на " + this.floor + " этаже";
    }

    @Override
    public int compareTo(Home another) {
        if (this.getArea() > another.getArea()) {
            return 1;
        } else if (this.getArea() < another.getArea()) {
         return -1;
        } else {
            return 0;
        }
    }
}
// END
