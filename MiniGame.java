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
        String[] tiles = { "O ", "\033[0;31m# \033[0m", "\033[0;34m@ \033[0m" };
        int move = 1;
        Scanner in = new Scanner(System.in);
        while (!over) {
            util.prt("\033[H\033[2J");
            System.out.println("1,2,3,4,5,6,7");
            display(map, tiles);
            over = win(map, move);
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
                if (move == 1) {
                    move = 2;
                } else {
                    move = 1;
                }

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
        if (col >= 0 && col < 8)
            return true;

        return false;
    }

    public static boolean win(int[][] map, int move) {
        int colW = 0;// colum win
        int rowW = 0;// row win
        int diagRW = 0;// diaganal right win
        int diagLW = 0;// diaganal left win
        for (int i = 0; i < map.length-1; i++) {
            for (int j = 0; j < map[i].length; j++) {
                for (int w = 0; w < 4; w++) {
                    if (map[i][j] == move && map[i + 1][j] == move && i + 1 != 6) {
                        colW++;
                    }
                    if (map[i][j] == move && map[i][j + 1] == move && j + 1 != 7) {
                        rowW++;
                    }
                    if (map[i][j] == move && map[i + 1][j + 1] == move && i + 1 != 6 && j + 1 != 7) {
                        diagRW++;
                    }
                    if (map[i][j] == move && map[i - 1][j - 1] == move && i - 1 != -1 && j - 1 != -1) {
                        diagLW++;
                    }
                }
            }
        }
        if (colW >= 4 || rowW >= 4 || diagRW >= 4 || diagLW >= 4) {
            return true;
        } else {
            return false;
        }
    }

}