import javax.swing.*;
import java.sql.Struct;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

void main() {
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    char[][] grid = {{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}};
    char playerChar = '-';
    char robotChar = '-';
    int countMoves = 0;

    int countRounds =0 ;

    int min = 1;
    int max = 9;

    int countUserWinnings =0;
    int countRobotWinnings =0;

    System.out.println("  ");
    System.out.println("                                                             Let's Play TIC-TAC-TOE !                          ");


    do {
        System.out.println("Do you Want to Play with ( X ) or ( O ) ?");
        playerChar = scanner.next().charAt(0);
        playerChar = Character.toUpperCase(playerChar);

        if (playerChar == 'O') {
            robotChar = 'X';
            break;
        } else if (playerChar == 'X') {
            robotChar = 'O';
            break;
        }

    } while (!(isLegal(playerChar)));

    System.out.println("                                                               You Will Play With : " + playerChar);
    System.out.println("                                                             The Robot Will play with : " + robotChar);
    System.out.println("            ___________________________________________________________________________________________________________________________");
    System.out.println("  ");


    do {
        try {
            System.out.println("Do you Want to Play 1 Round or 3 Rounds ? ");
            scanner.nextLine();
            countRounds = scanner.nextInt();

            if(countRounds != 1 && countRounds !=3){
                throw new Exception();
            }
            countRounds = countRounds;

        } catch (InputMismatchException e) {
            System.out.println("Please Enter Numbers only ");
        } catch (Exception e) {
            System.out.println("Please Enter Either 1 or 3 ");
        }
    }while (countRounds != 1 && countRounds != 3);


    for ( int round= 0; round < countRounds ; round ++) {
        System.out.println("            ----------------------------------------------------------- ROUND " + ( round + 1 ) + " --------------------------------------------------------");
        System.out.println("  ");
        countMoves = 0;
        do {
            try {
                displayGrid(grid);
                System.out.println("                                     ");

                System.out.println("                                              Choose the number that represents the place you want to play in                                         ");
                scanner.nextLine();
                int place = scanner.nextInt();

                if (isAvailable(grid, place)) {
                    addToGrid(grid, playerChar, place);
                    if (checkWinner(grid) != '0') {
                        countUserWinnings++;
                        displayGrid(grid);
                        break;
                    }
                    countMoves++;


                    do {
                        place = random.nextInt(max - min + 1) + min;

                        try {
                            if (isAvailable(grid, place) != true) {
                            } else {
                                if (addToGrid(grid, robotChar, place)) {
                                    countMoves++;

                                    System.out.println("                                              ROBOT Chose to play in : " + place);
                                    System.out.println("     ");
                                    if (checkWinner(grid) != '0') {
                                        countRobotWinnings++;
                                        displayGrid(grid);
                                        break;
                                    }
                                    break;
                                }
                            }
                        } catch (Exception e) {
                            continue;
                        }

                    } while (true);

                }


            } catch (InputMismatchException e) {
                System.out.println("Please Enter numbers only");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } while (((checkWinner(grid) == '0') && countMoves < 9));

        System.out.println("     ");
        System.out.println("                                                                 YOU: " + countUserWinnings + " -VS- "+" ROBOT: " + countRobotWinnings);
        System.out.println("     ");
        resetGrid(grid);
    }
    String winner =( countUserWinnings > countRobotWinnings ? "YOU" : "ROBOT");
        System.out.print("                                                                 The Winner IS : " + winner + " !!");


}//end of main


//method to check if someone wins !
public static char checkWinner(char grid[][]) {
    boolean exist = true;
    char currentChar = '0';

    //check horizontally
    for (int row = 0; row < grid.length; row++) {
        exist = true;
        for (int col = 0; col < grid[row].length; col++) {
            if (col + 1 <= (grid.length - 1)) {
                if ((grid[row][col]) != grid[row][col + 1]) {
                    exist = false;
                    break;
                }
            }
        }
        if (exist) {
            currentChar = grid[row][0];
            return currentChar;
        }
    }

    //check vertically
    for (int col = 0; col < grid.length; col++) {
        exist = true;
        for (int row = 0; row < grid.length; row++) {
            if (row + 1 <= (grid.length - 1)) {
                if (grid[row][col] != grid[row + 1][col]) {
                    exist = false;
                    break;
                }
            }

        }

        if (exist) {
            currentChar = grid[0][col];
            return currentChar;
        }
    }


    //check diagonally
    for (int row = 0; row < grid.length; row++) {
        exist = true;
        currentChar = grid[0][0];
        if (row <= (grid.length - 1)) {
            if (currentChar == grid[row][row]) {
                exist = true;
            } else {
                exist = false;
                break;
            }
        }
    }
    if (exist) {
        return currentChar;
    }

//    negative diagonal
    for (char[] row : grid) {
        exist = true;
        char char1 = filledWith(grid, '3');
        char char2 = filledWith(grid, '5');
        char char3 = filledWith(grid, '7');

        if ((char1 == char2) && (char2 == char3)) {
            exist = true;
            currentChar = char1;

        } else {
            exist = false;
            break;
        }
    }

    if (exist != false) {
        return currentChar;
    }

    return '0';
}


//method to check if the place is Available ( valid number & Empty )
public static boolean isAvailable(char grid[][], int place) throws Exception {
    if (place >= 1 && place <= 9) {
        char newPlace = (char) ('0' + place);
        char content = filledWith(grid, newPlace);

        if (content == 'O' || content == 'X') {
            throw new Exception(" This place is filled !");
        } else {
            return true;
        }
    } else {
        throw new Exception(" Please enter numbers only from 1 to 9 ");
    }

}

//method to check the entered char wiether it's X or O
public static boolean isLegal(char input) {
    try {
        if (input != 'O' || input != 'X') {
            throw new Exception("Please only choose X or O ");
        } else {
            return true;
        }
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return false;
}

// method Display Grid for printing the GameBoard
public static char[][] displayGrid(char grid[][]) {

    for (int row = 0; row < grid.length; row++) {
        System.out.print("                                                                      ");
        for (int col = 0; col < grid[row].length; col++) {
            System.out.print(grid[row][col]);
            if (col < 2) System.out.print(" | ");
        }
        System.out.println();
        if (row < 2) System.out.println("                                                                     ------------  ");
    }

    return grid;
}

//method to assign default values to the grid
public static char[][] resetGrid(char grid[][]){
    int index =1;
    for ( int row = 0 ; row< grid.length ; row++){
        for( int col=0; col < grid[row].length; col++){
            grid[row][col]= (char)('0'+(index));
            index++;
        }
    }
    return grid;
}


//method to return the grid index letter
public static char filledWith(char grid[][], char place) {
    switch (place) {
        case '1':
            return grid[0][0];
        case '2':
            return grid[0][1];
        case '3':
            return grid[0][2];
        case '4':
            return grid[1][0];
        case '5':
            return grid[1][1];
        case '6':
            return grid[1][2];
        case '7':
            return grid[2][0];
        case '8':
            return grid[2][1];
        case '9':
            return grid[2][2];
    }
    return '0';
}

//method to add items to the grid
public static boolean addToGrid(char grid[][], char choice, int place) {

    switch (place) {
        case 1:
            grid[0][0] = choice;
            return true;
        case 2:
            grid[0][1] = choice;
            return true;
        case 3:
            grid[0][2] = choice;
            return true;
        case 4:
            grid[1][0] = choice;
            return true;
        case 5:
            grid[1][1] = choice;
            return true;
        case 6:
            grid[1][2] = choice;
            return true;
        case 7:
            grid[2][0] = choice;
            return true;
        case 8:
            grid[2][1] = choice;
            return true;
        case 9:
            grid[2][2] = choice;
            return true;
    }

    return false;
}


