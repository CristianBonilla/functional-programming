package com.timing.before;

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
  
  private static double calculateProfit(Double costs, Double revenue){
    pretendToWorkHard();
    return revenue - costs;
  }
  
  public static void main(String[] args) {
    final double costs = calculateCosts();
    final double revenue = calculateRevenue();
    final double profit = calculateProfit(costs, revenue);

    System.out.println("Profit = $" + profit);
  }
}
