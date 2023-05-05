/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author chery
 */
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class RefuellingTruck {
    
    private final ReentrantLock lock;
    private boolean refuelling = false;
    Random rand = new Random();
    
    public RefuellingTruck() {
        lock = new ReentrantLock();
    }
    
    public void refuelAirplane(Airplane ap) {
        lock.lock();
        try {
            refuelling = true;
            System.out.println("Refuelling Airplane " + ap.getId() + "...");
            Thread.sleep(rand.nextInt(3000));
            System.out.println("Refuelling Truck has finished refuelling Airplane " + ap.getId());
            refuelling = false;
            
        } catch (InterruptedException iex) {
            iex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    
    public boolean isRefuelling() {
        return this.refuelling;
    }
    
}
