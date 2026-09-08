import java.util.ArrayList;

public class Novel extends Book {
    private String genre;

    public Novel() {
    }

    public Novel(String title, String auther, String ISBN, double price, int quantity, ArrayList<Review> reviews, String genre) {
        super(title, auther, ISBN, price, quantity, reviews);
        this.genre = genre;
    }

    public Novel(String title, String auther, String ISBN, double price, int quantity, String genre) {
        super(title, auther, ISBN, price, quantity);
        this.genre = genre;
    }

    public String getMediaType(){
        if(getAverageRating() >= 4.5 ){
            return "Bestselling Novel";
        }
        return "Novel";
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Novel{" +
                "genre='" + genre + '\'' +
                '}';
    }
}
