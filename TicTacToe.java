package Game;

import java.net.SocketPermission;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static void main(String[] args){


        char[][] board = {  {' ', '|', ' ', '|', ' '},
                            {'-', '+', '-', '+', '-'},
                            {' ', '|', ' ', '|', ' '},
                            {'-', '+', '-', '+', '-'},
                            {' ', '|', ' ', '|', ' '}
                                };
        printBoard(board);

        
        String result = "";
        while((result != "player")||(result != "cpu")){

          


            Scanner scn =  new Scanner(System.in);
            System.out.println("Enter your next move[1-9]: ");

            int playerPos = scn.nextInt();
            while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)){
                System.out.println("Position already taken! Enter a correct psition");
                playerPos = scn.nextInt();
            }

            placePiece(board, playerPos, "player");

            result = checkWinner();
            if(result.length()>0){
                if(result == "player"){
                    printBoard(board);
                    System.out.println("You won");
                    break;
                } 
                else if(result == "cpu"){
                    printBoard(board);
                    System.out.println("oops! CPU wins!");
                    break;
                }
                else if(result == "tie"){
                    printBoard(board);
                    System.out.println("It's A Tie!");
                    break;
                }

            }


            if(playerPositions.size() + cpuPositions.size() != 9){
                Random rand = new Random();
                int cpuPos = rand.nextInt(9)+1;
                while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)){
                    cpuPos = rand.nextInt(9)+1;
                }
                placePiece(board, cpuPos, "cpu");
            }
            result = checkWinner();
            if(result.length()>0){
                if(result == "player"){
                    printBoard(board);
                    System.out.println("You won");
                    break;
                } 
                else if(result == "cpu"){
                    printBoard(board);
                    System.out.println("oops! CPU wins!");
                    break;
                }
                else if(result == "tie"){
                    printBoard(board);
                    System.out.println("It's A Tie!");
                    break;
                }

            }

                                    
            printBoard(board);


        }

        
        
    }


        public static void printBoard(char[][] board){
            
            for(char[] row: board ){
                for(char col: row){
                    System.out.print(col); 
                }
                System.out.println();
            }
        }

        public static void placePiece(char[][] board, int pos, String user){
            
            char symbol = ' ';

                if(user.equals("player")){
                    symbol = 'X';
                    playerPositions.add(pos);
                }
                if(user.equals("cpu")){
                    symbol = 'O';
                    cpuPositions.add(pos);
                }

                switch(pos) {
                case 1:
                    board[0][0] = symbol;
                    break;
                case 2:
                    board[0][2] = symbol;
                    break;
                case 3:
                    board[0][4] = symbol;
                    break;
                case 4:
                    board[2][0] = symbol;
                    break; 
                case 5:
                    board[2][2] = symbol;
                    break; 
                case 6:
                    board[2][4] = symbol;
                    break;
                case 7:
                    board[4][0] = symbol;
                    break;
                case 8:
                    board[4][2] = symbol ;
                    break; 
                case 9:
                    board[4][4] = symbol;
                    break; 
                default:
                    break;
    
            }
        }

    
    public static String checkWinner(){
        List topRow =  Arrays.asList(1, 2, 3);
        List midRow =  Arrays.asList(4, 5, 6);
        List botRow =  Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol =  Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(3, 5, 7);
        

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

        for(List l : winning){
            if(playerPositions.containsAll(l)){
                return "player";
            } 
            else if(cpuPositions.containsAll(l)){
                return "cpu";
            } 
            else if(playerPositions.size() + cpuPositions.size() == 9){
                return "tie";
            }

        }
        return "";
    }

}
