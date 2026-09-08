import java.util.ArrayList;

public class Movie extends Media {
    private int minuts;

    public Movie() {
    }

    public Movie(String title, String auther, String ISBN, double price, int minuts) {
        super(title, auther, ISBN, price);
        this.minuts = minuts;
    }

    public void watch(User user){
        user.getPurchaseMediaList().add(this);
    }

    public ArrayList<Movie> reccommendSimilarMovies(ArrayList<Movie> movieCatalog){
        ArrayList<Movie> reccommendedMovies = new ArrayList<>();
        for (Movie movie : movieCatalog){
            if (movie != this && movie.getAuteur().equalsIgnoreCase(this.getAuteur())){
                reccommendedMovies.add(movie);
            }
        }
        return reccommendedMovies;
    }

    public String getMediaType(){
        if ( minuts >= 120){
            return "Long Movie";
        }
        return "Movie";
    }


    public double getMinuts() {
        return minuts;
    }

    public void setMinuts(int minuts) {
        this.minuts = minuts;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "minuts=" + minuts +
                '}';
    }
}
