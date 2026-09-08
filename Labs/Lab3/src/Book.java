import java.util.ArrayList;
import java.util.Arrays;

public class Book extends Media {
    private int quantity;
    ArrayList<Review> reviews = new ArrayList<>();

    public Book() {
    }

    public Book(String title, String auteur, String ISBN, double price, int quantity) {
        super(title, auteur, ISBN, price);
        this.quantity = quantity;
    }

    public Book(String title, String auther, String ISBN, double price, int quantity, ArrayList<Review> reviews) {
        super(title, auther, ISBN, price);
        this.quantity = quantity;
        this.reviews = reviews;
    }

    public void addReview (Review review){
        reviews.add(review);
    }

    public double getAverageRating(){
        double sum = 0.0;
        double avg = 0.0;
        for ( Review review : reviews){
            sum += review.getRating();
        }
        avg = sum / reviews.size();
        return avg;
    }

    public void purchase(User user){
        user.getPurchaseMediaList().add(this);
        this.quantity -= 1;
    }

    public boolean isBestSeller(){
        if(getAverageRating() >= 4.5){
            return true;
        }
        return false;
    }

    public void restock(int quantity){
        this.quantity += quantity;
        System.out.println("restock proccess done succusfully");
    }


    public String getMediaType(){
        if(isBestSeller()){
            return "Bestselling Book";
        }
        return "Book";
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Book{" +
                "quantity=" + quantity +
                ", reviews=" + reviews +
                '}';
    }
}
