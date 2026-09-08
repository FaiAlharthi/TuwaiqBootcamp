//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    Scanner scanner = new Scanner(System.in);

    // Question 1
    ArrayList<String> wordsQ1 = new ArrayList<>();
    int largestLengthQ1 = 0;

    System.out.println("Enter a word to a list and Enter ( 0 ) to stop");
    String wordQ1 = scanner.nextLine();

    while( !(wordQ1.equals("0")) ){
        wordsQ1.add(wordQ1);
        System.out.println("Enter a word to a list and Enter ( 0 ) to stop");
        wordQ1 = scanner.nextLine();
    }

    for( String word : wordsQ1){
        if(word.length() > largestLengthQ1 ){
            largestLengthQ1 = word.length();
        }else{
            largestLengthQ1 = largestLengthQ1;
        }
    }
    System.out.println("These are the longest words in the list : ");
    for ( String word : wordsQ1){
        if(word.length() == largestLengthQ1){
            System.out.print(word + ", ");
        }
    }


    // Question 2
    System.out.println("How many numbers do you want to add ?");
    int sizeQ2 = scanner.nextInt();

    int [] numbersQ2 = new int [sizeQ2];

    int counterQ2 =0;

    for(int i = 0; i < numbersQ2.length ; i++){
        System.out.println("Add a number : ");
        int numberQ2 = scanner.nextInt();
        numbersQ2[i]= numberQ2;
    }

    for(int i =0 ; i< numbersQ2.length ; i++){
        counterQ2 =0;
        for ( int j =0 ; j< numbersQ2.length ; j++) {
            if ( numbersQ2 [i] == numbersQ2[j]){
                counterQ2++;
            }
        }
            System.out.println(numbersQ2[i] + " occurs " + counterQ2 + " times");
    }


    // Question 3
    System.out.println("How many numbers do you want to add ?");
    int sizeQ3 = scanner.nextInt();

    int [] numbersQ3 = new int [sizeQ3];
    ArrayList<Integer> resultQ3 = new ArrayList<>();
    ArrayList <Integer> mockingListQ3 = new ArrayList<>();

    for(int i = 0; i < numbersQ3.length ; i++) {
        System.out.println("Add a number : ");
        int numberQ2 = scanner.nextInt();
        numbersQ3[i] = numberQ2;
        mockingListQ3.add(numberQ2);
    }

    System.out.println(" we will find the largest K numbers, What do you want the value of K to be  ");
    int kValueQ3 = scanner.nextInt();

    for (int j = 0; j < kValueQ3; j++) {
        int largestQ3 = mockingListQ3.get(0);

        for (int listNo : mockingListQ3) {
                if (listNo > largestQ3) {
                    largestQ3 = listNo;
                }
        }
            resultQ3.add(largestQ3);
            int index = mockingListQ3.indexOf(largestQ3);
            mockingListQ3.remove(index);
    }

    System.out.println( kValueQ3 + " largest elemetns of the said array are : " + resultQ3);


    // Question 4
    int[] arrayQ4 = {1,2,3,4,5};
    reverseQ4(arrayQ4);


    // Question 5
    System.out.println(" what size do you want the array to be ? ");
    int sizeQ5= scanner.nextInt();

    int [] arrayQ5 = new int[sizeQ5];

    System.out.println(" Choose From The Menu : ( Write the number of your Choice )");
    System.out.println(" 1/ Fill Array ");
    System.out.println(" 2/ Display Array ");
    System.out.println(" 3/ Search for an element in your Array ");
    System.out.println(" 4/ Sort The Array ");
    System.out.println(" 5/ Stop ");
    int ansswerMenuQ5 = scanner.nextInt();

    while (ansswerMenuQ5 != 5) {
        switch (ansswerMenuQ5){
            case 1:
                for(int i = 0 ; i < arrayQ5.length ; i++){
                    System.out.println("Enter array element : ");
                    int element = scanner.nextInt();
                    arrayQ5[i]= element;
                }
                break;

            case 2:
                if(arrayQ5.length != 0 ){
                    System.out.println("This is your array elements : ");
                    for(int i = 0 ; i < arrayQ5.length ; i++){
                        System.out.print(arrayQ5[i] + " ");
                    }
                }else{
                    System.out.println("The array is empty");
                }
                System.out.println("  ");
                break;

            case 3:
                if(arrayQ5.length != 0 ){
                    System.out.println(" Enter a number you want to search for : ");
                    int searchedNo = scanner.nextInt();
                    for(int i = 0 ; i < arrayQ5.length ; i++){
                        if(arrayQ5[i] == searchedNo){
                            System.out.println("The index of the number "+searchedNo+ " is : "+ i);
                        }
                    }
                }else{
                    System.out.println("The array is empty");
                }
                break;

            case 4:
                if(arrayQ5.length != 0 ){
                    System.out.println(" Array after sorting ");
                    Arrays.sort(arrayQ5);
                    for(int i = 0 ; i < arrayQ5.length ; i++){
                        System.out.print(arrayQ5[i] + " ");
                    }
                }else{
                    System.out.println("The array is empty");
                }
                System.out.println(" ");
                break;
        }
        System.out.println(" Choose From The Menu : ( Write the number of your Choice )");
        System.out.println(" 1/ Fill Array ");
        System.out.println(" 2/ Display Array ");
        System.out.println(" 3/ Search for an element in your Array ");
        System.out.println(" 4/ Sort The Array ");
        System.out.println(" 5/ Stop ");
        ansswerMenuQ5 = scanner.nextInt();

    }


    // Question 6
    System.out.println("Enter the minimum of the range : ");
    int min = scanner.nextInt();
    System.out.println("Enter the maximum of the range : ");
    int max = scanner.nextInt();
    System.out.println("Enter the number of random numbers to generate : ");
    int numberQ6 = scanner.nextInt();

    System.out.println("numbers within the specified range : ");
    for(int i = 0 ; i<numberQ6 ; i++){
        generateRandom(min,max);
    }


    // Question 7
    System.out.println("Enter a Password : ");
    String passQ7 = scanner.nextLine();
    checkPass(passQ7);

