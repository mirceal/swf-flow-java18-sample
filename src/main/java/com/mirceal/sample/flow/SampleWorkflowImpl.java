package com.mirceal.sample.flow;


import com.amazonaws.services.simpleworkflow.flow.annotations.Asynchronous;
import com.amazonaws.services.simpleworkflow.flow.annotations.Wait;
import com.amazonaws.services.simpleworkflow.flow.core.Promise;
import com.amazonaws.services.simpleworkflow.flow.core.TryCatch;

import java.util.ArrayList;
import java.util.List;

public class SampleWorkflowImpl implements SampleWorkflow {

  private SampleActivitiesClient activitiesClient = new SampleActivitiesClientImpl();
  
  @Override
  public void test() {
    new TryCatch() {
      @Override
      protected void doTry() throws Throwable {
        Promise<List<String>> startSeed = activitiesClient.sampleActivity0();
        activitiesClient.sampleActivity2(process(startSeed));
      }

      @Override
      protected void doCatch(Throwable throwable) throws Throwable {
        // don't do this in a real env. this is for demo purposes. you have been warned.
        System.out.println("workflow failed");
        throwable.printStackTrace();
      }
    };
  }

  @Asynchronous
  private Promise<String> process(Promise<List<String>> inputs) {
    List<Promise<String>> results = new ArrayList<>();
    for(String input : inputs.get()) {
      results.add(activitiesClient.sampleActivity1(input));
    }
    return joinPromises(results);
  }

  @Asynchronous
  private Promise<String> joinPromises(@Wait List<Promise<String>> toWaitOn) {
    StringBuilder sb = new StringBuilder();
    for(Promise<String> promiseInput : toWaitOn) {
      sb.append(promiseInput.get()).append(" ");
    }
    return Promise.asPromise(sb.toString());
  }
}
