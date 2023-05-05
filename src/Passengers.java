/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author chery
 */
public class Passengers {
    
    Airplane ap;
    private final int PASSENGER_COUNT = 50;
    
    public Passengers(Airplane ap) {
        this.ap = ap;
    }
    
    public void embark() {
        for (int i = 1; i <= this.PASSENGER_COUNT; i++) {
            try {
                System.out.println("Passenger " + i + " has embarked the Airplane " + ap.getId());
                Thread.sleep(50);
            } catch (InterruptedException iex) {
                iex.printStackTrace();
            }
        }
    }
    
    public void disembark() {
        for (int i = 1; i <= this.PASSENGER_COUNT; i++) {
            try {
                System.out.println("Passenger " + i + " has disembarked from Airplane " + ap.getId());
                Thread.sleep(50);
            } catch (InterruptedException iex) {
                iex.printStackTrace();
            }

            
        }
    }
}
