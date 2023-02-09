package com.example;

import java.util.List;

public class FunctionOutput {
  private List<FunctionWrapper> results;

  public FunctionOutput(List<FunctionWrapper> results) {
    this.results = results;
  }

  public List<FunctionWrapper> getResults() {
    return results;
  }
}
