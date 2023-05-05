///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//
///**
// *
// * @author wyeye
// */
//
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;


public class Runway {
    
    private final ReentrantLock lock;
    private boolean isVacant = true;
    Random rand = new Random();
    
    public Runway() {
        lock = new ReentrantLock();
    }
    
    public void landPlanes(Airplane ap) {
        lock.lock();
        try {
            TimeUnit.SECONDS.sleep((long)rand.nextInt(3));
            System.out.println("Airplane " + ap.getId() + " is landing on the runway..");
            isVacant = false;
            
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("Airplane " + ap.getId() + " has left the runway...");
            isVacant = true;
        }
    }
    
    public void departPlanes(Airplane ap) {
        lock.lock();
        try {
            isVacant = false;
            TimeUnit.SECONDS.sleep((long)rand.nextInt(3));
            System.out.println("Airplane " + ap.getId() + " has departed through the runway...");
            isVacant = true;
            
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
        
    public boolean isVacant() {
        return this.isVacant;
    }
}
