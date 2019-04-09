package com.examples;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntToDoubleFunction;

public class MinimumLambda {
  public static void main(String[] args) {
    IntToDoubleFunction incrementalCosts = time -> 5.1 + 0.5 * time;
    Function<String, Integer> wordCount = (String s) -> s.split("").length;
    BiFunction<String, Integer, Boolean> exceedsMaxLength = (s, maxLength) -> {
      int actualLength = s.length();
      return actualLength > maxLength;
    };
    
    System.out.println(incrementalCosts.applyAsDouble(5));
    System.out.println(wordCount.apply("Test Function Generic"));
    System.out.println(exceedsMaxLength.apply("ExpressionLambda", 10));
  }
}
