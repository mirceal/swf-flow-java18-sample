package com.mirceal.sample.flow;

import com.amazonaws.services.simpleworkflow.flow.annotations.Activities;
import com.amazonaws.services.simpleworkflow.flow.annotations.ActivityRegistrationOptions;
import com.amazonaws.services.simpleworkflow.flow.annotations.ExponentialRetry;

import java.util.List;

@ActivityRegistrationOptions(defaultTaskScheduleToStartTimeoutSeconds = 5,
                             defaultTaskStartToCloseTimeoutSeconds = 5)
@Activities(version = "1.0")
public interface SampleActivities {

  @ExponentialRetry(initialRetryIntervalSeconds = 5, backoffCoefficient = 2, maximumAttempts = 8,
                    retryExpirationSeconds = 60)
  public List<String> sampleActivity0();
  
  @ExponentialRetry(initialRetryIntervalSeconds = 5, backoffCoefficient = 2, maximumAttempts = 8,
                    retryExpirationSeconds = 60)
  public String sampleActivity1(String input);


  @ExponentialRetry(initialRetryIntervalSeconds = 5, backoffCoefficient = 2, maximumAttempts = 8,
                    retryExpirationSeconds = 60)
  public void sampleActivity2(String input);
}
