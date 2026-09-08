public class Rectangle extends Shape {

    private double width = 1.0;
    private double length = 1.0;


    public Rectangle(){
        //
    }

    public Rectangle( double width, double length){
        this.width = width;
        this.length = length;
    }

    public Rectangle (String color, boolean filled, double width, double length ){
        super(color,filled);
        this.width = width;
        this.length = length;
    }

    //methods
    public double getArea (){
        return length * width;
    }

    public double getPreimeter(){
        return ( 2 * (length+width));
    }
    //setters
    public void setWidth ( double width){
        this.width = width;
    }
    public void setLength ( double length){
        this.length = length;
    }


    //getters
    public double getWidth(){
        return width;
    }
    public double getLength(){
        return length;
    }


    //toString
    public String toString (){
        return super.toString()+"\n width "+ width + "\nlength "+ length;
    }
}
