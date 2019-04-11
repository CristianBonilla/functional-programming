package com.streams.sales;

//import java.util.Arrays;
//import java.util.Date;
import java.util.DoubleSummaryStatistics;
//import java.util.List;
//import java.util.Map;
import java.util.concurrent.ConcurrentMap;
//import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;
import com.streams.sales.random.RandomSale;

public class TodaysSalesDemo {
//  se comenta porque se usan ventas aleatorias (RandomSale)
//  private static final List<Sale> sales = Arrays.asList(
//      new Sale(Store.KANSAS_CITY, new Date(), Optional.of("Sarah"),
//          Arrays.asList(
//              new Item("carrot", 12.00))),
//      new Sale(Store.ST_LOUIS, new Date(), Optional.empty(),
//          Arrays.asList(
//              new Item("carrot", 12.00),
//              new Item("lizard", 99.99),
//              new Item("cookie", 1.99))),
//      new Sale(Store.ST_LOUIS, new Date(), Optional.of("Jamie"),
//          Arrays.asList(
//              new Item("banana", 3.49),
//              new Item("cookie", 1.49))));
  
  private static Stream<Sale> saleStream() {
    // return sales.stream();
    // para obtener un flujo de 100 ventas aleatorias y calcularemos que
    // hoy tenemos 100 ventas de algún tipo. 
    return RandomSale.streamOf(10000);
  }
  
  public static void main(String[] args) {
    // how many sales?
    long saleCount = saleStream().count();
    System.out.println("Count of sales: " + saleCount);
    
    System.out.println(" ------ ");
    
    // any sales over $100?
    // boolean bigSaleDay = saleStream().anyMatch(sale -> sale.total() > 100.00);
    
    // IllegalStateException -> el flujo ha sido operado.
    // cada instancia de un flujo puede hacer exactamente un conjunto de
    // procesamiento, es como un iterador, proporciona un recorrido único de
    // alguna fuente de valores, es mejor que un iterador porque al menos
    // se conoce la transmisión al momento de la excepción, con el iterador se
    // verá vacio.
    // DoubleStream totalStream = saleStream().mapToDouble(Sale::total);
    
    // terminal operations
    Supplier<DoubleStream> totalStream = () ->
      saleStream().mapToDouble(Sale::total);
    
    boolean bigSaleDay = totalStream.get().anyMatch(total -> total > 100.00);
    System.out.println("Big sale day? " + bigSaleDay);
    
    System.out.println(" ------ ");
    
    // maximum sale amount?
    // DoubleSummaryStatistics stats = saleStream().mapToDouble(Sale::total)
    //     .summaryStatistics();
    DoubleSummaryStatistics stats = totalStream.get().summaryStatistics();
    System.out.println("Max sale amount: " + stats.getMax());
    System.out.println("Stats on total: " + stats);
    
    System.out.println(" ------ ");
    
    // intermediate operations 
    // how many items were sold today?
    Supplier<Stream<Item>> itemStream = () ->
      saleStream().flatMap(sale -> sale.items.stream());
    long itemCount = itemStream.get().count();
    System.out.println("Count of items: " + itemCount);
    
    System.out.println(" ------ ");
    
    // which many different items were sold today?
    long uniqueItemCount = itemStream.get().map(item -> item.identity)
        .distinct().count();
    System.out.println("Count of distinct items: " + uniqueItemCount);
    
    System.out.println(" ------ ");
    
    // collections
    // List<String> uniqueItems = itemStream.get()
    //     .map(item -> item.identity)
    //     .distinct()
    //     .collect(Collectors.toList());
    
    String uniqueItems = itemStream.get()
        .map(item -> item.identity)
        .distinct()
        .collect(Collectors.joining(" & "));    
    System.out.println("Distinct items: " + uniqueItems);
    
    System.out.println(" ------ ");
    
    // summarize sales by store
    // Map<Store, DoubleSummaryStatistics> summary = saleStream()
    //     .collect(Collectors.groupingBy(sale -> sale.store,
    //         Collectors.summarizingDouble(Sale::total)));
    // System.out.println("Summary by store: " + summary);    
    
    // summary.keySet().stream().forEach(store ->
    //     System.out.println(store + " stats: " + summary.get(store)));
    
    // System.out.println(" ------ ");
    
    // parallelism -> with threads
    // Map<String, DoubleSummaryStatistics> parallelism = saleStream()
    //     .parallel()
    //     .collect(
    //         Collectors.groupingBy(sale -> Thread.currentThread().getName(),
    //             Collectors.summarizingDouble(Sale::total)));
    
    // para hacer un poco más eficiente usar groupingByConcurrent, para
    // devolver un mapa concurrente, pero en una operación tan fácil,
    // la diferencia de rendimiento no se nota.
    ConcurrentMap<String, DoubleSummaryStatistics> parallelism = saleStream()
        .parallel()
        .collect(
            Collectors.groupingByConcurrent(sale -> Thread.currentThread().getName(),
                Collectors.summarizingDouble(Sale::total)));
    System.out.println("Summary by thread: " + parallelism);
    
    parallelism.keySet().stream().sorted().forEach(store ->
      System.out.println(store + " stats: " + parallelism.get(store))); 
    // hay 2 o 3 hilos porque tengo 2 o 3 CPU en mi computadora.
  }
}
