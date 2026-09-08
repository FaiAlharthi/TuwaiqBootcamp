public class Shape {
    private String color;
    private boolean filled;

    public Shape(){
        //
    }

    public Shape(String color, boolean filled){
        this.color = color;
        this.filled = filled;
    }

    //setters
    public void setColor (String color){
        this.color = color;
    }
    public void setFilled ( boolean filled){
        this.filled = filled;
    }

    //getters
    public String getColor(){
        return color;
    }
    public boolean getFilled(){
        return filled;
    }

    //toString
    public String toString(){
        return " Shape Color "+ color
                +" \n Is Filled ??"+ filled;
    }
}
