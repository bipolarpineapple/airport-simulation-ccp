/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author chery
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Airport ap = new Airport();
        SanityCheck sc = new SanityCheck();
        
        System.out.println("ATC manager is checking the airport's condition...");
        System.out.println("Airplanes are safe to land...");
        
        //Call 6 airplanes: 5 normal flights, 1 with emergency landing
        for (int i = 1; i <= 6; i++) {
            AirplaneGenerator ag;
            
            if (i == 6) {
                ag = new AirplaneGenerator(ap, "Emergency Aeroplane + " + i, true);
                
            } else {
                ag = new AirplaneGenerator(ap, "Aeroplane + " + i, false);
                
            }
            
            ag.start();
        }
        
    }
    
}
