package Oppgave1_LastComeFirstServe;

import java.io.IOException;
import java.util.Scanner;

public class Oppgave1_Main {
    // Enkelt testprogram:
    // * Hashlengde gis som input på kommandolinjen
    //
    // * Leser tekststrenger linje for linje fra standard input
    //   og lagrer dem i hashtabellen
    //
    // * Skriver ut litt statistikk etter innsetting
    //
    // * Tester om søk fungerer for et par konstante verdier
    public static void main(String argv[])
    {
        System.out.println("\nTrykk på CTRL + D når du har skrevet inn alle elementene"); //DENNE KODEBITEN LA JEG TIL. Info til klienten

        // Hashlengde leses fra kommandolinjen
        int hashLengde = 0;
        Scanner input = new Scanner(System.in);
        try
        {
            if (argv.length != 1)
                throw new IOException("Feil: Hashlengde må angis");
            hashLengde = Integer.parseInt(argv[0]);
            if (hashLengde < 1 )
                throw new IOException("Feil: Hashlengde må væree stÃ¸rre enn 0");
        }
        catch (Exception e)
        {
            System.err.println(e);
            System.exit(1);
        }

        System.out.println("HashLengden = " + hashLengde); //DENNE KODEBITEN LA JEG TIL. Info for klienten

        // Lager ny hashTabell
        hashLinear hL = new hashLinear(hashLengde);

        // Leser input og hasher alle linjer
        while (input.hasNext())
        {
            hL.insert(input.nextLine());
        }

        // Skriver ut hashlengde, antall data lest, antall kollisjoner
        // og load factor
        System.out.println("Hashlengde  : " + hashLengde);
        System.out.println("Elementer   : " + hL.antData());
        System.out.printf( "Load factor : %5.3f\n",  hL.loadFactor());
        System.out.println("Probes      : " + hL.antProbes());

        // Et par enkle søk
        String S = "Volkswagen Karmann Ghia";
        if (hL.search(S))
            System.out.println("\"" + S + "\"" + " finnes i hashtabellen");
        S = "Il Tempo Gigante";
        if (!hL.search(S))
            System.out.println("\"" + S + "\"" + " finnes ikke i hashtabellen");



        /*
         * Jeg lager en enkel utskrift for elementene som ligger i hashtabellen.
         * Dette lages for å se lettere hva som befinner seg i den ETTER alle inserts
         */
        System.out.println("\n********************INNHOLD I HASHTABELLEN********************");
        int i;
        for(i = 0; i < hL.hashTabell.length; i++) {
            System.out.println("Indeks: " + i + "   -   " + hL.hashTabell[i]);
        }
        System.out.println("**************************************************************");
    }
}
