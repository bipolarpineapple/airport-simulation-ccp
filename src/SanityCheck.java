
import java.text.DecimalFormat;
import java.time.LocalTime;
import java.time.Duration;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author chery
 */
public class SanityCheck {
    
    static int totalPlanesServed;
    static ArrayList<SanityCheck> allSanityChecks = new ArrayList<>();
    private static final DecimalFormat df = new DecimalFormat("0.00");
    
    private Airplane ap;
    private String planeName;
    private int waitingTime;
    private int totalPassengersEmbarked;
    private int totalPassengersDisembarked;
    
    public SanityCheck(String planeName) {
        this.planeName = planeName;
    }
       
    public void calculateWaitingTime(LocalTime startTime, LocalTime endTime) {
        Duration duration = Duration.between(startTime, endTime);
        
        this.waitingTime = (int) duration.getSeconds();
    }
    
    public void recordTotalPassengerEmbarked(int totalPassengersEmbarked) {
        this.totalPassengersEmbarked = totalPassengersEmbarked;
    }
    
    public void recordTotalPassengerDisembarked(int totalPassengersDisembarked) {
        this.totalPassengersDisembarked = totalPassengersDisembarked;
    }
    
    public int getWaitingTime() {
        return this.waitingTime;
    }
    
    public String getPlaneName() {
        return this.planeName;
    }
    
    public int getTotalPassengersEmbarked() {
        return this.totalPassengersEmbarked;
    }
    
    public int getTotalPassengersDisembarked() {
        return this.totalPassengersDisembarked;
    }
    
    static void printAllSanityChecks() {
        int maximumWaitingTime = 0;
        
        int minimumWaitingTime = allSanityChecks.get(0).getWaitingTime(); // first sanity check's waiting time
        int totalWaitingTime = 0;
        double averageWaitingTime;
        int totalEmbarkedPassengers = 0;
        int totalDisembarkedPassengers = 0;
        
        // calculate max, min and average waiting time here (1st for-loop)
        for (SanityCheck planeStatistics:allSanityChecks) {
            int currentWaitingTime = planeStatistics.getWaitingTime();
            
            
            System.out.println("Waiting Time for " + planeStatistics.getPlaneName() + ": " + currentWaitingTime + " seconds");
            System.out.println(planeStatistics.getTotalPassengersDisembarked() + " passengers have disembarked from the airplanes...");
            System.out.println(planeStatistics.getTotalPassengersEmbarked() + " passengers have embarked the airplanes...");
            
            if (currentWaitingTime > maximumWaitingTime) {
                maximumWaitingTime = currentWaitingTime;
                
            } else if (currentWaitingTime < minimumWaitingTime) {
                minimumWaitingTime = currentWaitingTime;
                
            }
             
            totalWaitingTime += currentWaitingTime;
            totalEmbarkedPassengers += planeStatistics.getTotalPassengersEmbarked();
            totalDisembarkedPassengers += planeStatistics.getTotalPassengersDisembarked();
            
        }
        
        averageWaitingTime = (double) totalWaitingTime / totalPlanesServed;
        
        // print all the stats recorded here 
        System.out.println("====================================================");
        System.out.println("Overall Statistics");
        System.out.println("----------------------------------------------------");
        System.out.println("Maximum Waiting Time for Airplanes: " + maximumWaitingTime + " seconds");
        System.out.println("Minimum Waiting Time for Airplanes: " + minimumWaitingTime + " seconds");
        System.out.println("Average Waiting Time for Airplanes: " + df.format(averageWaitingTime) + " seconds" + "\n");
        System.out.println("Total airplanes served: " + totalPlanesServed + " airplanes");
        System.out.println("Total passengers embarked: " + totalEmbarkedPassengers + " passengers");
        System.out.println("Total passengers disembarked: " + totalDisembarkedPassengers + " passengers");
    }
    
}
