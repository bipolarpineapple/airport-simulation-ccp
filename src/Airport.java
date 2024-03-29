/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author wyeye
 */
import java.time.LocalTime;
import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Airport {
    
    private AirplaneGenerator ag;
    Gate gate = new Gate();
    Runway runway = new Runway();
    
    // PriorityBlockingQueue and comparator to compare the priorities based on the emergency status
    private final Comparator<Airplane> comparePlanes = Comparator.comparing(Airplane::isEmergency).reversed();
    private PriorityBlockingQueue<Airplane> pq = new PriorityBlockingQueue(6, comparePlanes);
    
    private final int totalPlanesScheduled;
    private int totalPlanesServed = 0;
    
    ReentrantLock lock = new ReentrantLock();
    Condition planeCondition = lock.newCondition();
    
    // Constructor for Airport
    public Airport(int totalPlanesScheduled) {
        this.totalPlanesScheduled = totalPlanesScheduled;
    }
    
    // Add airplane into the PriorityBlockingQueue fot waiting purposes 
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
    
    // Queue controlloer for Airplanes
    // When the gates are full or the runway is being used by other airplanes, the airplanes within the queue shall wait
    public void landPlanes() {
        try {
            lock.lock();
            while(gate.getCurrentCapacity() == 3 || !runway.isVacant()) {
                planeCondition.await();
            }
            // if the one of the gates are vacant or the runway is vacant
            // remove the airplane from the queue and end the wait timer
            Airplane ap = pq.poll();
            ap.setEndTime(LocalTime.now());
            
            // The airplane shall start landing and entering to the assigned gate
            runway.landPlanes(ap);
            gate.enterGate(ap);
            
        } catch (InterruptedException iex) {
            Logger.getLogger(Gate.class.getName()).log(Level.SEVERE, null, iex);
        } finally {
            lock.unlock();
        }

    }
    
    // To refuel the airplane
    public void refuel() {
        
        gate.refuelAirplane();
        
    }
    
    // To disembark the passengers within the plane
    public void disembarkPassenger() {
        
        gate.disembarkPassengers();
    }
    
    // To embark passengers to the plane
    public void embarkPassenger() {
        
        gate.embarkPassengers();
    }
    
    // If the runway is not vacant, the yet to depart plane has to wait
    // After departing from the runway, statistics will be displayed
    public void embarkPlanes() {
        try {
            lock.lock();
            while (!runway.isVacant()) {
                planeCondition.await();
            }
            
            Airplane ap = gate.leaveGate();
            runway.departPlanes(ap);
            totalPlanesServed++;
            SanityCheck.totalPlanesServed++;
            SanityCheck.allSanityChecks.add(ap.getStatistics());

            if (totalPlanesServed == totalPlanesScheduled) {
                // if the plane is the last plane 
                
                SanityCheck.printAllSanityChecks();
                
                if (gate.getCurrentCapacity() == 0) {
                    System.out.println("Available gate count: " + (3 - gate.getCurrentCapacity()));
                    System.out.println("The gates are all empty!");
                    
                }
            }
            
            planeCondition.signal();
            
        } catch (InterruptedException iex) {
            iex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
