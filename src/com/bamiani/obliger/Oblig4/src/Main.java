import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {

        // Jeg brukte koden fra https://www.baeldung.com/java-binary-tree

        // Listen som jeg skal sette inn ord fra tekstfilen
        ArrayList<String> ordliste = new ArrayList<>();

        // Les fil
        try {

            File file = new File("text.txt");
            Scanner scanner = new Scanner(file);
            while ( scanner.hasNextLine()) {
                String linje = scanner.nextLine();
                String[] splittet_linje = linje.split(" ");
                // Isoler hvert ord og fjern uviktige karakterer
                // konverter til Uppercase
                for ( int i = 0; i < splittet_linje.length; i++ ) {
                    splittet_linje[i] = splittet_linje[i].replace(",", "");
                    splittet_linje[i] = splittet_linje[i].replace(".", "");
                    splittet_linje[i] = splittet_linje[i].toUpperCase();
                }
                // Legg ord til ordlisten øverst
                for ( int i = 0; i < splittet_linje.length; i++ ) {
                    ordliste.add( splittet_linje[i] );
                }
            }

        } catch ( FileNotFoundException e ) {
            System.out.println("Filen eksisterer ikke");
        }

        // Legg til første ordet i listen til det binære treet, dette blir roten av treet
        Rot BinaryTree = new Rot( new Node( ordliste.get(0) ) );

        // Legg til resten av ordene rekursivt
        for ( int i = 1; i < ordliste.size(); i++ ) {
            BinaryTree.leggtil( ordliste.get(i) );
        }

        // Print ut alle ord i treet. Start med alle som ligger til venstre for treet
        System.out.println( "\n\n| Antall forekomster | Ord |" );
        System.out.println( " -------------------------- " );
        BinaryTree.printAlfabetisk(BinaryTree.rot);

    }
}