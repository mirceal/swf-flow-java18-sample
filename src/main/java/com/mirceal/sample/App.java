package com.mirceal.sample;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflowClient;
import com.amazonaws.services.simpleworkflow.flow.ActivityWorker;
import com.amazonaws.services.simpleworkflow.flow.WorkflowWorker;
import com.amazonaws.services.simpleworkflow.model.WorkflowExecutionAlreadyStartedException;
import com.mirceal.sample.flow.SampleActivitiesImpl;
import com.mirceal.sample.flow.SampleWorkflowClientExternal;
import com.mirceal.sample.flow.SampleWorkflowClientExternalFactory;
import com.mirceal.sample.flow.SampleWorkflowClientExternalFactoryImpl;
import com.mirceal.sample.flow.SampleWorkflowImpl;

public class App {
  public static void main(String... args)
      throws IllegalAccessException, NoSuchMethodException, InstantiationException,
             InterruptedException {
    // don't do this at home. this are hardcoded here for demo purposes
    // you need to put your own creds / domain for this sample to work
    String accessKey = "<< insert your access key here >>";
    String secretKey = "<< insert your secret key here>>";
    String domain = "mydomain"; // this need to exist already
    String taskList = "mytasklist";
    
    // build the swf client
    AmazonSimpleWorkflowClient simpleWorkflowClient = new AmazonSimpleWorkflowClient(
        new BasicAWSCredentials(accessKey, secretKey));
    simpleWorkflowClient.setRegion(Regions.US_WEST_2);

    // prepare the actitiy workers
    ActivityWorker aw = new ActivityWorker(simpleWorkflowClient, domain, taskList);
    aw.addActivitiesImplementation(new SampleActivitiesImpl());

    // prepare the worflow workers
    // remember that there are different task lists for deciders and actities even if they
    //  have the same name
    WorkflowWorker wfw = new WorkflowWorker(simpleWorkflowClient, domain, taskList);
    wfw.addWorkflowImplementationType(SampleWorkflowImpl.class);
    
    // start the show
    aw.start();
    wfw.start();
    
    // launch sample workflow
    SampleWorkflowClientExternalFactory fetchWorkflowFactory =
        new SampleWorkflowClientExternalFactoryImpl(simpleWorkflowClient, domain);
    SampleWorkflowClientExternal
        fetchWorkflowFactoryClient = fetchWorkflowFactory.getClient("test01");
    try {
      fetchWorkflowFactoryClient.test();
    } catch (WorkflowExecutionAlreadyStartedException workflowExecutionAlreadyStartedException) {
      workflowExecutionAlreadyStartedException.printStackTrace();
    }
    
    // wait 60 seconds and exit
    // you normally have to handle the shutdown scenario more gracefully and signal the workers 
    // above via shutdown. you probably don't want to run everything on the main thread and you 
    // may want to have something like deamon threads, etc, etc
    // this is just a sample so I get away with doing this.
    Thread.sleep(60000);
    aw.shutdown();
    wfw.shutdown();
    System.exit(0);
  }
}
