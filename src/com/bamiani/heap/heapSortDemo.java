package heap;

import java.io.*;
import java.util.*;

// Demo av enkel heapsort
//
public class heapSortDemo
{
    // Heapsort av array med heltall
    //
    public static void heapSort(int A[])
    {
        int n = A.length;
        intHeap H = new intHeap(n);

        // Legger alle verdier i array inn i en heap
        for (int i = 0; i < n; i++)
            H.insert(A[i]);

        // Tar ut minste verdi fra heap n ganger, og legger sortert
        // tilbake i array
        for (int i = 0; i < n; i++)
            A[i] = H.removeMin();
    }

    // Testprogram
    //
    public static void main(String args[])
    {
        // Heapsort av array med tilfeldige tall
        int n = 20;
        int A[] = new int[n];
        Random r = new Random();

        for (int i = 0; i < n; i++)
            A[i]  = r.nextInt(100);

        heapSort(A);

        for (int i = 0; i < n; i++)
            System.out.print(A[i] + " ");
        System.out.println();

    }
}