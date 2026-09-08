public class Media {
    private String title;
    private String auteur;
    private String ISBN;
    private double price;

    public Media() {
    }

    public Media(String title, String auther, String ISBN ,double price) {
        this.title = title;
        this.auteur = auther;
        this.ISBN = ISBN;
        this.price = price;

    }

    @Override
    public String toString() {
        return "Media{" +
                "title='" + title + '\'' +
                ", auther='" + auteur + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", price=" + price +
                '}';
    }

    public String getMediaType(){
        return "Media";
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
