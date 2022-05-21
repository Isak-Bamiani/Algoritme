package Oppgave1_LastComeFirstServe;

// Hashing av tekststrenger med lineær probing
// Bruker Javas innebygde hashfunksjon for strenger
//
// Enkel og begrenset implementasjon:
//
// - Ingen rehashing ved full tabell
// - Tilbyr bare innsetting og søking

/**
 * Dette er min løsning for opgpave 1: "Last come, first served".
 * Jeg har lagt til kommentarer med store bokstaver der jeg har gjort endringer
 */
public class hashLinear
{
    // Hashlengde
    private int hashLengde;

    // Hashtabell
    public String hashTabell[];  //JEG GJØR EN ENDRING HER. Gjør om tabell-atributten til en public så jeg kan bruke den i Main klassen for å printe

    // Antall elementer lagret i tabellen
    private int n;

    // Antall probes ved innsetting
    private int antProbes;

    // Konstruktør
    // Sjekker ikke for fornuftig verdi av hashlengden
    //
    public hashLinear(int lengde)
    {
        hashLengde = lengde;
        hashTabell = new String[lengde];
        n = 0;
        antProbes = 0;
    }

    // Returnerer load factor
    public float loadFactor()
    {
        return ((float) n)/hashLengde;
    }

    // Returnerer antall data i tabellen
    public int antData()
    {
        return n;
    }

    // Returnerer antall probes ved innsetting
    public int antProbes()
    {
        return antProbes;
    }

    // Hashfunksjon
    int hash(String S)
    {
        int h = Math.abs(S.hashCode());
        return h % hashLengde;
    }

    // Innsetting av tekststreng med lineær probing
    // Avbryter med feilmelding hvis ledig plass ikke finnes
    void insert(String S)
    {
        String temp; //DENNE VARIABELEN HAR JEG LAGT TIL. DEN VIL HOLDE PÅ VERDIER SOM SKAL FLYTTES PÅ ET HAKK TIL HØYRE

        // Beregner hashverdien
        int h = hash(S);

        // Lineær probing
        int neste = h;

        while (hashTabell[neste] != null)
        {
            // Ny probe
            antProbes++;

            /*
             *Linje 89, 90, 91 implementerer en Last Come First Serve prinsipp
             *Hvor det gamle elementet lagres når vi kommer til en index. Deretter
             *byttes det gamle med det nye elementet i den indexen vi står i
             *og det gamle elementet brukes videre for å
             *dytte resterende elementene ett hakk til "høyre".
             */
            temp = hashTabell[neste]; //DETTE HAR JEG LAGT TIL. Det som ligger på den første indeksen lagrer vi for senere bruk
            hashTabell[neste] = S; //DETTE HAR JEG LAGT TIL. Etter at vi har lagret den gamle stringen så bytter vi på verdien med den nye verdien
            S = temp; //DETTE HAR JEG LAGT TIL. Den gamle strengen setter jeg nå som S slik at løkken fortsetter nå med denne verdien og flytter da gjenstående elementer i forhold til denne

            // Denne indeksen er opptatt, prøver neste
            neste++;

            // Wrap-around
            if (neste >= hashLengde)
                neste = 0;
            // Hvis vi er kommet tilbake til opprinnelig hashverdi, er
            // tabellen full og vi gir opp (her ville man normalt
            // doblet lengden på hashtabellen og gjort en rehashing)
            if (neste == h)
            {
                System.err.println("\nHashtabell full, avbryter");
                System.exit(0);
            }
        }

        // Lagrer tekststrengen på funnet indeks
        hashTabell[neste] = S;

        // Øker antall elementer som er lagret
        n++;
    }

    // Søking etter tekststreng med lineær probing
    // Returnerer true hvis strengen er lagret, false ellers
    boolean search(String S)
    {
        // Beregner hashverdien
        int h = hash(S);

        // Lineær probing
        int neste = h;

        while (hashTabell[neste] != null)
        {
            // Har vi funnet tekststrengen?
            if (hashTabell[neste].compareTo(S) == 0)
                return true;

            // Prøver neste mulige
            neste++;

            // Wrap-around
            if (neste >= hashLengde)
                neste = 0;

            // Hvis vi er kommet tilbake til opprinnelig hashverdi,
            // finnes ikke strengen i tabellen
            if (neste == h)
                return false;
        }

        // Finner ikke strengen, har kommet til en probe som er null
        return false;
    }
}
