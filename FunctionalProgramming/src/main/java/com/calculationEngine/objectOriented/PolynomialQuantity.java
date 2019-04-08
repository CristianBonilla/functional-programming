package com.calculationEngine.objectOriented;

public abstract class PolynomialQuantity implements QuantityOfInterest {
  private final double[] coefficients;
  
  protected PolynomialQuantity(final double[] coefficients) {
    this.coefficients = coefficients;
  }

  public double valueAt(int time) {
    double value = 0.0;
    for (int i = 0; i < coefficients.length; i++) {
      value += coefficients[i] * Math.pow(time, i);
    }
    return value;
  }
}
