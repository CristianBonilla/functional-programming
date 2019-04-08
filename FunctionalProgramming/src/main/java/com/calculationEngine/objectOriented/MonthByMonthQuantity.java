package com.calculationEngine.objectOriented;

public abstract class MonthByMonthQuantity implements QuantityOfInterest {
  private final double[] values;
  
  protected MonthByMonthQuantity(final double[] values) {
    this.values = values;
  }
  
  public double valueAt(final int time) {
    return values[time - 1];
  }
}
