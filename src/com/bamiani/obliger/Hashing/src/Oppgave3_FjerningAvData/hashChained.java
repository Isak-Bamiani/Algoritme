package Oppgave3_FjerningAvData;

// Hashing av tekststrenger med kjeding i lenket liste
// Bruker Javas innebygde hashfunksjon for strenger
//
// Enkel og begrenset implementasjon:
//
// - Ingen rehashing ved full tabell/lange lister
// - Tilbyr bare innsetting og søking

/**
 * Dette er min løsning for opgpave 3: "Fjerning av data".
 * Jeg har lagt til kommentarer med store bokstaver der jeg har gjort endringer
 */
public class hashChained {
    // Indre klasse:
    // Node med data, kjedes sammen i lenkede lister
    private class hashNode {
        // Data, en tekststreng
        String data;
        // Neste node i listen
        hashNode neste;

        // Konstruktør for listenoder
        public hashNode(String S, hashNode hN) {
            data = S;
            neste = hN;
        }
    }

    // Hashlengde
    private int hashLengde;

    // Hashtabell, pekere til lister
    public hashNode hashTabell[];   //ENDRING: Gjorde om denne fra privat til public

    // Antall elementer lagret i tabellen
    private int n;

    // Antall kollisjoner ved innsetting
    private int antKollisjoner;

    // Konstruktør
    // Sjekker ikke for fornuftig verdi av hashlengden
    public hashChained(int lengde) {
        hashLengde = lengde;
        hashTabell = new hashNode[lengde];
        n = 0;
        antKollisjoner = 0;
    }

    // Returnerer load factor
    public float loadFactor() {
        return ((float) n) / hashLengde;
    }

    // Returnerer antall data i tabellen
    public int antData() {
        return n;
    }

    // Returnerer antall kollisjoner ved innsetting
    public int antKollisjoner() {
        return antKollisjoner;
    }

    // Hashfunksjon
    int hash(String S) {
        int h = Math.abs(S.hashCode());
        return h % hashLengde;
    }

    // Innsetting av tekststreng med kjeding
    void insert(String S) {
        // Beregner hashverdien
        int h = hash(S);

        // øker antall elementer som er lagret
        n++;

        // Sjekker om kollisjon
        if (hashTabell[h] != null)
            antKollisjoner++;

        // Setter inn ny node først i listen
        hashTabell[h] = new hashNode(S, hashTabell[h]);
    }

    // Søking etter tekststreng i hashtabell med kjeding
    // Returnerer true hvis strengen er lagret, false ellers
    boolean search(String S) {
        // Finner listen som S skal ligge i
        hashNode hN = hashTabell[hash(S)];

        // Leter gjennom listen
        while (hN != null) {
            // Har vi funnet tekststrengen?
            if (hN.data.compareTo(S) == 0)
                return true;
            // Prøver neste
            hN = hN.neste;
        }
        // Finner ikke strengen, har kommet til slutten av listen
        return false;
    }

    /**
     * MIN KODE/IMPLEMENTASJON AV FUNKSJONEN FOR Å FJERNE DATA
     * I PROGRAMMET FOR HASHING MED KJEDING
     */
    void delete(String S) {
        hashNode hN = hashTabell[hash(S)]; //Finner listen som S skal ligge i
        String temp = null; // Temp. variabel som vil holde på tekst-strengene fra nodene

        //Leter gjennom listen
        while (hN != null) {
            //Sjekk om vi har funnet tekststrengen
            if (hN.data.compareTo(S) == 0) {

                //Sjekk om strengen er det første elementet i listen
                if (temp == null) {
                    //Hvis det ikke finnes noen nabo til denne noden så endrer vi strengen til en tom streng
                    if(hN.neste == null) {
                        hN.data = "";
                        temp = hN.data;  //Hvis det er det så tar vi dataen fra denne noden
                    } else {
                        //Hvis det er en nabo så tar vi dataen fra nabo-noden og setter det inn i denne noden.
                        hN.data = hN.neste.data;
                        temp = hN.data;
                    }
                }

                //Henter ut nabo-noden til den noden som skal fjernes
                hashNode nabo = hashTabell[hash(temp)];

                //Itererer gjennom nabo-relasjonene til nodene
                while (nabo != null) {
                    //Hvis vi står på noden som er før den vi skal slette så
                    if(nabo.data.compareTo(temp) == 0) {
                        //Hvis naboen sin neste --> neste ikke er lik null så setter vi naboens neste til å være lik naboens neste --> neste
                        if(nabo.neste.neste != null) {
                            nabo.neste = nabo.neste.neste;
                            break;
                        }
                        //Ellers erklærer vi at naboens neste er lik null
                        else {
                            nabo.neste = null;

                            break;
                        }
                    }
                    //Hvis vi ikke står på nabo-noden av den noden vi skal slette så sjekker vi neste
                    nabo = nabo.neste;
                }
                break;
            }
            //prøver neste
            temp = hN.data;
            hN = hN.neste;
        }
    }
}