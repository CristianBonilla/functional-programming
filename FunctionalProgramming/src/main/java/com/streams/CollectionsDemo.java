package com.streams;

import java.util.Arrays;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class CollectionsDemo {
  final static String[] food = new String[] {
    "Crunchy carrots",
    "Golden-hued bananas",
    "",
    "Bright orange pumpkins",
    "Little trees of broccoli",
    "meat"
  };
  
  private static Predicate<String> NON_EMPTY = s -> !s.isEmpty(); 
  
  private static String summarize(final String[] descriptions) {    
    System.out.println("---Setting up the stream---");
    Stream<String> lastWords = Arrays.asList(descriptions)
      .stream()
      .peek(s -> System.out.println("About to filter: " + s))
      .filter(NON_EMPTY)
      .limit(3)
      .peek(s -> System.out.println("About to map: " + s))
      .map(lastWord)
      .peek(s -> System.out.println("About to reduce: " + s));
    System.out.println("---Reducing---");
    
    return lastWords.reduce(joinOn(" & "))
      .orElse("");
  }
  
  private static BinaryOperator<String> joinOn(String connector) {
    return (previous, next) -> previous + connector + next;
  }
  
  private static BinaryOperator<String> chooseLast = (previous, next) -> next;
  
  private static Function<String, String> lastWord = 
      phrase -> Arrays.asList(phrase.split(" ")).stream()
        .reduce(chooseLast)
        .orElse("");
  
  public static void main(String[] args) {
    final String summary = summarize(food);
    final String desiredSummary = "carrots & bananas & pumpkins & broccoli & meat";
    System.out.println(summary);
    if (summary.equals(desiredSummary)) {
      System.out.println("good!");
    }
  }
}
