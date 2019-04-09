package com.timing.functional;

import java.util.Date;
import com.google.common.base.Function;
import com.google.common.base.Supplier;

public class Timing {
  public static final Function<String, Void> PRINT_TO_STDOUT = 
      new Function<String, Void>() {
    @Override
    public Void apply(String s) {
      System.out.println(s);
      return null;
    }
  };
  
  public static <T> T timed(String description, Supplier<T> code) {   
    return timed(description, code, PRINT_TO_STDOUT);
  }
  
  public static <T> T timed(String description, Supplier<T> code, 
      Function<String, Void> output) {
    final Date before = new Date();
    T result = code.get();
    final Long duration = new Date().getTime() - before.getTime();
    output.apply(description + " took " + duration + " milliseconds");
    
    return result;
  }
}
