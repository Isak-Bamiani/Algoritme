package heap;
// Enkle binÃ¦re trÃ¦r med et heltall som nÃ¸kkelverdi. TrÃ¦rne er heap-
// ordnet, dvs. at enhver node har en verdi som er mindre eller lik verdien
// i dens eventuelle barn. TrÃ¦rne er ikke nÃ¸dvendigvis balanserte.

// Koden nedenfor er kompilert, men ikke testet for bugs og logiske feil.

public class oving {
    // Oppgave a

    void reparer(Node rot) {
        // "rot" er roten i et binÃ¦rt tre der begge subtrÃ¦rne til roten er
        // heap-ordnede (hvis de ikke er tomme), mens verdien i selve roten kan
        // vÃ¦re slik at den bryter mot heap-ordningen. Funksjonen "reparerer"
        // treet slik at verdien i roten stÃ¥r riktig og hele treet blir
        // heap-ordnet.
        //
        // Tricket her er Ã¥ gjÃ¸re omtrent det samme som nÃ¥r vi fjerner noe fra
        // en heap, dvs. en "percolate down" der vi bytter verdien i roten
        // nedover i treet med den minste av verdiene i barna, inntil verdien
        // stÃ¥r riktig. Det kan lages mange varianter av lÃ¸sning her.

        Node denne, neste;
        boolean ferdig = false;

        denne = rot;
        ferdig = (denne == null);

        while (!ferdig) {
            // Finn det barnet til "denne" noden som har minst verdi
            if (denne.left == null)
                neste = denne.right;
            else if (denne.right == null)
                neste = denne.left;
            else if (denne.left.value < denne.right.value)
                neste = denne.left;
            else
                neste = denne.right;

            // Ferdig hvis vi er i en bladnode, eller verdien stÃ¥r riktig
            if (neste == null || denne.value <= neste.value)
                ferdig = true;
            else {
                // Bytt verdi med minste barn
                int tmp = denne.value;
                denne.value = neste.value;
                neste.value = tmp;

                // Forsett nedover i treet
                denne = neste;
            }
        }
    }

    // Oppgave b

    void lag_heap_ordning(Node rot) {
        // "heap-ordner" et helt binÃ¦rt tre med funksjonen fra forrige
        // oppgave. Rekursjon gjÃ¸r lÃ¸sningen enkel og elegant.

        if (rot != null) {
            // GjÃ¸r om venstre subtre til et heap-ordnet tre
            lag_heap_ordning(rot.left);

            // GjÃ¸r om hÃ¸yre subtre til et heap-ordnet tre
            lag_heap_ordning(rot.right);

            // Forutsetningene for funksjonen fra forrige oppgave er nÃ¥
            // tilstede, kun roten stÃ¥r feil. Plasser verdien i roten riktig
            // i treet med et kall pÃ¥ "reparer".

            reparer(rot);
        }
    }

    // Oppgave c

    // 1. O(n log n) kan godtas som svar pÃ¥ arbeidsmengden til metoden
    //    fra oppgave 2.2. Begrunnelsen for dette svaret er at treet har
    //    dybde log n nÃ¥r det er balansert, og at vi utfÃ¸rer reparer en
    //    gang for hver av de n nodene.
    //
    //    Hvis treet er komplett kan det imidlertid vises at arbeidmengden
    //    faktisk er 2n. Ser vi pÃ¥ kallene til metoden reparer, som har
    //    arbeidsmengde lik dybden av subtreet som "repareres", blir det ca.
    //    n/2 kall med dybde lik 1 (for alle nodene i nederste nivÃ¥), n/4
    //    kall for subtrÃ¦r med dybde 2, n/8 kall for subtrÃ¦r med dybde 3 etc.
    //    Den totale tiden for alle kallene til reparer blir da lik summen:
    //
    //        n/2 + 2n/4 + 3n/8 + 4n/16 + ...
    //
    //    Det kan vises at denne summen er lik 2n.
    //
    // 2. Treet er her degenerert til en liste (hver node har bare et barn,
    //    med unntak av den dypeste) og den totale arbeidsmengden for metoden
    //    lag_heap_ordning blir O(n^2).

    // Oppgave d

    int tell(Node rot, int verdi) {
        // Rekursiv variant som finner antall forekomster av "verdi" i
        // det heap-ordnede treet med rot i "rot".

        if (rot == null || verdi < rot.value)
            return 0;

        int antall = tell(rot.left, verdi) + tell(rot.right, verdi);

        return ((rot.value == verdi) ? 1 : 0) + antall;
    }
}
