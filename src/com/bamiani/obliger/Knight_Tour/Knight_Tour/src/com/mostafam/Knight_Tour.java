package com.mostafam;

import java.util.Scanner;

public class Knight_Tour {
     int chess_Board_Size;
     int free = -1;
     int[][] visited;

     int[] row_Move = { 2, 1, -1, -2, -2, -1, 1, 2 };
     int[] column_Move = { 1, 2, 2, 1, -1, -2, -2, -1 };


    public Knight_Tour(int chess_Board_Size) {
        this.chess_Board_Size = chess_Board_Size;
        visited = new int[chess_Board_Size][chess_Board_Size];

        for (int i = 0; i < chess_Board_Size; i++)
            for (int j = 0; j < chess_Board_Size; j++)
                this.visited[i][j] = free;

    }


    public void solve_Knight_Tour() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("enter row position: ");
        int row = scanner.nextInt();
        System.out.println("enter column position: ");
        int column = scanner.nextInt();
        System.out.println();
        //bruker scanner til å legge inn  start position
        visited[row][column] = 0;
        if( knight_Algorithm(1, row, column)) {
            print_Solution();
        } else {
            System.out.println("No solution found...");
        }
    }
    

    public boolean knight_Algorithm(int counting_Moves, int row, int column) {
        if (counting_Moves == chess_Board_Size * chess_Board_Size) {
            return true;
        }

        for (int i = 0; i < row_Move.length; ++i) {
            int next_Row_Move = row + row_Move[i];
            int next_Column_Move = column + column_Move[i];

            if ( check_Valid_Move(next_Row_Move, next_Column_Move) && visited[next_Row_Move][next_Column_Move] == free) {

                visited[next_Row_Move][next_Column_Move] = counting_Moves;
                if ( knight_Algorithm(counting_Moves + 1, next_Row_Move, next_Column_Move) ) {
                    return true;
                }
                visited[next_Row_Move][next_Column_Move] = free;
            }
        }
        return false;
    }

    public boolean check_Valid_Move(int row, int column) {

        return row >= 0 && row < chess_Board_Size && column >= 0 && column < chess_Board_Size;
    }

    public void print_Solution() {
        for (int row = 0; row < chess_Board_Size; row++) {
            for (int column = 0; column< chess_Board_Size; column++) {
                System.out.print(visited[row][column] + "   ");
            }
            System.out.println();
        }
    }
}
