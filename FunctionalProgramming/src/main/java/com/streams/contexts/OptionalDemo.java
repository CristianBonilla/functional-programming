package com.streams.contexts;

import java.util.Optional;
import java.util.stream.Stream;
import com.streams.sales.Sale;
import com.streams.sales.Store;
import com.streams.sales.random.RandomSale;

public class OptionalDemo {
  private static Stream<Sale> saleStream() {
    return RandomSale.streamOf(3);
  }
  
  private static Optional<Sale> findSaleOf(String itemName) {
    return saleStream().filter(sale ->
          sale.items.stream().anyMatch(item -> item.identity.equals(itemName)))
        .findFirst();
  }
  
  // Customer who bought a carrot
  private static Optional<String> carrotCustomer() {
    return findSaleOf("carrot").flatMap(sale -> sale.customer);
  }
  
  // Store that sold a carrot
  private static Optional<Store> carrotStore() {
    return findSaleOf("carrot").map(sale -> sale.store);
  }
  
  public static void main(String[] args) {
    System.out.println("Who bought a carrot? " + carrotCustomer()
      .orElse("I can't say."));
    
    System.out.println(carrotStore());
  }
}
