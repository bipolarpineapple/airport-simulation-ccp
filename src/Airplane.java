
import java.time.LocalTime;
import java.util.ArrayList;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author chery
 */

public class Airplane extends Thread {
    
    private static int nextId = 1;
    private final int id;
    private final boolean emergency;
    private Airport ap;
    private final Passengers ps;
    private SanityCheck sc;
    private String gateIdEntered;
    
    private LocalTime startTime;
    private LocalTime endTime;
    
    //Constructor
    public Airplane(Airport ap, boolean emergency) {
        this.id = nextId++;
        this.ap = ap;
        this.emergency = emergency;
        this.ps = new Passengers(this);
        this.sc = new SanityCheck("Airplane " + this.id);
    }
           
    //getter and setter
    @Override
    public long getId() {
        return id;
    }
    
    public boolean isEmergency() {
        return emergency;
    }
    
    public Airport getAirport() {
        return ap;
    }
    
    public void setAirport(Airport airport) {
        this.ap = airport;
    }
    
    public void disembarkPassengers() {
        this.ps.disembark();
    }
    
    public void embarkPassengers() {
        this.ps.embark();
    }
    
    public void enterGate(String gateId) {
        this.gateIdEntered = gateId;
    }
    
    public String getEnteredGate() {
        return gateIdEntered;
    }
    
    public void recordPassengersEmbarked(int passengerCount) {
        sc.recordTotalPassengerEmbarked(passengerCount);
    }
    
    public void recordPassengersDisembarked(int passengerCount) {
        sc.recordTotalPassengerDisembarked(passengerCount);
    }
    
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
        
        // calculate waiting time and record
        sc.calculateWaitingTime(startTime, endTime);
    }
    
    public SanityCheck getStatistics() {
        return sc;
    }
    
    @Override
    public void run() {
        // if the airplane requires emergency landing
        if (emergency == true) {
            System.out.printf("Airplane %d is arriving with emergency landing required...\n", this.getId());
        // if the airplane is a normal flight
        } else {
            System.out.printf("Airplane %d is arriving...\n", this.getId());
        }
        // start timing for waiting time per airplane
        this.startTime = LocalTime.now();
        // add the plane into the queue
        ap.addPlane(this);
        
        // Series of actions to be done per airplane
        Thread instruction = new Thread(() -> {
           
            ap.landPlanes();
            ap.disembarkPassenger();
            ap.refuel();
            ap.embarkPassenger();
            ap.embarkPlanes();
        }); 
        instruction.start();
    }
    
    
 }

