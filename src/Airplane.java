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
    
    //Constructor
    public Airplane(Airport ap, boolean emergency) {
        this.id = nextId++;
        this.ap = ap;
        this.emergency = emergency;
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
    
    @Override
    public void run() {
        System.out.printf("Airplane %d is arriving...\n", this.getId());
        ap.addPlane(this);
    }
 }

