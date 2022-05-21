package graph;

import java.io.*;

// Warshall-algoritmen for Ã¥ lÃ¸se APRP: All Pairs Reachability Problem
//
// Bruker en enkel uvektet graf med nabomatrise, der data i nodene
// bare er en tekststreng (superklassen enkelGraf)
//
public class warshall extends enkelGraf
{
    // KonstruktÃ¸r, leser inn en graf fra fil
    //
    public warshall(String filNavn)
    {
        super(filNavn);
    }

    // Warshall-algoritmen for Ã¥ lÃ¸se APRP 
    // LÃ¸sningen lagres i den opprinnelige nabomatrisen til grafen
    //
    public void warshallAPRP()
    {
        for (int k = 0; k < n; k++)
        {
            // Prøver alle noder som startpunkt
            for (int i = 0; i < n; i++)
            {
                // Prøver alle noder som endepunkt
                for (int j = 0; j < n; j++)
                {
                    // Finnes det en vei fra i til j gjennom k?
                    nabo[i][j] = nabo[i][j] ||
                            (nabo[i][k] && nabo[k][j]);
                }
            }
        }
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
        warshall G = new warshall(filNavn);

        // Skriver ut grafen/nabomatrisen fÃ¸r Warshall
        System.out.println("Graf:");
        G.skriv();

        // KjÃ¸rer Warshall-algoritmen
        G.warshallAPRP();

        // Skriver ut lÃ¸sningsmatrisen
        System.out.println("\nWarshall:");
        G.skriv();
    }
}