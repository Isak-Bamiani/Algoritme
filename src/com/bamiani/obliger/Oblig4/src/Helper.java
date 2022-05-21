public class Helper {

    // Hvis ord2 er alfabetisk mindre enn ord1, returner false
    public static Boolean sammenlignord(String ord1, String ord2 ) {
        Boolean verdi = false;
        if ( ord2.compareTo( ord1 ) < 0 ) {
            verdi = true;
        }
        return verdi;
    }

}