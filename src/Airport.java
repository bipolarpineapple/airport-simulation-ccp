/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author wyeye
 */
import java.util.Comparator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Airport {
    
    private AirplaneGenerator ag;
    Gate gate = new Gate();
    Runway runway = new Runway();
    private final Comparator<Airplane> comparePlanes = Comparator.comparing(Airplane::isEmergency).reversed();
    private PriorityBlockingQueue<Airplane> pq = new PriorityBlockingQueue(6, comparePlanes);
    
    ReentrantLock lock = new ReentrantLock();
    Condition planeCondition = lock.newCondition();
    
    public Airport() {
    }
    
    public void addPlane(Airplane plane) {
        // add locks signal planeCondtion 
        try {
            lock.lock();
            planeCondition.signal();
            pq.offer(plane);
        } finally {
            lock.unlock();
        }

    }
    
    public void landPlanes() {
        try {
            lock.lock();
            while(gate.getCurrentCapacity() == 3 || !runway.isVacant()) {
                planeCondition.await();
            }
            
            Airplane ap = pq.poll();
            runway.landPlanes(ap);
            gate.enterGate(ap);
            
        } catch (InterruptedException iex) {
            Logger.getLogger(Gate.class.getName()).log(Level.SEVERE, null, iex);
        } finally {
            lock.unlock();
        }

    }
    
    public void refuel() {
        
        gate.refuelAirplane();
        
    }
    
    public void disembarkPassenger() {
        
        gate.disembarkPassengers();
    }
    
    public void embarkPassenger() {
        
        gate.embarkPassengers();
    }
    
    public void embarkPlanes() {
        try {
            lock.lock();
            while (!runway.isVacant()) {
                planeCondition.await();
            }
            
            Airplane ap = gate.leaveGate();
            runway.departPlanes(ap);
            planeCondition.signal();
            
        } catch (InterruptedException iex) {
            iex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    
    public void inspectGate() {
        gate.inspectGate();
    }
    
}
