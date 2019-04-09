package com.collections;

//import java.util.ArrayList;
import java.util.Arrays;
//import java.util.Iterator;
//import java.util.List;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Iterables;

public class CollectionsDemo {
  final static String[] food = new String[] {
		"Crunchy carrots",
		"Golden-hued bananas",
		"",
		"Bright orange pumpkins",
		"Little trees of broccoli",
		"meat"
  };
  
  private static Predicate<String> NON_EMPTY = new Predicate<String>() {    
    @Override
    public boolean apply(String s) {
      return !s.isEmpty();
    }
  }; 
  
  private static String summarize(final String[] descriptions) {
//    final List<String> nonEmpties = new ArrayList<String>();
//    for (String description : descriptions) {
//      if (!description.isEmpty()) {
//        nonEmpties.add(description);
//      }
//    }
    
//    final Iterable<String> nonEmpties = Iterables.filter(
//        Arrays.asList(descriptions), NON_EMPTY);
    
// ----------
    
//    final List<String> lastWords = new ArrayList<String>();
//    for (String description : nonEmpties) {
//      lastWords.add(lastWord(description));
//    }
    
//    Iterator<String> iterator = nonEmpties.iterator();
//    while (iterator.hasNext()) {
//      String description = iterator.next();
//      lastWords.add(lastWord(description));
//    }
    
//    final Iterable<String> lastWords = Iterables.transform(nonEmpties,
//        new Function<String, String>() {
//          @Override
//          public String apply(String s) {
//            return lastWord(s);
//          }
//        });
    
//    final Iterable<String> lastWords = Iterables.transform(nonEmpties, lastWord);

// ----------
    
    final Iterable<String> lastWords = FluentIterable.from(
        Arrays.asList(descriptions))
      .filter(NON_EMPTY)
      .transform(lastWord);
    
//    final StringBuilder output = new StringBuilder();
//    boolean isFirst = true;
    
//    for (String description : nonEmpties) {
//      final String word = lastWord(description);
//      if (!isFirst) {
//        output.append(" & ");
//      }
//      output.append(word);
//      isFirst = false;
//    }    
    
//    for (String word : lastWords) {
//      if (!isFirst) {
//        output.append(" & ");
//      }
//      output.append(word);
//      isFirst = false;      
//    }
//    return output.toString();
    
    String lastWordsJoin = Joiner.on(" & ").join(lastWords);
    
    return lastWordsJoin;
  }
  
//  private static String lastWord(final String phrase) {
//    final int lastSpace = phrase.lastIndexOf(" ");
//    if (lastSpace < 0) {
//      return phrase;
//    } else {
//      return phrase.substring(lastSpace + 1, phrase.length());
//    }
//  }
  private static Function<String, String> lastWord = 
      new Function<String, String>() {
        @Override
        public String apply(final String phrase) {
//          final int lastSpace = phrase.lastIndexOf(" ");
//          if (lastSpace < 0) {
//            return phrase;
//          } else {
//            return phrase.substring(lastSpace + 1, phrase.length());
//          }
          return Iterables.getLast(Arrays.asList(phrase.split(" ")), "");
        }
      };
  
  public static void main(String[] args) {
    final String summary = summarize(food);
    final String desiredSummary = "carrots & bananas & pumpkins & broccoli & meat";
    System.out.println(summary);
    if (summary.equals(desiredSummary)) {
      System.out.println("good!");
    }
  }
}
