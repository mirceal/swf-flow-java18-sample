package com.mirceal.sample.flow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SampleActivitiesImpl implements SampleActivities {

  @Override
  public List<String> sampleActivity0() {
    String seed = "The rain in Spain stays mainly on the plane";
    return Arrays.asList(seed.split("\\s"));
  }

  @Override
  public String sampleActivity1(String input) {
    return new StringBuilder(input).reverse().toString();
  }

  @Override
  public void sampleActivity2(String input) {
    System.out.println("Result: " + input);
  }
}
