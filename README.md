# swf-flow-java18-sample

## What is this? 
This project show a sample of using AWS SWF Flow with Java 1.8

## Why ?
I've tried using AWS SWF Flow framework and had to tinker with it quite bit to get it right.  
You can read the history in this [Stack Overflow Thread](http://stackoverflow.com/questions/9392655/how-to-consume-amazon-swf/28843218#28843218)  
So puting this sample together to save future SWF Flow explorers some time.  

## How to get this working?

1.  Drop your AWS credential in App.java
2.  Make sure the domain you specify exists (or create it)
3.  Run the sample
4.  ...
5.  PROFIT

The meat of this sample is actually in the pom.xml.  
This is by no means a complete working sample. It's enough to show you how you can use Java 1.8 and make SWF calls.  
The meat of this sample is actually in the pom.xml. The java part is to prove it works :)  
The key insight is to separate the annotation process from the weaving process.  
Also, what makes the use of Java 1.8 possible is the use of aspects from [com.amazonaws.aws-java-sdk-swf-libraries](https://github.com/aws/aws-sdk-java/tree/master/aws-java-sdk-swf-libraries/src/main/java/com/amazonaws/services/simpleworkflow/flow/aspectj)

## Help 
If you have troubles getting this working or have any questions I may choose to help you out IFF I have the spare cycles, but for all purposes you should treat this as "as is".
