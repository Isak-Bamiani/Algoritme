package Oppgave2_RobinHood;

// Hashing av tekststrenger med lineær probing
// Bruker Javas innebygde hashfunksjon for strenger
//
// Enkel og begrenset implementasjon:
//
// - Ingen rehashing ved full tabell
// - Tilbyr bare innsetting og søking

/**
 * Dette er min løsning for opgpave 2: "Robin Hood".
 * Jeg har lagt til kommentarer med store bokstaver der jeg har gjort endringer
 */
public class hashLinear {
    // Hashlengde
    private int hashLengde;

    // Hashtabell
    public String hashTabell[];     //JEG GJØR EN ENDRING HER. Gjør om tabell-atributten til en public så jeg kan bruke den i Main klassen for å printe

    // Antall elementer lagret i tabellen
    private int n;

    // Antall probes ved innsetting
    private int antProbes;

    // Konstruktør
    // Sjekker ikke for fornuftig verdi av hashlengden
    //
    public hashLinear(int lengde) {
        hashLengde = lengde;
        hashTabell = new String[lengde];
        n = 0;
        antProbes = 0;
    }

    // Returnerer load factor
    public float loadFactor() {
        return ((float) n) / hashLengde;
    }

    // Returnerer antall data i tabellen
    public int antData() {
        return n;
    }

    // Returnerer antall probes ved innsetting
    public int antProbes() {
        return antProbes;
    }

    // Hashfunksjon
    int hash(String S) {
        int h = Math.abs(S.hashCode());
        return h % hashLengde;
    }

    // Innsetting av tekststreng med lineær probing
    // Avbryter med feilmelding hvis ledig plass ikke finnes
    //
    void insert(String S) {
        // Beregner hashverdien
        int h = hash(S);

        // Lineær probing
        int neste = h;

        //LINJE 70 OG 71 ER MINE ENDRINGER
        String temp;                    //Denne vil holde på verdien vi møter på når vi kommer til en index som har en verdi der
        int skrittT = 0, skrittS = 0;   //Her teller vi hvor mange skritt variabelen T og S tar (Se oppgavetekst for variablene), slik at vi kan bestemme oss for hvilken endring vi skal gjøre

        while (hashTabell[neste] != null) {
            // Ny probe
            antProbes++;

            //MIN ENDRING AV KODEN. Her tester vi for å se hvilken endring vi skal gjøre.
            /*
            * Dersom S har tatt flere skritt så vil vi implementere en last come first serve prinsipp,
            * ellers vil vi implementere en vanlig lineær probing
            */
            if(skrittS > skrittT) { //Last come first serve
                temp = hashTabell[neste];  //MIN ENDRING AV KODEN. Vi lagrer den strengen som er i denne indexen
                hashTabell[neste] = S;     //MIN ENDRING AV KODEN. Vi bytter ut den nåværende strengen med den nye strengen
                S = temp;                  //MIN ENDRING AV KODEN. Den gamle strengen setter jeg nå som S slik at løkken fortsetter nå med denne verdien og flytter da gjenstående elementer i forhold til denne
                skrittT++;                 //MIN ENDRING AV KODEN. Elementet i tabellen har nå måttet flytte på seg
            }
            else { //Vanlig lineær probing
                skrittS++;                 //MIN ENDRING AV KODEN. Elementet vi prøver å legge inn har nå måttet flytte på seg
            }

            // Denne indeksen er opptatt, prøver neste
            neste++;

            // Wrap-around
            if (neste >= hashLengde)
                neste = 0;

            // Hvis vi er kommet tilbake til opprinnelig hashverdi, er
            // tabellen full og vi gir opp (her ville man normalt
            // doblet lengden på hashtabellen og gjort en rehashing)
            if (neste == h) {
                System.err.println("\nHashtabell full, avbryter");
                System.exit(0);
            }
        }

        // Lagrer tekststrengen på funnet indeks
        hashTabell[neste] = S;

        // øker antall elementer som er lagret
        n++;
    }

    // Søking etter tekststreng med lineær probing
    // Returnerer true hvis strengen er lagret, false ellers
    //
    boolean search(String S) {
        // Beregner hashverdien
        int h = hash(S);

        // Lineær probing
        int neste = h;

        while (hashTabell[neste] != null) {
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
