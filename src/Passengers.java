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
    // Generate random number of passengers within the range of 50
    private final int PASSENGER_COUNT = rand.nextInt(50);
    private int duration;
    
    // Constructor
    public Passengers(Airplane ap) {
        this.ap = ap;
    }
    
    // Embark passengers
    public void embark() {
        for (int i = 1; i <= this.PASSENGER_COUNT; i++) {
            try {
                System.out.println("Passenger " + i + " has embarked the Airplane " + ap.getId());
                duration = 400;
                Thread.sleep(duration);
            } catch (InterruptedException iex) {
                iex.printStackTrace();
            }
        }
        // Record the number of passengers embarked
        ap.recordPassengersEmbarked(PASSENGER_COUNT);
    }
    
    // Disembarked passengers
    public void disembark() {
        for (int i = 1; i <= this.PASSENGER_COUNT; i++) {
            try {
                System.out.println("Passenger " + i + " has disembarked from Airplane " + ap.getId());
                duration = 400;
                Thread.sleep(duration);
            } catch (InterruptedException iex) {
                iex.printStackTrace();
            }

            
        }
        // Record the number of passengers disembarked
        ap.recordPassengersDisembarked(PASSENGER_COUNT);
    }
    
}
