//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    Scanner scanner = new Scanner(System.in);

    // Question1
    int[] numbers = new int[3];

    for ( int i =0 ; i <3 ; i++) {
        System.out.println("Enter the " + (i+1) +"s numbers");
        int answerQ1 = scanner.nextInt();
        numbers[i] = answerQ1;
    }
    Smallest(numbers);

    // Question2
    System.out.println("Enter a number : ");
    int numberQ2 = scanner.nextInt();
    NumberType(numberQ2);

    // Question3
    System.out.println("Enter a password : ");
    String passQ3 = scanner.nextLine();
    checkPass(passQ3);

}


// Question1
public static void Smallest ( int numbersQ1[] ){
    int min = numbersQ1[0];

    for(int i = 0; i < numbersQ1.length ; i++){
        if( min > numbersQ1[i]){
            min = numbersQ1[i];
        }else{
            min = min;
        }
    }
    System.out.println("The smallest value is " + min );
}


// Question2
public static void NumberType (int numberQ2){
    if( numberQ2 > 0){
        System.out.println("The number is : ( Positive ) ");
    } else if (numberQ2 < 0) {
        System.out.println("The number is : ( Negative ) ");
    } else {
        System.out.println("The number is : ( ZERO ) ");
    }

    }


// Question3
public static void checkPass (String pass){
    String[] characters = {"!","@","#","$","%","&","*","_","-"," "};
    int countDigits = 0;
    boolean noChar = false;

    for(int i =0; i<characters.length ; i++) {
        if (pass.contains(characters[i]) ){
            System.out.println(" Password should consist of only letters and digits ! ");
            noChar = false;
            break;
        }else {
            noChar = true;
        }
    }

    if ( noChar ){

        if ( pass.length() >= 10){

            for ( int i = 0 ; i < pass.length(); i++){

                if ( ( (int)(pass.charAt(i)) ) >= 48 && ( (int)(pass.charAt(i)) ) <= 57){
                    countDigits++;
                } else{
                    continue;
                }

            }

            if ( countDigits < 2){
                System.out.println(" a Password must have at least 2 Digits ! ");
            }else {
                System.out.println(" your Password is Valid : " + pass);
            }

        }else{
            System.out.println(" a Password must have at least TEN characters ! ");
        }

    }

}
