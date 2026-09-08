//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.

    Scanner scanner = new Scanner (System.in);

//    Exercise (Conditional) 11 August Day 3

//    1.Write a program that checks the role of the user
//    If the role is admin print "welcome admin"
//    If the role is superuser print "welcome superuser"
//    If the role is user print "welcome user" (tip:
//    use if else)
    System.out.println("what's your role ? either ( admin OR superuser OR user )");
    String role = scanner.nextLine();

    if (role.equalsIgnoreCase("admin")){
        System.out.println("welcome admin");
    }
    else if (role.equalsIgnoreCase("superuser")){
        System.out.println("welcome superuser");
    }
    else if (role.equalsIgnoreCase("user")){
        System.out.println("welcome user");
    }



//    2.Take three numbers from the user and print the greatest number.
//            Test Data
//    Input the 1st number: 25
//    Input the 2nd number: 78
//    Input the 3rd number: 87
//    Expected Output : The
//    greatest: 87
    System.out.println("Enter first number");
    int num1 = scanner.nextInt();
    System.out.println("Enter second number");
    int num2 = scanner.nextInt();
    System.out.println("Enter third number");
    int num3 = scanner.nextInt();

    if (num1 > num2){
        if(num1 > num3){
            System.out.println("greatest : " + num1);
        }
    }
    else{
        if (num2 > num3){
            System.out.println("greatest : " + num2);
        }
        else {
            System.out.println("greatest : " + num3);
        }
    }



//    3.Write a Java program that generates an integer between 1 and 7
//    and displays the name of the weekday.
//            Test Data
//    number: 4
//    Expected Output:
//    Wednesday
//    1
    Random random = new Random();
    int randomNumber = random.nextInt(7)+1;

    System.out.println("the selected number is : " + randomNumber);

    switch (randomNumber){
        case 1:
            System.out.println("Sunday");
            break;
        case 2:
            System.out.println("Monday");
            break;
        case 3:
            System.out.println("Tuesday");
            break;
        case 4:
            System.out.println("Wednesday");
            break;
        case 5:
            System.out.println("Thursday");
            break;
        case 6:
            System.out.println("Friday");
            break;
        case 7:
            System.out.println("Saturday");
            break;
    }



//    4. Write a program that takes a numeric score as input and prints
//    the corresponding letter grade using the following grading scale:
//    A: 90-100
//    B: 80-89
//    C: 70-79
//    D: 60-69 F:
//    0-59
//    Enter your numeric score: 85
//    Numeric Score: 85
//    Letter Grade: B
    System.out.println("Enter your Score : ");
    int score = scanner.nextInt();

    if(score >= 90 && score <= 100){
        System.out.println("Letter Grade: A ");
    } else if (score >= 80 && score <= 89) {
        System.out.println("Letter Grade: B ");
    } else if (score >= 70 && score <= 79) {
        System.out.println("Letter Grade: C ");
    } else if (score >= 60 && score <= 69) {
        System.out.println("Letter Grade: D ");
    } else {
        System.out.println("Letter Grade: F ");
    }



//    5. Write a Java program that takes a person's age as input and
//    categorizes them into one of three age categories: "Child,"
//    "Teenager," or "Adult" using an if statement.
//            use an if statement to categorize the age based on the following criteria:
//• If the age is less than 13, categorize them as a "Child."
//• If the age is between 13 and 19 (inclusive), categorize them as a
//    "Teenager."
//• If the age is 20 or older, categorize them as an "Adult."
//    Sample Output:
//    Enter your age: 25
//    You are an Adult.
    System.out.println("Enter your Age : " );
    int age = scanner.nextInt();

    String ageCategory = "non";

    if ( age < 13){
        ageCategory = "Child";
    }
    else if (age >= 13 && age <= 19) {
        ageCategory = "Teenager";
    }
    else if (age >= 20) {
        ageCategory = "Adult";
    }

    System.out.println(" You are a "+ ageCategory);

}


