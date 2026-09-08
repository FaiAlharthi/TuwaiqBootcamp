//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    // M1W1 Lab1 13 August

    Scanner scanner = new Scanner(System.in);
//
//    1. Question
    System.out.println("Enter first nummber");
    int num1 = scanner.nextInt();

    System.out.println("Enter second nummber");
    int num2 = scanner.nextInt();

    System.out.println( num1 + " + " + num2 + " = " + (num1 + num2));
    System.out.println( num1 + " - " + num2 + " = " + (num1 - num2));
    System.out.println( num1 + " x " + num2 + " = " + (num1 * num2));
    System.out.println( num1 + " / " + num2 + " = " + (num1 / num2));
    System.out.println( num1 + " mod " + num2 + " = " + (num1 % num2));



//    2. Question
    System.out.println("Enter first nummber");
    int numQ2 = scanner.nextInt();

    for(int i = 1 ; i <= 10 ; i++){
        System.out.println( numQ2 + " x " + i + " = " + (numQ2 * i));
    }



//    3. Question
    System.out.println("Enter the circle full radius ");
    Double radius = scanner.nextDouble();

    System.out.println("Preimeter is = " + (2*3.14*radius));
    System.out.println("Area is = " + ( 3.14* (radius * radius )));



//    4. Question
    System.out.println("Enter the count of numbers ");
    int count = scanner.nextInt();
    Double avg = 0.0;

    for(int i = 1 ; i<=count ; i ++){
        System.out.println("Enter an integer : ");
        int integer = scanner.nextInt();
        avg = avg + integer ;
    }
    avg = avg / count ;
    System.out.println("The avderage is : " + avg);



//    5. Question
    System.out.println("Enter first numbeer");
    int num1Q5 = scanner.nextInt();
    System.out.println("Enter second numbeer");
    int num2Q5 = scanner.nextInt();
    System.out.println("Enter third numbeer");
    int num3Q5 = scanner.nextInt();
    Boolean resultQ5 = false;

    int sum = num1Q5 + num2Q5;
    if(sum == num3Q5){
        resultQ5 = true;
        System.out.println("The result is : "+ resultQ5);
    }else{
        resultQ5 = false;
        System.out.println("The result is : "+ resultQ5);
    }



//    6. Question
    System.out.println("Enter a word : ");
    String wordQ6 = scanner.nextLine();
    String reverseWord = "";

    for( int i = wordQ6.length()-1 ; i >= 0 ; i--){
        reverseWord += wordQ6.charAt(i);
    }
    System.out.println("The reversed word is : " + reverseWord);



//    7. Question
    System.out.println("Enter a number");
    int numQ7 = scanner.nextInt();
    if(numQ7 % 2 == 0){
        System.out.println("the number is Even");
    }else {
        System.out.println("the number is Odd");
    }



//    8. Question
    System.out.println("Enter the degree in centigrade :");
    int tempQ8 = scanner.nextInt();
    int fahrenheit = (tempQ8 * 9/5) +32 ;
    System.out.println(" the degree in fahrenheit = " + fahrenheit);



//    9. Question
    System.out.println("write a word");
    String wordQ9 = scanner.nextLine();
    System.out.println("Which index you want to retrieve its character ( enter a number )");
    int indexQ9 = scanner.nextInt();
    System.out.println("the character for the index" + indexQ9 + " in word " + wordQ9 + " is : " + wordQ9.charAt(indexQ9));



//    10. Question
    System.out.println("Enter the height of the rectangle");
    double heightQ10 = scanner.nextDouble();
    System.out.println("Enter the width of the rectangle");
    double widthQ10 = scanner.nextDouble();

    double areaQ10 = heightQ10 * widthQ10;
    double perimeterQ10 = 2 * (heightQ10 + widthQ10);

    System.out.println(" the Area of the rectangle " + areaQ10);
    System.out.println(" the perimeter of the rectangle " + perimeterQ10 );



//    11. Question
    System.out.println("Enter first number");
    int num1Q11 = scanner.nextInt();
    System.out.println("Enter second number");
    int num2Q11 = scanner.nextInt();
    if (num1Q11 != num2Q11){
        System.out.println( num1Q11+" != " + num2Q11);
    }
    if (num1Q11 <= num2Q11) {
        System.out.println( num1Q11+" <= " + num2Q11);
    }
    if (num1Q11 >= num2Q11) {
        System.out.println( num1Q11+" >= " + num2Q11);
    }
    if (num1Q11 > num2Q11) {
        System.out.println( num1Q11+" > " + num2Q11);
    }
    if (num1Q11 < num2Q11) {
        System.out.println( num1Q11+" < " + num2Q11);
    }
    if (num1Q11 == num2Q11) {
        System.out.println( num1Q11+" == " + num2Q11);
    }



