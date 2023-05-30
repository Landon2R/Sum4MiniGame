import java.util.Scanner;

public class MiniGame {
    public static void main(String[] args) {
        int[][] map = {
                { 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0 },
        };
        boolean over = false;
        String[] tiles = { "O ", "\033[0;31m@ \033[0m", "\033[0;34m@ \033[0m" };
        int move = 1;
        Scanner in = new Scanner(System.in);
        while (!over) {
            util.prt("\033[H\033[2J");
            util.prt("1,2,3,4,5,6,7");
        
            display(map, tiles);

            if (over) {
                break;
            }
            int col = Integer.parseInt(in.nextLine()) - 1;
            if (canMove(col)) {

                for (int i = 0; i < map.length; i++) {
                    if (map[i][col] != 0) {
                        map[i - 1][col] = move;
                        break;
                    } else if (i == 5) {
                        map[i][col] = move;
                        break;
                    }
                }
            }
            display(map, tiles);
            over = win(map, move);
            if (over) {
                util.prt("game over");
            }
            if (move == 1) {
                move = 2;
            } else {
                move = 1;
            }

        }

    }

    public static void display(int[][] map, String[] tiles) {
        // Displays the map
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                int tileCode = map[i][j];
                System.out.print(tiles[tileCode]);

            }
            System.out.println();
        }
    }

    public static boolean canMove(int col) {
        // Check whether within bounds.
        if (col >= 0 && col < 7)
            return true;

        return false;
    }

    public static boolean win(int[][] map, int move) {

        for (int i = 0; i < map.length - 1; i++) {
            for (int j = 0; j < map[i].length; j++) {
                int colW = 0;// colum win
                int rowW = 0;// row win
                int diagRW = 0;// diaganal right win
                int diagLW = 0;// diaganal left win
                for (int w = 0; w < 4; w++) {// count how many of a players characters are in a row
                    if (i + w < 6 && map[i][j] == move && map[i + w][j] == move) {
                        colW++;
                    }
                    if (j + w < 7 && map[i][j] == move && map[i][j + w] == move) {
                        rowW++;
                    }
                    if (j + w < 7 && i + w < 6 && map[i][j] == move && map[i + w][j + w] == move) {
                        diagRW++;
                    }
                    if (i - w > -1 && j - w > -1 && map[i][j] == move && map[i - w][j - w] == move) {
                        diagLW++;
                    }
                }
                if (colW >= 4 || rowW >= 4 || diagRW >= 4 || diagLW >= 4) {
                    return true;
                }
            }
        }
        return false;
    }

}