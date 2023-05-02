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
        pq.offer(plane);
    }
    
    public void landPlanes() {
        try {
            lock.lock();
            while(gate.getCurrentCapacity() < 3 && !runway.isVacant()) {
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
    
    public void startWorking() {
        for (int i = 0; i < 6; i++) {
            if (!pq.isEmpty())
                this.landPlanes();
        }
    }
    
//    public void assignGate() {
//        
//        LinkedBlockingQueue<Gate> gates = new LinkedBlockingQueue<>();
//        Runway r = new Runway();
//        for (int i =0; i < NUM_GATES; i++) {
//            gates.offer(new Gate(i));
//        }
//        
//        while(!pq.isEmpty()) {
//            Airplane p = pq.poll();
//            if (p.isEmergency()) {
//                System.out.println("Emergency Plane " + p.getId() + " is landing!");
//                r.landPlanes(pq, gates);
//                break;
//            } else {
//                System.out.println("Plane " + p.getId() + " is waiting to land!");
//                r.landPlanes(pq, gates);
//            }
//        }
//        
//        while(!gates.isEmpty()) {
//            r.releasePlanes(pq, gates);
//        }
//    }
    
}
