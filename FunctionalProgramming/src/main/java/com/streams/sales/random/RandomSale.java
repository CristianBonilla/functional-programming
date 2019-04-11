package com.streams.sales.random;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.streams.sales.Item;
import com.streams.sales.Sale;
import com.streams.sales.Store;
import static com.streams.sales.random.RandomUtil.randomElement;

// para generar venta aleatoria
public class RandomSale {
  private static Random random = new Random();
  private static final int MAX_ITEMS = 6;
  private static final Double PERCENT_NO_CUSTOMER = 0.25;
  private static final String[] CUSTOMERS = {
      "Wilma", "Betty", "Fred", "Barney", "Dino"};
  
  public static Supplier<Sale> supplier = () -> new Sale(
      pickAStore(),
      new Date(),
      pickACustomer(),
      randomListOfItems());
  
  public static Stream<Sale> streamOf(long limit) {
    return Stream.generate(supplier).limit(limit);    
  }
  
  private static List<Item> randomListOfItems() {
    int howMany = random.nextInt(MAX_ITEMS) + 1;    
    return RandomItems.infiniteStream()
        .limit(howMany)
        .collect(Collectors.toList());
  }
  
  private static Optional<String> pickACustomer() {
    if (random.nextDouble() < PERCENT_NO_CUSTOMER) {
      return Optional.empty();
    } else {
      return Optional.of(randomElement(CUSTOMERS));
    }
  }
  
  private static Store pickAStore() {
    return randomElement(Store.values());
  }
}
