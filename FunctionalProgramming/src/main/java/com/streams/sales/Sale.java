package com.streams.sales;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Sale {
  public final Store store;
  public final Date date;
  public final Optional<String> customer;
  public final List<Item> items;
  
  public Sale(Store store, Date date, Optional<String> customer, List<Item> items) {
    this.store = store;
    this.date = date;
    this.customer = customer;
    this.items = items;
  }
  
  public double total() {
    return items.stream().mapToDouble(i -> i.price).sum();
  }
}
