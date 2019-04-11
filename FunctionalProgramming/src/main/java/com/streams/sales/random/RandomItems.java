package com.streams.sales.random;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;
import com.streams.sales.Item;
import static com.streams.sales.random.RandomUtil.randomElement;

public class RandomItems {
  private Random random = new Random();
  private static final double MAX_PRICE = 150.00;
  private static final String[] AVAILABLE_ITEMS = {
    "carrot", "eggs", "lizard", "cookie", "pickle", "cow", "row"};
  
  public static Stream<Item> infiniteStream() {
    return Stream.generate(new RandomItems().itemSupplier);
  }
  
  private Supplier<Item> itemSupplier = () ->
    new Item(pickAnIdentity(), pickAPrice());
  
  private double pickAPrice() {
    // might be free
    return Math.round(random.nextDouble() *  MAX_PRICE * 100) / 100.00;
  }
    
  private String pickAnIdentity() {
    return randomElement(AVAILABLE_ITEMS);
  }
}
