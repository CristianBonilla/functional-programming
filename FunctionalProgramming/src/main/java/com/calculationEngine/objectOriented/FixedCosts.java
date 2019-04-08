package com.calculationEngine.objectOriented;

public class FixedCosts extends PolynomialQuantity {
  public FixedCosts(final double constant) {
    super(new double[] { constant });
  }
  
  @Override
  public String getName() {
    return "Fixed Costs";
  }
}
