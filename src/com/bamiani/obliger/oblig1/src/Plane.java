import java.util.Random;

public class Plane {

    private static int planes = 1;

    private int name;
    private int wait_time = 0;

    Plane() {
        // Generate plane name
        this.planes++;
        this.name = this.planes;
    }

    void land() {
        System.out.println( "   Plane " + this.name + " landing, wait time: " + this.wait_time );
    }

    void takeoff() {
        System.out.println( "   Plane " + this.name + " taking off, wait time: " + this.wait_time );
    }

    void reject() {
        System.out.println( "   Plane " + this.name + " rejected, land on another airport." );
    }

    void incrementWaitTime() {
        this.wait_time++;
    }

    int getWaitTime() {
        return this.wait_time;
    }

}
