package com.calculationEngine.objectOriented;

public class IncrementalCosts extends PolynomialQuantity {
  public IncrementalCosts(
      final double intercept,
      final double slope) {
    super(new double[] { intercept, slope });
  }
  
  @Override
  public String getName() {
    return "Incremental Costs";
  }
}
