package com.calculationEngine.original;

public interface QuantityOfInterest {
  public String getName();
  
  /**
   * expected value for a particular month
   * @param time month, 1-12
   * @return expected value
   */
  public double valueAt(final int time);
}
