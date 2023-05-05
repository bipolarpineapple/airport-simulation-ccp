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
    
    //Constructor
    public Airplane(Airport ap, boolean emergency) {
        this.id = nextId++;
        this.ap = ap;
        this.emergency = emergency;
        this.ps = new Passengers(this);
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
    
    @Override
    public void run() {
        if (emergency == true) {
            System.out.printf("Airplane %d is arriving with emergency landing required...\n", this.getId());
        } else {
            System.out.printf("Airplane %d is arriving...\n", this.getId());
        }
        ap.addPlane(this);
//        ap.landPlanes();
        
        Thread instruction = new Thread(() -> {
            ap.landPlanes();
            ap.disembarkPassenger();
            ap.refuel();
            ap.embarkPassenger();
            ap.embarkPlanes();
            // add more operations
        });
        
        instruction.start();
    }
    
    
 }

