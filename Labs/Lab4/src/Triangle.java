public class Triangle extends Shape {
    private double height;
    private double base;

    public Triangle() {
    }

    public Triangle(double height, double base) {
        this.height = height;
        this.base = base;
    }

    @Override
    public double calculateArea() {
        return ( 0.5 * base *height);
    }

    @Override
    public double calculateCircumference() {
        return base*3;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getHeight() {
        return height;
    }

    public double getBase() {
        return base;
    }
}

