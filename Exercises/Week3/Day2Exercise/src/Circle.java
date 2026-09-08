public class Circle extends Shape {
    private double radius = 1.0;


    public Circle(){
        //
    }

    public Circle(double radius){
        this.radius = radius;
    }

    public Circle(String color, boolean filled, double radius){
        super(color, filled);
        this.radius = radius;
    }

    //methods
    public double getArea (){
        return (3.14 * (radius * radius));
    }

    public double getPreimeter (){
        return (3.14 * (2 * radius));
    }


    //setters
    public void setRadius ( double radius){
        this.radius = radius;
    }

    //getters
    public double getRadius (){
        return radius;
    }

    public String toString (){
        return super.toString()+ "\nThe radius "+ radius;
    }
}