//    12. Question
    System.out.println("Enter a second");
    int secondsQ12 = scanner.nextInt();

    int hoursQ12 = secondsQ12 / 3600 ;
    int secRemainder = secondsQ12 % 60 ;
    int minutsQ12 = (secondsQ12 % 3600)/60 ;

    System.out.println( hoursQ12 +" : " + minutsQ12 + " : " + secRemainder);



//    13. Question
    int previousQ13 = 0;
    Boolean equalQ13 = true;

    do{
        System.out.println("Enter first number : ");
        int num1Q13 = scanner.nextInt();
        System.out.println("Enter second number : ");
        int num2Q13 = scanner.nextInt();
        System.out.println("Enter third number : ");
        int num3Q13 = scanner.nextInt();
        System.out.println("Enter fourth number : ");
        int num4Q13 = scanner.nextInt();

        previousQ13= num1Q13;

        for ( int i = 1 ; i <= 4 ; i ++){
            if (previousQ13 != num2Q13 || previousQ13 != num3Q13 || previousQ13 != num4Q13){
                System.out.println("numbers are NOT equal ");
                equalQ13 = false;
                break;
            }else {
                equalQ13 = true;
            }
        }

        if ( equalQ13){
            System.out.println("numbers are equal ");
            break;
        }

    }while (equalQ13);

    // This is Another solution for Question 13.

//    System.out.println("Enter first number : ");
//    int num1Q13 = scanner.nextInt();
//    System.out.println("Enter second number : ");
//    int num2Q13 = scanner.nextInt();
//    System.out.println("Enter third number : ");
//    int num3Q13 = scanner.nextInt();
//    System.out.println("Enter fourth number : ");
//    int num4Q13 = scanner.nextInt();
//
//    if(num1Q13 == num2Q13 && num3Q13 == num4Q13){
//        if(num1Q13 == num3Q13 && num2Q13 == num4Q13){
//            System.out.println("All numbers are equal ");
//        }else{
//            System.out.println("numbers are not equal ");
//        }
//
//    }else{
//        System.out.println("numbers are not equal ");
//    }



//    14. Question
    System.out.println("Enter any number");
    int numQ14 = scanner.nextInt();
    if(numQ14 > 0){
        System.out.println("The number is positive");
    }else if(numQ14 < 0){
        System.out.println("The number is negative");
    }else{
        System.out.println("The number is ZERO");
    }



//    15. Question
    int countPositive = 0;
    int countNegative = 0;
    int countZeros = 0;

    System.out.println("Enter any number, and enter ( -1 ) if you want to exit ");
    int numQ15 = scanner.nextInt();

    while (numQ15 != -1) {

        if (numQ15 > 0) {
            countPositive++;

        } else if (numQ15 < 0) {
            countNegative++;
        } else {
            countZeros++;
        }

        System.out.println("Enter any number, and enter ( -1 ) if you want to exit ");
        numQ15 = scanner.nextInt();
    }

    System.out.println("Number of Positive numbers you entered : " + countPositive);
    System.out.println("Number of Negative numbers you entered : " + countNegative);
    System.out.println("Number of Zeroes  you entered : " + countZeros);



//    16. Question
    System.out.println(" Write a number to see its revers");
    int numQ16 = scanner.nextInt();
    String wordQ16 = Integer.toString(numQ16);
    String reverseQ16 = "";

    for ( int i = wordQ16.length()-1 ; i >= 0 ; i--){
        reverseQ16 += wordQ16.charAt(i);
    }

    System.out.println(" The reversed word : "+ reverseQ16);



//    17. Question
    System.out.println("Enter numbers, if you want to stop write the word ( exit ) ");
    String input = scanner.nextLine();

    int numbQ17 = Integer.parseInt(input);
    int largest = 0;
    int smallest = numbQ17;


    while (!(input.equalsIgnoreCase("exit"))){
        numbQ17 = Integer.parseInt(input);

        int previousNum = numbQ17;
        if( previousNum > largest){
            largest = previousNum;
        }else{
            largest = largest;
        }

        if( previousNum < smallest){
            smallest = previousNum;
        }else{
            smallest=smallest;
        }

        System.out.println("Enter numbers, if you want to stop write the word ( exit ) ");
        input = scanner.nextLine();
    }

    System.out.println("the largest number : " + largest);
    System.out.println("the smallest number : " + smallest);



//    18. Question
    System.out.println("Write a word :");
    String wordQ18 = scanner.nextLine();

    int countQ18 = 0;

    for( int i =0 ; i < wordQ18.length() ; i ++){
        if ( wordQ18.charAt(i) == 'a'){
            countQ18++;
        }
    }

    System.out.println("Number of the letter 'a' appears in word "+ wordQ18 + " is : " + countQ18 + " times");

}
