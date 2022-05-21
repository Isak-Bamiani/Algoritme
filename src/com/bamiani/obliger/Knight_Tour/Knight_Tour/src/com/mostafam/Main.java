package com.mostafam;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter board size: ");

        int board_Size = scanner.nextInt();


        Knight_Tour knightTour = new Knight_Tour(board_Size);
        System.out.println();
        knightTour.solve_Knight_Tour();

    }
}
