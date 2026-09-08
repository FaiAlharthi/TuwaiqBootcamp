//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    Scanner scanner = new Scanner(System.in);


    // Question 1
    System.out.println("How many numbers do you want to enter?");
    int listLengthQ1 = scanner.nextInt();

    int[] numbersQ1 = new int[listLengthQ1];

    for (int i = 0; i < numbersQ1.length; i++) {
        System.out.println("Enter number : ");
        int item = scanner.nextInt();
        numbersQ1[i] = item;
    }

    int firstQ1 = numbersQ1[0];
    int lastQ2 = numbersQ1[(numbersQ1.length - 1)];

    boolean equal = false;

    if (firstQ1 == lastQ2)
        equal = true;

    System.out.println(equal);


    // Question 2
    System.out.println("How many numbers do you want to enter?");
    int listLengthQ2 = scanner.nextInt();

    int[] numbersQ2 = new int[listLengthQ2];
    ArrayList<String> result = new ArrayList<String>();

    for (int i = 0; i < numbersQ2.length; i++) {
        System.out.println("Enter number : ");
        int item = scanner.nextInt();
        numbersQ2[i] = item;
    }

    int sum = 0;
    double avg = 0.0;

    for (int i = 0; i < numbersQ2.length; i++) {
        sum = sum + numbersQ2[i];
    }
    avg = sum / numbersQ2.length;
    System.out.println("the average is : " + avg);

    for (int i = 0; i < numbersQ2.length; i++) {
        if (numbersQ2[i] > avg) {
            System.out.println("The numbers are greater than the average are : " + numbersQ2[i]);
        }
    }


    // Question 3
    System.out.println("How many numbers do you want to enter?");
    int listLength = scanner.nextInt();

    int[] numbersQ3 = new int[listLength];

    for (int i = 0; i < numbersQ3.length; i++) {
        System.out.println("Enter number : ");
        int item = scanner.nextInt();
        numbersQ3[i] = item;
    }

    int firstQ3 = numbersQ3[0];
    int lastQ3 = numbersQ3[numbersQ3.length - 1];

    if (firstQ3 > lastQ3) {
        System.out.println("Larger value between first and last element : " + firstQ3);
    } else if (lastQ3 > firstQ3) {
        System.out.println("Larger value between first and last element : " + lastQ3);
    }


    // Question 4
    System.out.println("How many numbers do you want to enter?");
    int listLengthQ4 = scanner.nextInt();

    int[] NumbersQ4 = new int[listLengthQ4];

    for (int i = 0; i < NumbersQ4.length; i++) {
        System.out.println("Enter number : ");
        int item = scanner.nextInt();
        NumbersQ4[i] = item;
    }

    int firstQ4 = NumbersQ4[0];
    int lastQ4 = NumbersQ4[NumbersQ4.length - 1];

    for (int i = 0; i < NumbersQ4.length; i++) {
        if (i == 0) {
            NumbersQ4[i] = lastQ4;
        } else if (i == (NumbersQ4.length - 1)) {
            NumbersQ4[NumbersQ4.length - 1] = firstQ4;
        } else {
            NumbersQ4[i] = NumbersQ4[i];
        }
    }

    System.out.println("New Array after swapping : ");
    for (int i = 0; i < NumbersQ4.length; i++) {
        System.out.println(NumbersQ4[i]);
    }


    // Question 5
    ArrayList<Integer> numbersQ5 = new ArrayList<Integer>();
    ArrayList<Integer> oddNumberQ5 = new ArrayList<Integer>();
    ArrayList<Integer> evenNumberQ5 = new ArrayList<Integer>();

    System.out.println("Enter the numbers for your list, and write ( exit ) to stop");
    String responseQ5 = scanner.nextLine();
    int listNumber;

    while (!(responseQ5.equalsIgnoreCase("exit"))) {
        listNumber = Integer.parseInt(responseQ5);
        numbersQ5.add(listNumber);

        System.out.println("Enter the numbers for your list, and write ( exit ) to stop");
        responseQ5 = scanner.nextLine();
    }

    for (int element : numbersQ5) {
        if (element % 2 == 0) {
            evenNumberQ5.add(element);
        } else {
            oddNumberQ5.add(element);
        }
    }

    numbersQ5 = oddNumberQ5;
    numbersQ5.addAll(evenNumberQ5);
    System.out.println("Final list : " + numbersQ5);


    // Question 6
    ArrayList<Integer> fistNumbersQ6 = new ArrayList<Integer>();
    ArrayList<Integer> secNumbersQ6 = new ArrayList<Integer>();

    Boolean equalQ6 = false;

    System.out.println("Enter the numbers for your First list, and write ( exit ) to stop");
    String responseQ6 = scanner.nextLine();
    int listNumberQ6;

    while (!(responseQ6.equalsIgnoreCase("exit"))) {
        listNumberQ6 = Integer.parseInt(responseQ6);
        fistNumbersQ6.add(listNumberQ6);

        System.out.println("Enter the numbers for your list, and write ( exit ) to stop");
        responseQ6 = scanner.nextLine();
    }


    for (int i = 0; i < fistNumbersQ6.size(); i++) {
        System.out.println("Enter the numbers for your seconde list : ");
        int response2Q6 = scanner.nextInt();
        secNumbersQ6.add(response2Q6);
    }

    for (int i = 0; i < fistNumbersQ6.size(); i++) {
        if (fistNumbersQ6.get(i) == secNumbersQ6.get(i)) {
            equalQ6 = true;
        } else {
            equalQ6 = false;
        }
    }

    System.out.println("Are lists equal to each other ? " + equalQ6);

}




