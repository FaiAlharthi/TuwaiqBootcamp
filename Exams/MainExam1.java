//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    //Exam 1

    Scanner scanner = new Scanner(System.in);

    // Question 1
    // Write a Java program that accept three numbers from the user and print the largest number .
    System.out.println("Question 1/");

    System.out.println("Enter a number");
    int numberQ1 = scanner.nextInt();

    int largest = numberQ1;

    for ( int i = 1 ; i < 3 ; i ++){
        System.out.println("Enter a number");
        numberQ1 = scanner.nextInt();

        if(largest > numberQ1){
            largest = largest;

        }else{
            largest = numberQ1;
        }
    }

    System.out.println("The largest number is : " + largest);

    System.out.println(" ");

// --------------------------------------------------------------------------------------------------------------------------------------------------------------

    // Question 2
    // Write a Java program that accept a String and a number from the user, then print the character at the given index( the number) .
    System.out.println("Question 2/");

    System.out.println("Enter a word : ");
    scanner.nextLine();
    String wordQ2 = scanner.nextLine();

    System.out.println("Enter number to find its character : ");
    int numberQ2 = scanner.nextInt();

    System.out.println("The charcter of the " +numberQ2 + " index is : " + wordQ2.charAt(numberQ2));

    System.out.println(" ");

// --------------------------------------------------------------------------------------------------------------------------------------------------------------

    // Question 3
    // Write a Java program that allows the user to enter numbers repeatedly until they choose to stop, then displays the total sum of all entered numbers.
    System.out.println("Question 3/");

    System.out.println("Enter a number and write a ( 0 ) to stop");
    int numberQ3 = scanner.nextInt();

    int sum = 0;

    while ( numberQ3 != 0){
        sum = sum+numberQ3;

        System.out.println("Enter a number and write a ( 0 ) to stop");
        numberQ3 = scanner.nextInt();
    }

    System.out.println("The summation of all numbers is : " + sum  );

    System.out.println(" ");

// --------------------------------------------------------------------------------------------------------------------------------------------------------------

    // Question 4
    // Write a Java program to determine whether each number in a given array is positive or negative.
    System.out.println("Question 4/");

    int[] numbersQ4 = {10, -21 , 30, 31, -25};

    for(int i = 0 ; i < numbersQ4.length ; i++) {
        if (numbersQ4[i] > 0){
            System.out.println(numbersQ4[i] + " is a positive number");
        }else if ( numbersQ4[i] < 0 ){
            System.out.println(numbersQ4[i] + " is a negative number");
        }
    }

    System.out.println(" ");

// --------------------------------------------------------------------------------------------------------------------------------------------------------------

    // Question 5
    // Write a Java program to find the shortest word in a given array of strings
    System.out.println("Question 5/");

    String [] wordsQ5  = {"Tuwaiq", "Bootcamp", "Student" , "JAVA"};

    String shortest = wordsQ5[0];

    for ( int i = 0 ; i < wordsQ5.length ; i++){
        if ( wordsQ5[i].length() < shortest.length()){
            shortest =  wordsQ5[i];
        }else{
            shortest = shortest;
        }
    }

    System.out.println("The shortest word is : "+ shortest);

}
