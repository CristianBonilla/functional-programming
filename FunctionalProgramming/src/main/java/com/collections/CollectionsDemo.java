package com.collections;

import java.util.Arrays;
// import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
// import java.util.stream.Stream;

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
//    final Stream<String> lastWords = Arrays.asList(descriptions)
//      .stream()
//      .filter(NON_EMPTY)
//      .map(lastWord);
    
//    final Optional<String> lastWordsJoin = lastWords.reduce(
//        (a, n) -> a + " & " + n);
//    
//    return lastWordsJoin.orElse("");
    
    return Arrays.asList(descriptions)
      .stream()
      .filter(NON_EMPTY)
      .map(lastWord)
      .reduce(joinOn(" & "))
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
