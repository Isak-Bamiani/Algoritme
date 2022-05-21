package graph;

import java.io.*;
import java.util.Scanner;

// Enkel implementasjon av vektet graf med nabomatrise
// Kantlengder/vekter er positive heltall
// Nodene har bare en tegnstreng som data
//
public class enkelVektetGraf
{
    int n;            // Antall noder i grafen
    int lengde[][];   // Matrise med kantlengder/vekter
    String data[];    // Data i hver node

    // Brukes for Ã¥ markere at det ikke gÃ¥r en kant mellom to noder
    final int UENDELIG = 999999;

    // Metode som returnerer antall noder
    //
    public int antallNoder()
    {
        return n;
    }

    // KonstruktÃ¸r, leser inn grafdata fra en fil
    //
    public enkelVektetGraf(String filNavn)
    {
        les(filNavn);
    }

    // Innlesning av en graf fra fil
    // Nodene mÃ¥ nummereres fra 0 til n-1
    // Sjekker ikke for feil i data
    //
    public void les(String filNavn)
    {
        // Format for grafdataene:
        //
        // <antall noder>
        // <nr> <data> <ant.naboer> <nabonr> <lengde> <nabonr> <lengde>...
        // <nr> <data> <ant.naboer> <nabonr> <lengde> <nabonr> <lengde>...
        //             .
        //             .
        //             .
        // <nr> <data> <ant.naboer> <nabonr> <lengde> <nabonr> <lengde>...

        try
        {
            Scanner in = new Scanner(new File(filNavn));

            // Leser antall noder i grafen
            n = in.nextInt();

            // Oppretter arrayene som lagrer grafen
            lengde = new int[n][n];
            data = new String[n];

            // Initierer hele kantmatrisen til UENDELIG (ingen kant),
            // med 0 pÃ¥ diagonalen (nodene har "avstand 0 til seg selv")
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    lengde[i][j] = (i == j) ? 0 : UENDELIG;


            // Leser en linje med data for hver grafnode
            for (int i = 0; i < n; i++)
            {
                // Leser nodenummeret og data i noden
                int nodeNr = in.nextInt();
                data[nodeNr] = in.next();

                // Leser antall naboer for denne noden
                int antNaboer = in.nextInt();

                // Leser og legger inn alle kantlengder i kantmatrisen
                for (int j = 0; j < antNaboer; j++)
                {
                    int naboNr = in.nextInt();
                    int kantLengde = in.nextInt();
                    lengde[nodeNr][naboNr] = kantLengde;
                }
            }
        }
        catch (Exception e)
        {
            System.err.println(e);
            System.exit(1);
        }
    }

    // Utskrift av kantlengdematrisen
    //
    public void skriv()
    {
        skrivHeader();
        for (int i = 0; i < n; i++)
        {
            System.out.printf("%2s ", data[i]);
            for (int j = 0; j < n; j++)
                if (lengde[i][j] == UENDELIG)
                    System.out.print(" * ");
                else
                    System.out.printf("%2d ", lengde[i][j]);
            System.out.println();
        }
    }

    // Hjelpe-metode som brukes i utskrift
    // Skriver ut en linje med headerdata over matrisen
    //
    public void skrivHeader()
    {
        System.out.printf("   ");
        for (int i = 0; i < n; i++)
            System.out.printf("%2s ", data[i]);
        System.out.println();
    }


    // Testprogram
    //
    public static void main(String args[])
    {
        // Leser navnet pÃ¥ en fil med grafdata som input fra
        // kommandolinjen
        String filNavn = " ";
        try
        {
            if (args.length != 1)
                throw new IOException("Mangler filnavn");
            filNavn = args[0];
        }
        catch (Exception e)
        {
            System.err.println(e);
            System.exit(1);
        }

        // Oppretter ny graf
        enkelVektetGraf G = new enkelVektetGraf(filNavn);

        // Skriver ut innholdet av grafen
        G.skriv();
    }
}