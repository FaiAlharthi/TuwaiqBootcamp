//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    System.out.println("    -----------------------------------------------Question 1------------------------------------------    ");

    Book novel = new Book("The Great Gatsby", 30, "John");
    Book nove2 = new Book("Atmic habits", 23, "lindsey");
    Book nove3 = new Book("young Wonmen", 40, "jack");
    Book nove4 = new Book("Rich poor dad", 30, "John");
    Book nove5 = new Book("Harry Poter", 50, "John");
    System.out.println("              ");

    System.out.println("Book1");
    System.out.println("The book information and price before any discount ");
    System.out.println("The book title: " + novel.getName());
    System.out.println("The book author: " +novel.getAuthor());
    System.out.println("The book price before discount : " +novel.getPrice());
    System.out.println("              ");
    System.out.println("The book information and price after discount ");
    System.out.println("The book title: " +novel.getName());
    System.out.println("The book author: " +novel.getAuthor());
    System.out.println("The book price After discount : " + (novel.getPrice()- novel.getDiscount()));
    System.out.println("              ");

    System.out.println("Book2");
    System.out.println("The book information and price before any discount ");
    System.out.println("The book title: " + nove2.getName());
    System.out.println("The book author: " +nove2.getAuthor());
    System.out.println("The book price before discount : " +nove2.getPrice());
    System.out.println("              ");
    System.out.println("The book information and price after discount ");
    System.out.println("The book title: " +nove2.getName());
    System.out.println("The book author: " +nove2.getAuthor());
    System.out.println("The book price After discount : " + (nove2.getPrice()- nove2.getDiscount()));
    System.out.println("              ");

    System.out.println("Book3");
    System.out.println("The book information and price before any discount ");
    System.out.println("The book title: " + nove3.getName());
    System.out.println("The book author: " +nove3.getAuthor());
    System.out.println("The book price before discount : " +nove3.getPrice());
    System.out.println("              ");
    System.out.println("The book information and price after discount ");
    System.out.println("The book title: " +nove3.getName());
    System.out.println("The book author: " +nove3.getAuthor());
    System.out.println("The book price After discount : " + (nove3.getPrice()- nove3.getDiscount()));
    System.out.println("              ");

    System.out.println("Book4");
    System.out.println("The book information and price before any discount ");
    System.out.println("The book title: " + nove4.getName());
    System.out.println("The book author: " +nove4.getAuthor());
    System.out.println("The book price before discount : " +nove4.getPrice());
    System.out.println("              ");
    System.out.println("The book information and price after discount ");
    System.out.println("The book title: " +nove4.getName());
    System.out.println("The book author: " +nove4.getAuthor());
    System.out.println("The book price After discount : " + (nove4.getPrice()- nove4.getDiscount()));
    System.out.println("              ");

    System.out.println("Book5");
    System.out.println("The book information and price before any discount ");
    System.out.println("The book title: " + nove5.getName());
    System.out.println("The book author: " +nove5.getAuthor());
    System.out.println("The book price before discount : " +nove5.getPrice());
    System.out.println("              ");
    System.out.println("The book information and price after discount ");
    System.out.println("The book title: " +nove5.getName());
    System.out.println("The book author: " +nove5.getAuthor());
    System.out.println("The book price After discount : " + (nove5.getPrice()- nove5.getDiscount()));
    System.out.println("              ");

    System.out.println("              ");

    Movie movie = new Movie("intersteller", 40 , "James");
    Movie movie2 = new Movie("the walking dead", 20 , "michle");
    Movie movie3 = new Movie("Devil wears prada", 30 , "Anne");
    Movie movie4 = new Movie("Jocker", 45 , "James");
    Movie movie5 = new Movie("Patman", 50 , "Henry");

    System.out.println("Movie1");
    System.out.println("The Movie information and price before any discount ");
    System.out.println("The book title: " + movie.getName());
    System.out.println("The book author: " +movie.getDirector());
    System.out.println("The book price before discount : " +movie.getPrice());
    System.out.println("              ");
    System.out.println("The book information and price after discount ");
    System.out.println("The book title: " +movie.getName());
    System.out.println("The book author: " +movie.getDirector());
    System.out.println("The book price After discount : " + (movie.getPrice()- movie.getDiscount()));
    System.out.println("              ");

    System.out.println("Movie2");
    System.out.println("The Movie information and price before any discount ");
    System.out.println("The book title: " + movie2.getName());
    System.out.println("The book author: " +movie2.getDirector());
    System.out.println("The book price before discount : " +movie2.getPrice());
    System.out.println("              ");
    System.out.println("The book information and price after discount ");
    System.out.println("The book title: " +movie2.getName());
    System.out.println("The book author: " +movie2.getDirector());
    System.out.println("The book price After discount : " + (movie2.getPrice()- movie2.getDiscount()));
    System.out.println("              ");

    System.out.println("Movie3");
    System.out.println("The Movie information and price before any discount ");
    System.out.println("The book title: " + movie3.getName());
    System.out.println("The book author: " +movie3.getDirector());
    System.out.println("The book price before discount : " +movie3.getPrice());
    System.out.println("              ");
    System.out.println("The book information and price after discount ");
    System.out.println("The book title: " +movie3.getName());
    System.out.println("The book author: " +movie3.getDirector());
    System.out.println("The book price After discount : " + (movie3.getPrice()- movie3.getDiscount()));
    System.out.println("              ");

    System.out.println("Movie4");
    System.out.println("The Movie information and price before any discount ");
    System.out.println("The book title: " + movie4.getName());
    System.out.println("The book author: " +movie4.getDirector());
    System.out.println("The book price before discount : " +movie4.getPrice());
    System.out.println("              ");
    System.out.println("The book information and price after discount ");
    System.out.println("The book title: " +movie4.getName());
    System.out.println("The book author: " +movie4.getDirector());
    System.out.println("The book price After discount : " + (movie4.getPrice()- movie4.getDiscount()));
    System.out.println("              ");

    System.out.println("Movie5");
    System.out.println("The Movie information and price before any discount ");
    System.out.println("The book title: " + movie5.getName());
    System.out.println("The book author: " +movie5.getDirector());
    System.out.println("The book price before discount : " +movie5.getPrice());
    System.out.println("              ");
    System.out.println("The book information and price after discount ");
    System.out.println("The book title: " +movie5.getName());
    System.out.println("The book author: " +movie5.getDirector());
    System.out.println("The book price After discount : " + (movie5.getPrice()- movie5.getDiscount()));
    System.out.println("              ");

    System.out.println("    -----------------------------------------------Question 2------------------------------------------    ");
    MovablePoint vacume = new MovablePoint(100 , 200 , 20, 30);

    System.out.println("              ");
    System.out.println("The Vacume movement information before any adjusting ");
    System.out.println(" x point Position: " + vacume.getX());
    System.out.println(" y point Position: " + vacume.getY());
    System.out.println("how many steps it can move at one time horizontally : " +vacume.getxSpeed());
    System.out.println("how many steps it can move at one time vertically : " +vacume.getySpeed());

    System.out.println("              ");

    System.out.println("The Vacume movement information after changing it moves ( UP and Right ) ");
    System.out.println("moving up once and moving Right twice : " );
    vacume.moveUp();
    vacume.moveRight();
    vacume.moveRight();
    System.out.println(" x point Position: " + vacume.getX());
    System.out.println(" y point Position: " + vacume.getY());


    System.out.println("The Vacume movement information after changing it moves ( Down and Left ) ");
    System.out.println("moving Down twice and moving Left once : " );
    vacume.moveDown();
    vacume.moveDown();
    vacume.moveLeft();
    System.out.println(" x point Position: " + vacume.getX());
    System.out.println(" y point Position: " + vacume.getY());

    System.out.println("              ");




}
