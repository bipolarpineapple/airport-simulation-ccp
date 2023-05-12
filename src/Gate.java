
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
 
    //record the maximum capacity of the airport gates
    private int maxCapacity = 3;
    
    //count the current capacity
    private int currentCapacity;
    
    // gate ids
    ArrayBlockingQueue<String> gateIds = new ArrayBlockingQueue<>(3, true);
    
    //Blocking Queues for airplanes within situations while in gate
    ArrayBlockingQueue<Airplane> airplaneQueue = new ArrayBlockingQueue<>(3);
    ArrayBlockingQueue<Airplane> embarkedAirplaneQueue = new ArrayBlockingQueue<>(3);
    ArrayBlockingQueue<Airplane> embarkingAirplaneQueue = new ArrayBlockingQueue<>(3);
    ArrayBlockingQueue<Airplane> refuellingAirplaneQueue = new ArrayBlockingQueue<>(3);
    
    //To lock the gates
    ReentrantLock lock = new ReentrantLock();
    Condition gateCondition = lock.newCondition();
    Random rand = new Random();
    RefuellingTruck rt = new RefuellingTruck();
    ControlTower ct = new ControlTower();
    private int durationGate;
    private int durationInspect;
    
    public Gate() {
        for (int i = 1; i <= 3; i++) {
            gateIds.offer(String.valueOf(i));
        }
    }
    
    //getter and setter for capacity
    public int getCurrentCapacity() {
        return currentCapacity;
    }
    
    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }
    
    public void enterGate(Airplane ap) {
        try {
            //lock the gate
            lock.lock();
            //increase the current capacity of the airport gates
            currentCapacity++;
            
            //Offer the airplane into the airplaneQueue
            String enterGateId = gateIds.poll();
            ap.enterGate(enterGateId);
            
            airplaneQueue.offer(ap);
            durationGate = rand.nextInt(3);
            TimeUnit.SECONDS.sleep((long)durationGate);
            System.out.println("Airplane " + ap.getId() + " is entering Gate " + ap.getEnteredGate() + "...");
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Gate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            lock.unlock();
        }
    }
   
    public void disembarkPassengers() {
        Airplane ap = airplaneQueue.poll();
        ap.disembarkPassengers();
        refuellingAirplaneQueue.offer(ap);
    }
    
    public void embarkPassengers() {
        Airplane ap = embarkingAirplaneQueue.poll();
        ap.embarkPassengers();
        embarkedAirplaneQueue.offer(ap);
    }
    
    public void refuelAirplane() {
        Airplane ap = refuellingAirplaneQueue.poll();
        rt.refuelAirplane(ap);
        ct.cleanAirplane(ap);
        ct.refillSupplies(ap);
        embarkingAirplaneQueue.offer(ap);
    }
    
    public Airplane leaveGate() {
        try {
            lock.lock();
            Airplane ap = embarkedAirplaneQueue.poll();
            String gateEnterId = ap.getEnteredGate();
            System.out.println("Airplane " + ap.getId() + " is leaving from Gate " + gateEnterId + " and is going for take off...");
            gateIds.offer(gateEnterId);
            
            currentCapacity--;
            return ap;
        } finally {
            lock.unlock();
        }

    }
    
}
