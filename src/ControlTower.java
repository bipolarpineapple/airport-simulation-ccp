/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author chery
 */
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class ControlTower extends Thread {
    
    private static ControlTower instance;
    private final BlockingQueue<Airplane> airplanes;
    private final List<Integer> vacantGates;
    private final Random random;
    
    private ControlTower(BlockingQueue<Airplane> airplanes){
        this.airplanes = airplanes;
        this.vacantGates = new ArrayList<>();
        this.random = new Random();
        for (int i = 1; i <= 5; i++) {
            vacantGates.add(i);
        }
    }
    
    public static ControlTower getInstance() {
        if (instance == null) {
            instance = new ControlTower(new LinkedBlockingQueue<>());
        }
        return instance;
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                Airplane airplane = airplanes.take();
                int gateNumber = assignGate();
                airplane.setGateNumber(gateNumber);
                airplane.setLanded(true);
                System.out.println("Airplane " + airplane.getId() + " has "
                        + "landed on gate " + gateNumber);
                //RefuellingTruck.getInstance().refuelAirplane(airplane);
                //airplane.setRefueled(true);
                ControlTower.getInstance().notifyBoarding(airplane);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public synchronized void notifyPilot(Airplane airplane) {
        airplanes.add(airplane);
        System.out.println("Airplane " + airplane.getId() + " has joined the queue.");
    }
    
    public synchronized void notifyBoarding(Airplane airplane) {
        System.out.println("Airplane " + airplane.getId() + " is ready for boarding!");
    }
    
    private int assignGate() throws InterruptedException {
        while (vacantGates.isEmpty()) {
            wait();
        }
        int gateNumber = vacantGates.remove(0);
        return gateNumber;
    }
    
    public synchronized void releaseGate(int gateNumber) {
        vacantGates.add(gateNumber);
        notify();
    }
    
    
}