//    // Question 8
    System.out.println("Enter the number of Fibonacci  : ");
    int numberQ8 = scanner.nextInt();
    Fibonacci(numberQ8);


}

// Question 4
public static void reverseQ4 ( int[] array){
    System.out.println("Reversed Array : ");
    for( int i = array.length -1 ; i >=0 ; i --){
        System.out.print(array[i] + " ");
    }
}

// Question 6
public static void generateRandom (int min, int max){
    Random random = new Random();
    int randomNo = random.nextInt(max - min + 1) + min;
    System.out.print(randomNo + " ");
}

// Question 7
public static void checkPass ( String pass ){

    int lengthScore = checkLength(pass);
    int charScore = checkSpecialChars(pass);
    int letterScore = checkUpperAndLower(pass);

    int totalScore = (lengthScore+charScore+letterScore);

    if(totalScore >= 8 ){
        System.out.print("Password is Strong ! ");
    } else if (totalScore >=5 ) {
        System.out.print("Password is Moderately Strong ! ");
    } else{
        System.out.print("Password is WEAK ! ");
    }


}

public static int checkLength(String pass) {
    int lengthScore =0;

    if (  pass.length() >= 6 && pass.length() <= 7) {
        lengthScore = 2;
    }else if ( pass.length() >= 8  ) {
        lengthScore = 3;
    }

    return lengthScore;
}

public static int checkSpecialChars(String pass){
    int charScore =0;
    String[] chars = {"!","#","$","%","*","-","_","?","/","@"};
    for(int i = 0 ; i< chars.length ; i++){
        if (pass.contains(chars[i])){
            charScore = 2;
        }
    }
    return charScore;
}

public static int checkUpperAndLower(String pass){
    int letterScore =0;
    boolean upper = false;
    boolean lower = false;

    for ( int i = 0 ; i < pass.length() ; i++){
        char charPass = pass.charAt(i);

        if(Character.isUpperCase(charPass)){
            upper = true;
        }
        if (Character.isLowerCase(charPass)){
            lower = true;
            letterScore =2;
        }
    }

    if(upper && lower){
        letterScore =2;
    }

    return letterScore;
}

// Question 8
public static void Fibonacci(int fib){
    int num1 = 0;
    int num2 = 1;
    System.out.println("Fibonacci sequence with " +fib  +" terms : ");

    for(int i = 0 ; i < fib ; i++){
        System.out.print(num1 + " ");
        int num3 = num1 + num2;
        num1 = num2;
        num2 = num3;
    }

}


