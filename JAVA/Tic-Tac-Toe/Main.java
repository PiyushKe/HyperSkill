package tictactoe;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String game = "_________";
        printGame(game);
        char[] start = {'X', 'O'};
        int k = 0;
        while(true) {
            System.out.print("Enter the coordinates:");
            String move = sc.nextLine();
            while(moveCheck(move,game) == -1) {
                System.out.print("Enter the coordinates:");
                move = sc.nextLine();
            }
            game = String.format("%s%c%s",game.substring(0,indexOf(move)),start[k % 2],game.substring(indexOf(move) + 1,9));
            k++;
            printGame(game);
                 int xCount = 0;
                 int oCount = 0;
                 for (char i : game.toCharArray()) {
                     if (i == 'X') {
                         xCount++;
                     } else if (i == 'O') {
                         oCount++;
                     }
                 }
                 if (Math.abs(xCount - oCount) > 1) {
                     System.out.print("Impossible");
                     System.exit(0);
                 }
                 if (rowCheck(game) != '_') {
                     System.out.printf("%c wins",rowCheck(game));
                     break;
                 } else if (columnCheck(game) != '_') {
                     System.out.printf("%c wins",columnCheck(game));
                     break;
                 } else if (positiveSlopeDiagonalCheck(game) != '_') {
                     System.out.printf("%c wins",positiveSlopeDiagonalCheck(game));
                     break;
                 } else if (negativeSlopeDiagonalCheck(game) != '_') {
                     System.out.printf("%c wins",negativeSlopeDiagonalCheck(game));
                     break;
                 }
//                 else {
//                     System.out.print("Draw");
//                     break;
//                 }
        }
    }
    private static int moveCheck(String move, String game) {
        if(move.matches(".*[a-zA-Z]+.*")) {
            System.out.println("You should enter numbers!");
            return -1;
        } else if(Integer.parseInt(String.valueOf(move.charAt(0))) > 3 || Integer.parseInt(String.valueOf(move.charAt(2))) > 3) {
            System.out.println("Coordinates should be from 1 to 3!");
            return -1;
        } else if(game.charAt(indexOf(move)) != '_') {
            System.out.println("This cell is occupied! Choose another one!");
            return -1;
        }
        return 1;
    }
    private static int indexOf(String move) {
        String[] cases = {"1 3", "2 3", "3 3", "1 2", "2 2", "3 2", "1 1", "2 1", "3 1"};
        int i = 0;
        for (String j : cases) {
            if (j.equals(move)) {
                return i;
            }
            ++i;
        }
        return -1;
    }

    private static void printGame(String game) {
        System.out.println("---------");
        for (int i = 0; i < game.length(); i+=3) {
            System.out.printf("| %c %c %c |\n",game.charAt(i),game.charAt(i + 1),game.charAt(i + 2));
        }
        System.out.println("---------");
    }

     private static char rowCheck(String game) {
         boolean flag = false;
         char symbol = '_';
         for(int i = 0; i < game.length(); i += 3) {
             if(game.startsWith("XXX", i) || game.startsWith("OOO", i)) {
                 if(flag) {
                     System.out.print("Impossible");
                     System.exit(0);
                 }
                 flag = true;
                 symbol = game.charAt(i);
             }
         }
         return symbol;
     }
     private static char columnCheck(String game) {
         boolean flag = false;
         char symbol = '_';
         for(int i = 0; i < 3; ++i) {
             String check = String.format("%c%c%c",game.charAt(i),game.charAt(i + 3),game.charAt(i + 6));
             if(check.equals("XXX") || check.equals("OOO")) {
                 if(flag) {
                     System.out.print("Impossible");
                     System.exit(0);
                 }
                 flag = true;
                 symbol = game.charAt(i);
             }
         }
         return symbol;
     }
     private static char positiveSlopeDiagonalCheck(String game) {
         String check = String.format("%c%c%c",game.charAt(2),game.charAt(4),game.charAt(6));
         char symbol = '_';
         if(check.equals("XXX") || check.equals("OOO")) {
             symbol = game.charAt(4);
         }
         return symbol;
     }
     private static char negativeSlopeDiagonalCheck(String game) {
         String check = String.format("%c%c%c",game.charAt(0),game.charAt(4),game.charAt(8));
         char symbol = '_';
         if(check.equals("XXX") || check.equals("OOO")) {
             symbol = game.charAt(4);
         }
         return symbol;
     }
}
