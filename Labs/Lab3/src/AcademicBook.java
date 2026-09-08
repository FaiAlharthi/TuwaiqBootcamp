import java.util.ArrayList;

public class AcademicBook extends Book {
    private String subject;

    public AcademicBook() {
    }

    public AcademicBook(String title, String auther, String ISBN, double price, int quantity, ArrayList<Review> reviews, String subject) {
        super(title, auther, ISBN, price, quantity, reviews);
        this.subject = subject;
    }

    public AcademicBook(String title, String auther, String ISBN, double price, int quantity, String subject) {
        super(title, auther, ISBN, price, quantity);
        this.subject = subject;
    }

    public String getMediaType(){
        if(getAverageRating() >= 4.5 ){
            return "Bestselling AcademicBook";
        }
        return "AcademicBook";
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "AcademicBook{" +
                "subject='" + subject + '\'' +
                '}';
    }
}
