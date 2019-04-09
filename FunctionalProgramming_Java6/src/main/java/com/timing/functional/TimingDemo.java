package com.timing.functional;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.google.common.base.Supplier;
import static com.timing.functional.Timing.timed;

public class TimingDemo {
  private static final Random random = new Random();
  private static final Integer MAX_WORK_TIME_MS = 2000;
  private static final Logger logger = LogManager.getLogger(
      TimingDemo.class.getName());

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
    final Double costs = timed("Costs calculation", 
        new Supplier<Double>() {
          @Override
          public Double get() {
            return calculateCosts();
          }
        });
    final Double revenue = timed("Revenue calculation",
        new Supplier<Double>() {
          @Override
          public Double get() {
            return calculateRevenue();
          }
        });
    final Double profit = timed("Profit calculation",
        new Supplier<Double>() {
          @Override
          public Double get() {
            return calculateProfit(costs, revenue);
          }
        },
        LoggerFunctions.info(logger));
    
    System.out.println("Profit = $" + profit);
  }
}
