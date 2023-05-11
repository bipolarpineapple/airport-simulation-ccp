/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author wyeye
 */
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AirplaneGenerator extends Thread {

    private final Random rand = new Random();
    private final Airport airport;
    private final String planeName;
    private final boolean emergencyLanding;
    private int duration;
    private int durationCounter;

    public AirplaneGenerator(Airport airport, String planeName, boolean emergencyLanding) {
        this.airport = airport;
        this.planeName = planeName;
        this.emergencyLanding = emergencyLanding;
    }
    
    @Override
    public void run() {
        try {
            duration = rand.nextInt(4);
            TimeUnit.SECONDS.sleep((long) duration);
            
        } catch (InterruptedException iex) {
            iex.printStackTrace();
        }
        
        Airplane airplane = new Airplane(airport, emergencyLanding);
        airplane.start();


    }

}
