package com.timing.functional;

import java.util.Date;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Timing {  
  public static <T> T timed(String description, Supplier<T> code) {
    // Consumer<String> defaultOutput = System.out::println;
    // change default to do nothing
    final Consumer<String> defaultPrinter = s -> { };
    
    return timed(description, code, defaultPrinter);
  }
  
  // isolation (output)
  // generic types
  public static <T> T timed(String description, Supplier<T> code, 
      Consumer<String> output) {
    final Date before = new Date();
    T result = code.get();
    final Long duration = new Date().getTime() - before.getTime();
    // Test Unit 
    output.accept(description + " took " + duration + " milliseconds");
    
    return result;
  }
  
//  public static <T> T timed(String description, Supplier<T> code) {
//    final Date before = new Date();
//    T result = code.get();
//    final Long duration = new Date().getTime() - before.getTime();
//    System.out.println(description + " took " + duration + " milliseconds");
//    
//    return result;
//  }  
  
//  public static double timed(String description, Supplier<Double> code) {
//    final Date before = new Date();
//    final double result = code.get();
//    final long duration = new Date().getTime() - before.getTime();
//    System.out.println(description + " took " + duration + " milliseconds");
//    
//    return result;
//  }
  
//  deduplicate
//  public static double timed(String description, Supplier<Double> code) {
//    final Date before = new Date();
//    final double result = code.get();
//    final long duration = new Date().getTime() - before.getTime();
//    System.out.println(description + " took " + duration + " ms");
//    
//    return result;
//  }  
}
