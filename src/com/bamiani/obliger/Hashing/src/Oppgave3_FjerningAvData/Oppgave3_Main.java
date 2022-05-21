package Oppgave3_FjerningAvData;

import java.io.IOException;
import java.util.Scanner;

public class Oppgave3_Main {
    // Enkelt testprogram:
    //
    // * Hashlengde gis som input på kommandolinjen
    //
    // * Leser tekststrenger linje for linje fra standard input
    //   og lagrer dem i hashtabelle
    //
    // * Skriver ut litt statistikk etter innsetting
    //
    // * Tester om søk fungerer for et par konstante verdier
    //
    public static void main(String argv[])
    {
        System.out.println("\nFor å sjekke om slettingen er ok så lager jeg en streng \"test2\"." +
                "\nNår jeg da kjører koden så legger jeg til for eksempel strengene \"test2\" og \"test22\"." +
                "Begge\ndisse strengene får samme hashverdi på min pc. Dermed vil programmet først kjøre en sjekk for\n" +
                "å vise at \"test2\" finnes etter at bruker selv taster inn, også sletter jeg den og viser at\n" +
                "\"test2\" nå ikke finnes.\n ************************************************************"); //DENNE KODEBITEN LA JEG TIL. Info til klienten
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
                throw new IOException("Feil: Hashlengde må være større enn 0");
        }
        catch (Exception e)
        {
            System.err.println(e);
            System.exit(1);
        }

        System.out.println("HashLengden = " + hashLengde); //DENNE KODEBITEN LA JEG TIL. Info for klienten

        // Lager ny hashTabell
        hashChained hC = new hashChained(hashLengde);

        // Leser input og hasher alle linjer
        while (input.hasNext())
        {
            hC.insert(input.nextLine());
        }

        // Skriver ut hashlengde, antall data lest, antall kollisjoner
        // og load factor
        System.out.println("Hashlengde  : " + hashLengde);
        System.out.println("Elementer   : " + hC.antData());
        System.out.printf( "Load factor : %5.3f\n",  hC.loadFactor());
        System.out.println("Kollisjoner : " + hC.antKollisjoner());

///////////////////////////////ENDRING AV HOVED-KODEN/////////////////////////////////
/*
For å sjekke om slettingen er ok så lager jeg en streng "test2".
Når jeg da kjører koden så legger jeg til for eksempel strengene "test2" og "test22". Begge
disse strengene får samme hashverdi på min pc. Dermed vil programmet først kjøre en sjekk for
å vise at "test2" finnes etter at bruker selv taster inn, også sletter jeg den og viser at
"test2" nå ikke finnes.
 */
        String S = "test2";
        if (hC.search(S))
            System.out.println("\"" + S + "\"" + " finnes i hashtabellen");

        hC.delete(S);

        if (!hC.search(S))
            System.out.println("\"" + S + "\"" + " finnes ikke i hashtabellen");
    }
}
