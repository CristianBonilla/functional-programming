package com.calculationEngine.functional;

public class Sales implements QuantityOfInterest {
  private final FunctionOverTime valueFunction;
  
  public Sales(FunctionOverTime valueFunction) {
    this.valueFunction = valueFunction;
  }
  
  public String getName() {
    return "Sales";
  }
  
  public double valueAt(int time) {
    return valueFunction.valueAt(time);
  }
}
