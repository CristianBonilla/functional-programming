package com.timing.functional;

import org.apache.logging.log4j.Logger;
import com.google.common.base.Function;

public class LoggerFunctions {
  public static Function<String, Void> info(final Logger logger) {
    return new Function<String, Void>() {
      @Override
      public Void apply(String s) {
        logger.info(s);
        return null;
      }
    };
  }
}
