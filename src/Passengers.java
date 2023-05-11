/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author chery
 */
import java.util.Random;

public class Passengers {
    
    private final Random rand = new Random();
    Airplane ap;
    private final int PASSENGER_COUNT = rand.nextInt(50);
   // private int totalPassengers;
    private int duration;
    
    public Passengers(Airplane ap) {
        this.ap = ap;
    }
    
//    public int getTotalPassengers() {
//         return totalPassengers;
//    }
    
    public void embark() {
        for (int i = 1; i <= this.PASSENGER_COUNT; i++) {
            try {
                System.out.println("Passenger " + i + " has embarked the Airplane " + ap.getId());
                duration = 400;
                Thread.sleep(duration);
                //totalPassengers++;
            } catch (InterruptedException iex) {
                iex.printStackTrace();
            }
        }
    }
    
    public void disembark() {
        for (int i = 1; i <= this.PASSENGER_COUNT; i++) {
            try {
                System.out.println("Passenger " + i + " has disembarked from Airplane " + ap.getId());
                duration = 400;
                Thread.sleep(duration);
                //totalPassengers++;
            } catch (InterruptedException iex) {
                iex.printStackTrace();
            }

            
        }
    }
    
}
