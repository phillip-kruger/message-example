# message-example
Simple example project to demonstrate [MicroProfile](https://microprofile.io/) 1.2

This works with

* [Open Liberty](https://openliberty.io/)
* [Wildfly swarm](http://wildfly-swarm.io/) 

and can tested with [Apache Maven](https://maven.apache.org/) using the parent pom from [https://github.com/phillip-kruger/javaee-servers-parent](https://github.com/phillip-kruger/javaee-servers-parent)

This also use some extentions from [https://github.com/phillip-kruger/microprofile-extentions](https://github.com/phillip-kruger/microprofile-extentions)

## Example

![](https://raw.githubusercontent.com/phillip-kruger/message-example/master/message-example.png)

## Getting started

To demo this:

### Logfile writer

This is a simple JAX-RS endpoint that prints a message to it's log file

1. Start the logfile writer:
        `mvn clean install -Popenliberty-run` 
        or
        `mvn clean install -Pwildfly-swarm-run`
1. You can test the logfile writer (http://localhost:8081/logfile-writer/api/SEVERE/):
        `mvn -Dtest=com.github.phillipkruger.messageexample.logfileprovider.LogFileWriterApiIT -Popenliberty-run surefire:test`
1. You should see something like this in the log file:
        `>>>>>>>>> testing_log_file_writer_59d5f869-a6ba-4eec-a5d9-07f3a529f7ae`

### Message application

This is a application that distribute message to multiple providers

1. Start the application:
        `mvn clean install -Popenliberty-run` 
        or
        `mvn clean install -Pwildfly-swarm-run`
1. Send a hello message:
        `mvn -Dtest=com.github.phillipkruger.message.MessageApiIT -Popenliberty-log surefire:test`
1. You should see something like this in the log file:
        `>>>>>>>>> [09:48:42.952] From defaulthello_5c92f0c5-808f-465f-ab23-7de4259f5eec`

### Fault tolerance API

1. Shut down the log file server.
1. Run the message application again (a few times).
1. Start the log file server.

### Config API

1. Update some configuration:
        `mvn -Dtest=com.github.phillipkruger.message.ConfigApiIT -Popenliberty-log surefire:test`
1. Run the message application again
1. Value should change:
        `>>>>>>>>> [09:53:03.432] memoryhello_ffd020c6-2a22-4279-bbc6-4dd97c0a44c5`
    
