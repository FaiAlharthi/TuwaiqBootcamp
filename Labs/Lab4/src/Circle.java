public class Circle extends Shape{
    private double radius ;

    public Circle() {
    }

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        double halfRadius = radius/2;
        return (3.14 * (halfRadius * halfRadius));
    }

    @Override
    public double calculateCircumference() {
        return 3.14*radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
