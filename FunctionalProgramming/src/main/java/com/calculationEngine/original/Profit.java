package com.calculationEngine.original;

// projected profit
public class Profit implements QuantityOfInterest {
  private final Sales sales;
  private final FixedCosts fixedCosts;
  private final IncrementalCosts incrementalCosts;
  
  public Profit(
      final Sales sales,
      final FixedCosts fixedCosts,
      final IncrementalCosts incrementalCosts) {
    this.sales = sales;
    this.incrementalCosts = incrementalCosts;
    this.fixedCosts = fixedCosts;
  }
  
  public String getName() {
    return "Profit!!";
  }

  @Override
  public double valueAt(int time) {
    return sales.valueAt(time) - (fixedCosts.valueAt(time) + 
        incrementalCosts.valueAt(time));
  }
}
