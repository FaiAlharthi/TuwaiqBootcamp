public class Book extends Product{

    private String author;

    public Book() {
    }

    public Book(String name, double price, String author) {
        super(name, price);
        this.author = author;
    }

    public double getDiscount(){
        return this.getPrice() * 0.20;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }
}
