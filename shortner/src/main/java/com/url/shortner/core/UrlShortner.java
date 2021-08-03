package com.url.shortner.core;

public class UrlShortner {
  private static final String ALPHABETS =
      "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
  private static final int base = ALPHABETS.length();

  public static String encode(int num) {
    StringBuilder sb = new StringBuilder();
    while (num > 0) {
      sb.append(ALPHABETS.charAt(num % base));
      num = num / base;
    }
    return sb.reverse().toString();
  }

  public static int decode(String str) {
    int num = 0;
    for (int i = 0; i < str.length(); i++) {
      num = num * base + ALPHABETS.indexOf(str.charAt(i));
    }
    return num;
  }

}