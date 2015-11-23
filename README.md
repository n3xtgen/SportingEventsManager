## SportingEventsManager

Welcome to the GitHub page of project SportingEventsManager.  
This is a training project for Masaryk University's PA165 lecture.

Please look at our [Wiki page](https://github.com/n3xtgen/SportingEventsManager/wiki).

##Building Sporting Events Manager
This application will run only on Java 8 platform. We use Maven 3.x to build this software. The following commands clone this project, compile the code, install the JAR into your local Maven repository and run all the tests:

      1. $ git clone https://github.com/n3xtgen/SportingEventsManager.git
      2. $ mvn clean install

This project uses in memory database so you don't need any local resources, but you can change this settings in "sportingEventsManager-persistence/src/main/resources/META-INF/persistence.xml".

##Sporting Events Manager web application
If you want to use this application you can utilize our web interface.  To run this UI please run following commands:

      1. $ cd  sporting-events-manager-mvc
      2. $ mvn

This commands will start the application and insert sample data to it. To use web UI open your browser and go to "http://localhost:8080/sporting-events-manager-mvc".

