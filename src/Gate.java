
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author wyeye
 */

public class Gate {
 
    private int maxCapacity = 3;
    private int currentCapacity;
    ArrayBlockingQueue<Airplane> airplaneQueue = new ArrayBlockingQueue<>(3);
    
    ReentrantLock lock = new ReentrantLock();
    Condition gateCondition = lock.newCondition();
    Random rand = new Random();
        
    public int getCurrentCapacity() {
        return currentCapacity;
    }
    
    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }
    
    public void enterGate(Airplane ap) {
        try {
            lock.lock();
            currentCapacity++;
            airplaneQueue.offer(ap);
            TimeUnit.SECONDS.sleep((long)rand.nextInt(3));
            System.out.println("Airplane " + ap.getName() + " is entering a gate...");
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Gate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            lock.unlock();
        }
    }
    
}