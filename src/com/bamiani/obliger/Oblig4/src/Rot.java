public class Rot {

    Node rot;

    public Rot( Node node ) {
        this.rot = node;
    }

    // Metode som starter den rekursive metoden leggtilrekursivt
    void leggtil(String ord ) {
        this.leggtilrekursivt( this.rot, ord );
    }

    public Node leggtilrekursivt( Node valgtnode, String ord ) {

        if (valgtnode == null) {
            return new Node(ord);
        }

        // Hvis ordene matcher, ikke add ordet til treet, men inkrementer telleren i den valgte noden.
        if ( valgtnode.ord.equals( ord ) ) {

            valgtnode.forekomster += 1;

        } else {

            // Hvis ordet er alfabetisk mindre enn foreldrenoden, legg til ordet til venstre til foreldrenoden.
            if ( Helper.sammenlignord( valgtnode.ord, ord ) ) {
                valgtnode.venstre = leggtilrekursivt( valgtnode.venstre, ord );

            // Hvis det er større, legg den til høyre delen.
            } else {
                valgtnode.hoyre = leggtilrekursivt( valgtnode.hoyre, ord );
            }

        }

        return valgtnode;
    }

    public void printAlfabetisk(Node node) {
        if (node != null) {
            printAlfabetisk(node.venstre);
            System.out.println( node.forekomster + " " + node.ord);
            printAlfabetisk(node.hoyre);
        }
    }

}
