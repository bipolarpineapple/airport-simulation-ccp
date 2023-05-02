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
        
        for (int i = 1; i <= 6; i++) {
            AirplaneGenerator ag;
            
            if (i == 6) {
                ag = new AirplaneGenerator(ap, "Aeroplane + " + i, true);
                
            } else {
                ag = new AirplaneGenerator(ap, "Aeroplane + " + i, false);
                
            }
            
            ag.start();
        }
        
    }
    
}
