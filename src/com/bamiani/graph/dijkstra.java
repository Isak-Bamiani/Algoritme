package graph;

import java.io.*;

// Dijkstra-algoritmen for Ã¥ lÃ¸se Single Source Shortest Path problemet
//
// Bruker en enkel vektet graf med kantlengdematrise, der data i
// nodene bare er en tekststreng (superklassen enkelVektetGraf)
//
public class dijkstra extends enkelVektetGraf
{
    // KonstruktÃ¸r, leser inn en graf fra fil
    //
    public dijkstra(String filNavn)
    {
        super(filNavn);
    }

    // O(n^2) Dijkstra-algoritme for SSSP med start i en node S
    // Skriver ut lÃ¸sningsarrayen
    //
    public void dijkstraSSSP(int S)
    {
        // Array for Ã¥ merke av noder som vi allerede har funnet
        // korteste vei til fra S
        boolean funnet[] = new boolean[n];

        // Array for Ã¥ lagre hittil korteste kjente avstand til hver
        // node fra S
        int avstand[] = new int[n];

        // Initierer de to hjelpe-arrayene
        for (int i = 0; i < n; i++)
        {
            avstand[i] = (i == S ? 0 : UENDELIG);
            funnet[i] = false;
        }

        // nåværende node og antall noder som er funnet
        int denne = S ;
        int antall = 0;

        while (antall < n)
        {
            // Finn noden med minste avstand fra S som ennÃ¥ ikke er funnet
            int minste = UENDELIG;
            for (int i = 0; i < n; i++)
                if (!funnet[i] && avstand[i] < minste)
                {
                    // i er nummer til noden som har minst avstand til S
                    denne = i;
                    minste = avstand[i];
                }

            // Oppdater avstander for alle naboer til nÃ¥vÃ¦rende node
            // som ikke er funnet
            for (int i = 0; i < n; i++)
                if (!funnet[i])
                {
                    int l = avstand[denne] + lengde[denne][i];
                    if (l < avstand[i])
                        avstand[i] = l;
                }

            // Marker at vi har funnet korteste avstand til nÃ¥vÃ¦rende node
            funnet[denne] = true;
            antall++;
        }

        // Skriver ut lÃ¸sningen
        System.out.printf("%2s ", data[S]);
        for (int i = 0; i < n; i++)
            if (avstand[i] == UENDELIG)
                System.out.printf(" * ", avstand[i]);
            else
                System.out.printf("%2d ", avstand[i]);
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
        dijkstra G = new dijkstra(filNavn);

        // Skriver ut kantlengdematrisen fÃ¸r Dijkstra
        System.out.println("Graf:");
        G.skriv();

        // KjÃ¸rer Dijkstra en gang med start i hver node og skriver ut
        // lÃ¸sningen
        System.out.println("\nDijkstra:");
        G.skrivHeader();
        for (int S = 0; S < G.antallNoder(); S++)
            G.dijkstraSSSP(S);
    }
}

