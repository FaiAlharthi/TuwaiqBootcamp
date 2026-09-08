public class Square extends Rectangle{

    public Square(){
        //
    }

    public Square ( String color, boolean filled, double width, double length ){
        super(color,filled, width, length);
    }

    public double getArea (double side){
        return (side * side);
    }

    //setter
    public void setSide ( double side){
        super.setWidth(side);
    }

    //getter
    public double getSide(){
        return super.getWidth();
    }

    public String toString (){
        return "Square Color "+ getColor() + "\n is it Filled ? :"+ getFilled()+"\n side : "+ getSide() + " \nArea of Square "+ getArea(getSide()) ;
    }
}
