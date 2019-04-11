package com.streams.sales.random;

import java.util.Random;

public class RandomUtil {
  private static Random random = new Random();
  
  public static <T> T randomElement(T[] elements) {
    int randomStore = random.nextInt(elements.length);    
    return elements[randomStore];
  } 
}
