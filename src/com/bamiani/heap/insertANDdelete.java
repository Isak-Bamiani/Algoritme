package com.bamiani.heap;

import java.util.Scanner;

// Enkel min-heap med heltall
//
public class insertANDdelete
{
    // Array som lagrer selve heapen
    private int []a;

    // Antall verdier lagret i heapen
    private int n;

    // Maks. antall verdier i heap
    private int maxN;

    // KonstruktÃ¸r
    //
    public insertANDdelete(int length)
    {
        maxN = length;
        a = new int[maxN];
        n = 0;
    }

    // Sjekker om heap er tom
    //
    public boolean isEmpty()
{
    return (n == 0);
}

    // Legger inn ny verdi i heap
    // Terminerer ved feil (full heap)
    //
    public void insert(int value)
    {
        // Ã˜ker stÃ¸rrelsen pÃ¥ heapen
        n++;

        // Heap full?
        if (n > maxN)
        {
            System.err.println("Error: Heap full");
            System.exit(1);
        }

        // Setter "child" like indeks til første ledige plass
        int child = n-1;

        boolean done = false;
        // Bytter ny verdi oppover i heap inntil den stÃ¥r riktig
        while (!done)
        {
            if (child == 0)
                // Ny verdi stÃ¥r i roten, ferdig
                done = true;

            // Beregner indeks til foreldernoden
            int parent = (child-1)/2;

            if (value >= a[parent])
                // Ny verdi stÃ¥r riktig i forhold til forelder, ferdig
                done = true;
            else
            {
                // Ny verdi stÃ¥r feil, flytter verdien i foreldernode
                // til den ledige plassen
                a[child] = a[parent];

                // Finner indeks til neste foreldernode
                child = parent;
                parent = (child-1)/2;
            }
        }
        // Setter til slutt inn ny verdi pÃ¥ "hullet" som er ledig
        a[child] = value;
    }

    // Fjerner minste verdi fra heap
    // Terminerer ved feil (tom heap)
    //
    public int removeMin()
    {
        // Heap tom?
        if (isEmpty())
        {
            System.err.println("Error: Heap empty");
            System.exit(1);
        }

        // Ta vare pÃ¥ minste verdi i roten av heap
        int min = a[0];

        // Ta vare pÃ¥ verdien pÃ¥ siste indeks i heapen
        int siste = a[n-1];

        // Reduser stÃ¸rrelsen pÃ¥ heap
        n--;

        int current = 0;
        int left, right, next = 0;
        boolean done = false;

        // Flytt opp verdier fra roten og nedover i heap, inntil vi
        // har funnet plassen der siste verdi skal settes inn
        //
        while (!done)
        {
            // Beregner indeksene til venstre og hÃ¸yre barn
            left = 2*current + 1;
            right = left + 1;

            // Hvis indeks til venstre barn er utenfor heapen, stÃ¥r
            // verdien riktig som et blad pÃ¥ nederste nivÃ¥
            if (left > n-1)
                done = true;
            else
            {
                // Hvis indeks til hÃ¸yre barn er utenfor heapen, skal
                // vi sammenligne og evt. bytte plass med venstre barn
                if (right > n-1)
                    next = left;

                    // Hvis begge barna ligger i heapen, skal vi
                    // sammenligne og evt. bytte plass med barnet med
                    // minst verdi
                else if (a[left] < a[right])
                    next = left;
                else
                    next = right;

                // Ferdig hvis det som var siste verdi nÃ¥ stÃ¥r riktig
                // i forhold til minste barn
                done = (siste <= a[next]);
            }

            // Hvis ikke ferdig, fortsett Ã¥ sjekke nedover i heapen
            if (!done)
            {
                a[current] = a[next];
                current = next;
            }
        }
        // Setter inn det som var siste verdi pÃ¥ riktig plass
        a[current] = siste;

        // Returner den "gamle" roten i heapen
        return min;
    }

    // Demo print av innholdet i heap, ett nivÃ¥ per linje
    //
    public void print()
    {
        int toerpotens = 2;
        for (int i = 0; i < n; i++)
        {
            if (i == toerpotens-1)
            {
                System.out.println();
                toerpotens *= 2;
            }
            System.out.print(" " + a[i]);
        }
        System.out.println("\n");
    }

    // Testprogram
    //
    public static void main(String args[])
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Max. number of values    ? ");
        int n = scanner.nextInt();

        insertANDdelete H = new insertANDdelete(n);

        char valg = ' ';
        while (valg != 'e')
        {
            System.out.print("I(nsert)/R(emove)/E(xit) ? ");
            valg = scanner.next().charAt(0);

            if (valg == 'i' || valg == 'I')
            {
                System.out.print("Integer value to insert  ? ");
                int verdi = scanner.nextInt();
                H.insert(verdi);
                System.out.println("\nAfter inserting " + verdi + " :");
                H.print();
            }

            if (valg == 'r' || valg == 'R')
            {
                int verdi = H.removeMin();
                System.out.println("\nAfter removing " + verdi + " :");
                H.print();
            }
        }
    }
}