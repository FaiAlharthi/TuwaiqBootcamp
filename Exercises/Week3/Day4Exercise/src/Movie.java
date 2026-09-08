public class Movie extends Product{
    private String director;

    public Movie() {
    }

    public Movie(String name, double price, String director) {
        super(name, price);
        this.director = director;
    }

    public double getDiscount(){
        return this.getPrice()*0.50;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}
