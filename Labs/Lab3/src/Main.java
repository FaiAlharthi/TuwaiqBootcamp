//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

void main() {

    Store centralLibrary = new Store();

    ArrayList<Media> medias = new ArrayList<>();
    ArrayList<User> libraryUsers = new ArrayList<>();

    ArrayList<Review> reviewsBook1 = new ArrayList<>();
    ArrayList<Review> reviewsBook2 = new ArrayList<>();


    Music music1 = new Music("Hello", "Adele", "4111111", 2.1);
    medias.add(music1);


    Review review11 = new Review("Fai", 5, "Great Book !");
    Review review12 = new Review("Nour", 5, "Hate this Book !");

    Review review21 = new Review("Ali", 2, "Exciting Book !");
    Review review22 = new Review("Nour", 3, "Helpful !");

    reviewsBook1.add(review11);
    reviewsBook1.add(review12);

    reviewsBook2.add(review21);
    reviewsBook2.add(review22);

    Book book1 = new Book("Atmoic Habits","James", "1222222", 2.1, 3, reviewsBook1);
    Book book2 = new Book("How to Win Friends and Influence People","Dale", "1333333", 1.1, 10, reviewsBook2);
    medias.add(book1);
    medias.add(book2);


    Movie movie1 = new Movie("Intersteller", "Mathiew", "21111111", 10 , 140);
    medias.add(movie1);

    Review review3 = new Review("Nada", 3, "Really Enjoyed the book");

    Novel novel1 = new Novel("The Great Gatsby", "Scott", "9000000", 2.3, 7, new ArrayList<>(), "Tragedy");
    novel1.addReview(review3);
    medias.add(novel1);

    AcademicBook academicBook1 = new AcademicBook("Clean Code", "Robert", "8888888", 5.5, 4, new ArrayList<>(), "Computer Science");
    medias.add(academicBook1);


    User user1 = new User("Fai", "Fai@example.com");
    User user2 = new User("Ali", "Ali@example.com");

    libraryUsers.add(user1);
    libraryUsers.add(user2);

    centralLibrary = new Store(libraryUsers, medias);

    System.out.println("Library existing media: ");
    for ( Media media : medias){
        System.out.println("    ");
        System.out.println(media.getTitle());
        System.out.println(media.getMediaType());
        System.out.println(media.getAuteur());
        System.out.println(media);
    }

    System.out.println("Before any purchase:");
    System.out.println("book1 stock = " + book1.getQuantity());

    System.out.println("after adding a book, a movie and a song to cart");
    user1.addToCart(book1);
    user1.addToCart(movie1);
    user1.addToCart(music1);

    System.out.println(" checks out");
    user1.checkOut();
    System.out.println(" purchased media: " + user1.getPurchaseMediaList());
    System.out.println(" book1 stock after checkout = " + book1.getQuantity() );


    ArrayList<Media> preloadedPurchases = new ArrayList<>();
    preloadedPurchases.add(book2);
    ArrayList<Media> preloadedCart = new ArrayList<>();
    preloadedCart.add(novel1);

    User user3 = new User("Sara", "Sara@example.com", preloadedPurchases, preloadedCart);
    System.out.println(" user3 purchases = " + user3.getPurchaseMediaList());
    System.out.println(" user3 cart = " + user3.getShoppingCart());


    Movie movie2 = new Movie("Interstellar 2", "Mathiew", "21111112", 9, 150);
    ArrayList<Movie> movieCatalog = new ArrayList<>();
    movieCatalog.add(movie1);
    movieCatalog.add(movie2);

    ArrayList<Movie> recommended = movie1.reccommendSimilarMovies(movieCatalog);
    System.out.println(" movies recommended for movie1 = " + recommended );
}
