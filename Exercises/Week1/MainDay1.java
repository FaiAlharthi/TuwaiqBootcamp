
void main() {

    // Day 2 Exercises 10 August

    Scanner scanner = new Scanner (System.in);


//    1. Develop a program that takes the weight (in kilograms) and height (in meters)
//    as input and calculates the BMI, then prints it.
//    • Input: Weight (kg) = 70, Height (m) = 1.75
//    • Expected Output: BMI = 22.86
    System.out.println("Enter your weight in Kilograms : ");
    int weight = scanner.nextInt();

    System.out.println("Enter your height in meters : ");
    float height = scanner.nextFloat();

    float BMI = (weight / (height * height));
    System.out.println(" BMI = " + BMI);



//    2. Write a program that takes the obtained marks and total marks as input and
//    calculates the percentage, then prints it.
//    • Input: Obtained Marks = 85, Total Marks = 100
//    • Expected Output: Percentage = 85.0%
    System.out.println("Enter your Obtained Marks : ");
    int obtainedMarks = scanner.nextInt() * 100;

    System.out.println("Enter your Total Marks : ");
    int totalMarks = scanner.nextInt();

    float result = obtainedMarks / totalMarks ;
    System.out.println("Percentage = " + result + "%");



//    3. Create a program that takes an amount in one currency and an exchange rate
//    as input, then converts and prints the amount in another currency.
//    • Input: Amount in USD = 100, Exchange Rate (USD to EUR) = 0.85
//    • Expected Output: Amount in EUR = 85.0
    System.out.println("Amount in USD = ");
    int amount = scanner.nextInt();

    System.out.println("Exchange Rate (USD to EUR) = ");
    float exchangeRate = scanner.nextFloat();

    System.out.println("Amount in EUR = " + (amount * exchangeRate));



//    4. Create a program that takes a string as input, calculates its length, and then
//    reverses the string using the StringBuilder class, finally printing both the length and
//    reversed string.
//    • Input: "Hello, World!"
//    • Expected Output: Length of the string: 13 And Reversed string: "!dlroW
//            ,olleH"
    System.out.println("Enter a word");
    String word = scanner.nextLine();

    int length = word.length();

    StringBuilder sb = new StringBuilder(word);
    StringBuilder reversedWord = sb.reverse();

    System.out.println("Length of the string is " + length + " characters, And Reversed string is : " + reversedWord);



//    5. Develop a program that takes a sentence as input and extracts a substring from
//    it, then prints the extracted substring.
//    • Input: Sentence = "The quick brown fox jumps over the lazy dog", Start Index
//            = 10, End Index = 20
//    • Expected Output: "brown fox"
    System.out.println("Enter a sentence");
    String sentence = scanner.nextLine();

    int index1 = scanner.nextInt();
    int index2 = scanner.nextInt();

    String subset = sentence.substring(index1,index2);

    System.out.println("the subset is : " + subset );



//    6. Write a program that takes a sentence and a keyword as input, then check if
//    the keyword is present in the sentence and prints the result.
//    • Input: Sentence = "The quick brown fox jumps over the lazy dog", Keyword =
//            "jumps"
//    • Expected Output: Keyword "jumps" is present in the sentence.
    System.out.println("write a sentence");
    String sentenceQ6 = scanner.nextLine();

    System.out.println("enter a word");
    String wordQ6 = scanner.nextLine();

    boolean found = sentenceQ6.contains(wordQ6);

    System.out.println(" Keyword " + wordQ6 + " is present in the sentence ? " + found);



//    7. Develop a program that takes a sentence and a word to replace as input, then
//    replace all occurrences of the word with another word and prints the modified
//    sentence.
//    • Input: Sentence = "The quick brown fox jumps over the lazy dog", Word to
//    Replace = "fox", Replacement Word = "cat"
//    • Expected Output: "The quick brown cat jumps over the lazy dog"
        System.out.println("write a sentence");
        String sentenceQ7 = scanner.nextLine();

        System.out.println("enter a word you want to replace ");
        String wordQ7 = scanner.nextLine();

        System.out.println("enter a word you want to replace ( " + wordQ7 + " ) with ?");
        String replacedWord = scanner.nextLine();

        String alteredSentence = sentenceQ7.replace(wordQ7,replacedWord);

        System.out.println("Result : " + alteredSentence);



//    8. Write a program that takes two strings as input and check if they are equal,
//            ignoring the case, then prints whether they are equal or not.
//    • Input: String 1 = "Hello", String 2 = "hello"
//    • Expected Output: Strings are equal (ignoring case).
        System.out.println("enter first word  ");
        String word1Q8 = scanner.nextLine();

        System.out.println("enter second word  ");
        String word2Q8 = scanner.nextLine();

        System.out.println(" Strings are equal ? " + word1Q8.equalsIgnoreCase(word2Q8));


}
