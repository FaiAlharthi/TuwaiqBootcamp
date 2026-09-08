//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    Scanner scanner = new Scanner (System.in);

    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.
//    1.Write a program that prints the numbers from 1 to 100 such that:
//    If the number is a multiple of 3, you need to print "Fizz" instead of that number.
//    If the number is a multiple of 5, you need to print "Buzz" instead of that number.
//    If the number is a multiple of both 3 and 5, you need to print "FizzBuzz" instead of that number.
    for ( int i= 1 ; i <=100 ; i++){
        if ((i % 3 == 0 ) && ( i % 5 ==0 )){
            System.out.println("FizzBuzz");
        } else if (i % 5 == 0) {
            System.out.println("Buzz");
        } else if ( i % 3 == 0 ) {
            System.out.println("Fizz");
        }else {
            System.out.println(i);
        }
    }



//    2.Write a Java program to reverse a string.
//    Test Data: Input a string: The quick brown fox Expected Output: Reverse string: xof nworb kciuq eht
    System.out.println("write a text : ");
    String text = scanner.nextLine();
    String revers= " ";

    for ( int i = (text.length()-1) ; i >= 0; i--){
        revers += text.charAt(i);
    }
    System.out.println("Reverse string: "+ revers);



//    3.Write a program to find the factorial value of any number entered through the keyboard.
        System.out.println("Enter a number to find its factorial result : ");
        int number3 = scanner.nextInt();

        int multiplication = 1;

        for( int i = 1 ; i <= number3 ; i++){

            multiplication = multiplication * i;
        }
    System.out.println(" number factorial result : " + multiplication);



//    4. Two numbers are entered through the keyboard. Write a program to find the value of one number raised to the power of another. (Do not use Java built-in method)
    System.out.println("Enter first number");
    int num1 = scanner.nextInt();
    System.out.println("Enter second number");
    int num2 = scanner.nextInt();

    int result4 = 0;

    for ( int i = 1 ; i < num2 ; i++){
        result4 = num1 * num1 ;
    }
    System.out.println("The result of " + num1 + " raised to the power "+ num2 + " is : " + result4);



//    5.Write a program that reads a set of integers, and then prints the sum of the even and odd integers.
    System.out.println("Enter multiple numbers, and write 0 if you want to exit ");
    int numberList = scanner.nextInt();

    int evenSum =0;
    int oddSum =0;

    while (numberList !=0){
        if(numberList % 2 == 0 ){
            evenSum += numberList;
        } else {
            oddSum += numberList;
        }
        System.out.println("Enter multiple numbers, and write 0 if you want to exit ");
        numberList = scanner.nextInt();
    }
    System.out.println("The summation of even numbers is : " + evenSum);
    System.out.println("The summation of odd numbers is : " + oddSum);



//    6.Write a program that prompts the user to input a positive integer. It should then output a message indicating whether the number is a prime number.
    System.out.println("Enter a positive number : ");
    int primeNum = scanner.nextInt();
    int count = 0;

    for ( int i = 2 ; i < primeNum ; i++){
        if (primeNum % i == 0 ){
            count++;
            if( count != 0 ){
                System.out.println("this is NOT a prime number ");
                break;
            }else {
                System.out.println("this is a prime number ");
                break;
            }

        }
    }



//    7.Use a for loop to print headings for four weeks (Weeks 1 - 4). Then use another for loop to print the days (Days 1 -7) for each week.
//    Expected Output:
//    Week 1
//    Day 1
//    Day2
//            Day3
//    Day4
//            Day5
//    Day6
//    Day 7
//    Week 2
//    Day 1
//    Day2
    for ( int i = 1 ; i <= 4 ; i++){

        System.out.println("Week "+ i);
        for( int j = 1 ; j <= 7 ; j++){
            System.out.println("Day "+ j);
        }

        System.out.println("     ");

    }



//    8.Write a program thats check if the word is a palindrome or not. hint: A string is said to be a palindrome if it is the same if we start reading it from left to right or right to left.
    System.out.println("Write a word to check if it's palindrome : ");
    String wordQ8 = scanner.nextLine();

    String reverse = "";

    for (int i = wordQ8.length() - 1; i >= 0; i--) {
        reverse += wordQ8.charAt(i);
    }

    if (wordQ8.equalsIgnoreCase(reverse)) {
        System.out.println("It is a palindrome");
    } else {
        System.out.println("It is not a palindrome");
    }

    

}
