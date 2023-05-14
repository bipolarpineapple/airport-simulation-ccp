
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//
///**
// *
// * @author chery
// */

public class ControlTower {

    private Airport ap;
    private Random rand = new Random();
    
    public ControlTower(){
        
    }
    
    // Clean airplane action
    public void cleanAirplane(Airplane ap) {
        try {
            System.out.println("Control Tower has cued cleaners to clean Airplane "  + ap.getId() + "...");
            Thread.sleep(rand.nextInt(1000));
            System.out.println("Cleaners have started cleaning Airplane " + ap.getId() + "...");
            Thread.sleep(rand.nextInt(1500));
            System.out.println("Cleaners have finished cleaning Airplane " + ap.getId() + "...");
        } catch (InterruptedException ex) {
            Logger.getLogger(ControlTower.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Refill airplane action
    public void refillSupplies(Airplane ap) {
        try {
            System.out.println("Control Tower has cued suppliers to refill Airplane " + ap.getId() + "...");
            Thread.sleep(rand.nextInt(1000));
            System.out.println("Suppliers have started refilling Airplane " + ap.getId() + "...");
            Thread.sleep(rand.nextInt(1200));
            System.out.println("Suppliers have finished refilling Airplane " + ap.getId() + "...");
        } catch (InterruptedException ex) {
            Logger.getLogger(ControlTower.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
