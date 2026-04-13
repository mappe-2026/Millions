package edu.ntnu.idatt2003.gruppe50.util;

import java.math.BigDecimal;

public class Parse {

  public static BigDecimal parseBigDecimal(String input) {
    String cleaned = input.replaceAll("[^0-9.]", "");
    if (cleaned.isBlank()) {
      throw new IllegalArgumentException("Starting capital must be a valid number");
    }
    return new BigDecimal(cleaned);
  }
}
