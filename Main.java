import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static char[][] board = {{'1','2','3'}
            ,{'4','5','6'}
            ,{'7','8','9'}};
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("Welcome to tic tac toe!");
        // i added playing with friend so the program not only against a computer
        System.out.println("Choose a mode: \n1. Play with friend. \n2. Play with bot.");
        int mode = 0;
        while (true) {
            try { // i also added try and catch so the user doesnt make error or chose something isnt right
                mode = input.nextInt();
                if (mode == 1 || mode == 2) {
                    break;
                } else {
                    System.out.println("please choose only from the numbers above.");
                }
            } catch(InputMismatchException e){
            System.out.println("please choose only from the numbers above.");
            }
        }
        // i also added if the player want to play more than one round
        System.out.println("Choose how many round you want: \n1. 1 round \n2. best of 3 \n3. best of 5");
        int roundChoice = 0;
        while (true) {
            try {
                roundChoice = input.nextInt();
                if (roundChoice == 1 || roundChoice == 2 || roundChoice == 3) {
                    break;
                } else {
                    System.out.println("please choose only from the numbers above.");
                }
            } catch(InputMismatchException e){
                System.out.println("please choose only from the numbers above.");
            }
        }

        int rounds =0, roundsToWin =0;
        switch (roundChoice){
            case 1:
                rounds = 1;
                roundsToWin =1;
                break;
            case 2:
                rounds = 3;
                roundsToWin = 2;
                break;
            case 3:
                rounds = 5;
                roundsToWin =3;
                break;
            default:
                System.out.println("please choose one of the above only");
                break;
        }

        int xWins =0, oWins =0;
        for (int i = 1; i <=rounds; i++) {
            char turn = 'x';
            int turns =0;
            boolean gameOver = true;

            while (gameOver) {
                displayBoard();
                int choice = 0;
                switch (mode) {
                    case 1:
                        System.out.println("Player ( " + turn + " ) choose: ");
                        while (true) {
                            try {
                                choice = input.nextInt();
                                if (choice < 1 || choice > 9){
                                    System.out.println("please enter numbers from 1 to 9 only");
                                } else {
                                break;
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("please enter numbers from 1 to 9 only");
                            }
                        }
                        break;
                    case 2:
                        if (turn == 'o') {
                            choice = rand.nextInt(1, 10);
                            System.out.println("bot chose: " + choice);
                        } else {
                            while (true) {
                                System.out.println("Player ( " + turn + " ) choose: ");
                                try {
                                    choice = input.nextInt();
                                    if (choice < 1 || choice > 9) {
                                        System.out.println("please enter numbers from 1 to 9 only");
                                    } else {
                                        break;
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("please enter numbers from 1 to 9 only");
                                }
                            }
                        }
                        break;
                    default:
                        System.out.println("invalid mode. ");
                }

                if (isThere(choice)) {
                    switch (choice) {
                        case 1:
                            board[0][0] = turn;
                            break;
                        case 2:
                            board[0][1] = turn;
                            break;
                        case 3:
                            board[0][2] = turn;
                            break;
                        case 4:
                            board[1][0] = turn;
                            break;
                        case 5:
                            board[1][1] = turn;
                            break;
                        case 6:
                            board[1][2] = turn;
                            break;
                        case 7:
                            board[2][0] = turn;
                            break;
                        case 8:
                            board[2][1] = turn;
                            break;
                        case 9:
                            board[2][2] = turn;
                            break;
                        default:
                            System.out.println("invalid input");
                            break;
                    }
                    turns++;
                } else {
                    System.out.println("this place is taken try again");
                    continue;
                }

                if (checkWinner(turn)) {
                    displayBoard();
                    System.out.println("Player " + turn + " wins the round!");
                    gameOver = false;
                    if (turn == 'x') {
                        xWins++;
                    }
                    else{
                        oWins++;
                    }
                } else if (turns == 9){
                    displayBoard();
                    System.out.println("its a draw");
                    gameOver = false;
                }else {
                    if (turn == 'x') {
                        turn = 'o';
                    } else {
                        turn = 'x';
                    }
                }

            }
            System.out.println("Score x " + xWins +"-"+ oWins + " o " );
            reset();
            if (xWins == roundsToWin){
                System.out.println("Congrats player X win!");
                break;
            } else if (oWins == roundsToWin){
                System.out.println("Congrats player O win!");
                break;
            }
        }
    }
    public static void displayBoard(){ // this method to display the board
        System.out.println();
        System.out.println(" " + board[0][0] + " | " + board[0][1] + " | " + board[0][2]);
        System.out.println("---|---|---");
        System.out.println(" " + board[1][0] + " | " + board[1][1] + " | " + board[1][2]);
        System.out.println("---|---|---");
        System.out.println(" " + board[2][0] + " | " + board[2][1] + " | " + board[2][2]);
    }
    public static boolean checkWinner(char turn){ // this method to check for the winner
        for (int r = 0; r <3; r++){
            if (board[r][0] == turn && board[r][1] == turn && board[r][2] == turn){
                return true;
            }
        }

        for (int c = 0; c<3; c++){
            if (board[0][c] == turn && board[1][c] == turn && board[2][c] == turn){
                return true;
            }
        }
        if (board[0][0] == turn && board[1][1] == turn && board[2][2] == turn){
            return true;
        }
        if (board[0][2] == turn && board[1][1] == turn && board[2][0] == turn){
            return true;
        }
        return false;
    }
    public static boolean isThere(int choice){ // this method to check if the place is empty in the board
        switch (choice) {
            case 1:
                if (board[0][0] == 'x' || board[0][0] == 'o') {
                    return false;
                }
                break;
            case 2:
                if (board[0][1] == 'x' || board[0][1] == 'o') {
                    return false;
                }
                break;
            case 3:
                if (board[0][2] == 'x' || board[0][2] == 'o') {
                    return false;
                }
                break;
            case 4:
                if (board[1][0] == 'x' || board[1][0] == 'o') {
                    return false;
                }
                break;
            case 5:
                if (board[1][1] == 'x' || board[1][1] == 'o') {
                    return false;
                }
                break;
            case 6:
                if (board[1][2] == 'x' || board[1][2] == 'o') {
                    return false;
                }
                break;
            case 7:
                if (board[2][0] == 'x' || board[2][0] == 'o') {
                    return false;
                }
                break;
            case 8:
                if (board[2][1] == 'x' || board[2][1] == 'o') {
                    return false;
                }
                break;
            case 9:
                if (board[2][2] == 'x' || board[2][2] == 'o') {
                    return false;
                }
                break;
            default:
                return false;
        }
        return true;
        }
    public static void reset(){ // this method to clean the board after each round
        char place = '1';
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                board[i][j] = place++;
            }
            }
        }

    }


