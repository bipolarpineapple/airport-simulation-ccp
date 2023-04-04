/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author chery
 */

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Airplane extends Thread {
    
    private long id;
    private int gateNumber;
    private boolean landed;
    Passenger passenger;
    ControlTower control;
    RefuellingTruck refuel;
    SanityCheck check;
    private Random random;
    
    //Constructor
    public Airplane(long id) {
        this.id = id;
        this.random = new Random();
    }
        
    @Override
    public void run() {
        try {
            Thread.sleep(random.nextInt(3000)); //wait for a random tiem to simulate arrival time
            System.out.println("Airplane " + id + " has arrived.");
            control.getInstance().notifyPilot(this); //notify control tower
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    //getter and setter
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public int getGateNumber() {
        return gateNumber;
    }
    
    public void setGateNumber(int gateNumber) {
        this.gateNumber = gateNumber;
    }
    
    public boolean getLanded() {
        return landed;
    }
    
    public void setLanded(boolean landed) {
        this.landed = landed;
    }
 }

