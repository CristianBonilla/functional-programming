package com.timing.original;

import java.util.Date;
import java.util.Random;

public class TimingDemo {
  private static final Random random = new Random();
  private static final Integer MAX_WORK_TIME_MS = 2000;
  
  private static void pretendToWorkHard() {
    try {
      Thread.sleep(random.nextInt(MAX_WORK_TIME_MS));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }  
  
  private static double calculateCosts() {
    pretendToWorkHard();
    return 4567.3;
  }
  
  private static double calculateRevenue() {
    pretendToWorkHard();
    return 23413.2;
  }

  private static double calculateProfit(double costs, double revenue) {
    pretendToWorkHard();
    return revenue - costs;
  }

  public static void main(String[] args) {
    final Date beforeCosts = new Date();
    final double costs = calculateCosts();
    final long costsDuration = new Date().getTime() - beforeCosts.getTime();
    System.out.println("Cost calculation took " + costsDuration + " ms");
    
    final Date beforeRevenue = new Date();
    final double revenue = calculateRevenue();
    final long revenueDuration = new Date().getTime() - beforeRevenue.getTime();
    System.out.println("Revenue calculation took " + revenueDuration + " ms");
    
    final Date beforeProfit = new Date();
    final double profit = calculateProfit(costs, revenue);
    final long profitDuration = new Date().getTime() - beforeProfit.getTime();
    System.out.println("Profit calculation took " + profitDuration + " ms");
    
    System.out.println("Profit = $" + profit);
  }
}
