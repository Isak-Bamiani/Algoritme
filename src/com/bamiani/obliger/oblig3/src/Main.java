

import java.util.Random;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Quicksort quicksort = new Quicksort();
        Merge_Sort merge_Sort = new Merge_Sort();
        InsertionSort insertionSort = new InsertionSort();
        Radix_Sort radix_sort = new Radix_Sort();
        CalculationConstant calculationConstant = new CalculationConstant();


        Scanner scanner = new Scanner(System.in);



        System.out.println("Write number of integer to sort");

        int number = scanner.nextInt();

        System.out.println("choice different sort algorithms: ");

        System.out.println("1 = Quick Sort: ");

        System.out.println("2 = Merge Sort : ");

        System.out.println("3 = Insertion Sort : ");

        System.out.println("4 = Radix Sort");


        int method_Algorithms = scanner.nextInt();



        Random random = new Random();
        int[] new_Array = new int[number];
        //lager arrayen
        for (int i = 0; i < number; i++) {
            new_Array[i] = random.nextInt(2 * number);
        }

        long time = System.currentTimeMillis();

        switch (method_Algorithms) {
            case 1 -> {
                quicksort.quickSort(new_Array, 0, number - 1);
                time = System.currentTimeMillis() - time;
                System.out.printf("Quick sort\t: %6.3f s\n", time / 1000.0);
                calculationConstant.Calculate_Constant_Log(time, number);

            }
            case 2 -> {
                merge_Sort.mergeSort(new_Array, 0, number - 1);
                time = System.currentTimeMillis() - time;
                System.out.printf("Merge sort\t: %6.3f s\n", time / 1000.0);
                calculationConstant.Calculate_Constant_Log(time, number);

            }
            case 3 -> {
                insertionSort.sort_Insertion(new_Array);
                time = System.currentTimeMillis() - time;
                System.out.printf("Insertion Sort\t: %6.3f s\n", time / 1000.0);
                calculationConstant.Calculate_Constant_Square(time, number);
            }
            case 4 -> {
                radix_sort.radixsort(new_Array, number);
                time = System.currentTimeMillis() - time;
                System.out.printf("Radix Sort\t: %6.3f s\n", time / 1000.0);
                calculationConstant.Calculate_Constant_Log(time, number);

            }
            default -> System.out.println("start the  program again wrong number!!");
        }
    }
}
