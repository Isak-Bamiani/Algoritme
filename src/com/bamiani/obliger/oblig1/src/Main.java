import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

public class Main {

    // will be used to generate random planes that will land and takeoff
    private static int getPoissonRandom(double mean) {
        Random r = new Random();
        double L = Math.exp(-mean);
        int k = 0;
        double p = 1.0;
        do {
            p = p * r.nextDouble();
            k++;
        } while (p > L);
        return k - 1;
    }
    public static void main( String args[] ) {
        // Get program parameters from user, using a scanner
        Scanner s = new Scanner(System.in);
        System.out.println("How many units of time should the program run?: ");
        int timeInterval = Integer.parseInt( s.nextLine() );
        System.out.println("What should the ratio( ratio < 1 ) between landing and takeoff be?:  ");
        float ratio = Float.parseFloat( s.nextLine() );
        float landing_ratio = ratio;
        float takeoff_ratio = 1 - ratio;

        // ----------------------------- MAIN -----------------------------------

        // Variables that save stats from main loop
        int planes_rejected = 0;
        int planes_landed = 0;
        int planes_tookoff = 0;
        int total_wait_sum = 0;
        int total_wait_amount = 0;

        int landing_wait_sum = 0;
        int landing_wait_amount = 0;

        int takeoff_wait_sum = 0;
        int takeoff_wait_amount = 0;


        // Landing and takeoff queue, with size limit variable
        int queue_limit = 10;
        Queue<Plane> LandingQ = new LinkedList<Plane>();
        Queue<Plane> TakeoffQ = new LinkedList<Plane>();

        for ( int i = 1; i <= timeInterval; i++ ) {
            System.out.println( i + ":" );

            // Generate random amount of planes that want to land
            int want_to_land = getPoissonRandom(landing_ratio);
            // Convert these numbers to Plane objects
            for ( int j = 0; j < want_to_land; j++ ) {
                Plane plane = new Plane();
                // If there is no space in the queue, reject the plane
                if ( LandingQ.size() == queue_limit ) {
                    plane.reject();
                    planes_rejected++;
                    // If there is space, add plane to queue
                } else {
                    LandingQ.add( plane );
                }
            }

            // Generate random amount of planes that want to takeoff
            int want_to_takeoff = getPoissonRandom(takeoff_ratio);
            for ( int j = 0; j < want_to_takeoff; j++ ) {
                Plane plane = new Plane();
                // If there is no more space in the takeoff queue, reject the place
                if ( TakeoffQ.size() == queue_limit ) {
                    plane.reject();
                    planes_rejected++;
                    // If there is space, add plane to queue
                } else {
                    TakeoffQ.add( plane );
                }
            }


            // If there are planes in the landing queue, let them land
            if ( LandingQ.size() != 0 ) {
                Plane plane = LandingQ.poll();
                plane.land();
                // Increment stat variables
                total_wait_sum += plane.getWaitTime();
                landing_wait_sum += plane.getWaitTime();
                total_wait_amount++;
                landing_wait_amount++;
                planes_landed++;
            }
            // Let planes takeoff if there are planes in the takeoff queue
            else if ( TakeoffQ.size() != 0 ) {
                Plane plane = TakeoffQ.poll();
                plane.takeoff();
                total_wait_sum += plane.getWaitTime();
                takeoff_wait_sum += plane.getWaitTime();
                total_wait_amount++;
                takeoff_wait_amount++;
                planes_tookoff++;
            }
            // Do nothing if no planes want to use the airport
            else {
                System.out.println("Airport is empty.");
            }

            // Increment wait time for all planes in queues for each timeinterval
            for ( Plane p : LandingQ ) {
                p.incrementWaitTime();
            }
            for ( Plane p : TakeoffQ ) {
                p.incrementWaitTime();
            }

            System.out.println("\n");
        }

        // Calculate wait times
        // Using try blocks, so to mitigate DivisionByZeroException
        float av_wait_time = 0;
        float av_takeoff_wait_time = 0;
        float av_landing_wait_time = 0;

        try {
            av_wait_time = total_wait_sum/total_wait_amount;
        } catch( Exception e ) {
            av_wait_time = 0;
        }

        try {
            av_takeoff_wait_time = takeoff_wait_sum/takeoff_wait_amount;
        } catch ( Exception e ) {
            av_takeoff_wait_time = 0;
        }

        try {
            av_landing_wait_time = landing_wait_sum/landing_wait_amount;
        } catch ( Exception e ) {
            av_landing_wait_time = 0;
        }

        // Print out stats about the simulation at the end of simulation
        System.out.println( "------------------------- |||| END |||| -------------------------" );
        System.out.println( "------------------------ |||| Stats |||| ------------------------\n" );
        System.out.println( "Simulation ended after: "  + timeInterval + " iterations" );
        System.out.println( "Total planes rejected: "   + planes_rejected );
        System.out.println( "Average wait time: " + av_wait_time );
        System.out.println( "Average wait time for takeoff " + av_takeoff_wait_time );
        System.out.println( "Average wait time for landing " + av_landing_wait_time );
        System.out.println( "Total planes that landed: "    + planes_landed );
        System.out.println( "Total planes that took off: "  + planes_tookoff );

    }







}
