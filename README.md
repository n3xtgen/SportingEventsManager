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
      2. $ mvn tomcat7:run

This commands will start the application and insert sample data to it. To use web UI open your browser and go to "http://localhost:8080/sporting-events-manager-mvc".

##REST
You also can use our REST api. If you want to access to the application via rest you have to start REST API. 

      1. $ cd  sporting-events-manager-rest
      2. $ mvn tomcat7:run

####After that you can use following commands to manage data in application :
      
**This command show you all endpoints in our application:**

       $ curl -L -i -X GET http://localhost:8080/sporting-events-manager-rest 
      
**For list of all users:**

       $ curl -i -X GET http://localhost:8080/sporting-events-manager-rest/users
      
**Show user with given ID:**

       $ curl -i -X GET http://localhost:8080/sporting-events-manager-rest/users/{$ID}
      
**Show user with given ID:**

       $ curl -i -X GET http://localhost:8080/sporting-events-manager-rest/users/{$ID}
      
**For create new user with parameters $NAME, $SURNAME, $EMAIL and $PASSWORD:**
      
        $ curl -i -H "Content-Type: application/json" -X POST -d '{"name":"$NAME","surname":"$SURNAME","email":"$EMAIL","password":"$PASSWORD"}' 
            http://localhost:8080/sporting-events-manager-rest/users/create


      


