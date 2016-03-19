## Start & test

Build the project and launch the web app with tomcat on [http://localhost:8082](http://localhost:8082):

    Before first start all dependencies should be downloaded:
    mvn clean install

    cd almacen
    mvn clean spring-boot:run  -Djavax.net.ssl.trustStore=.keystore -Djavax.net.ssl.trustStorePassword=mateusz3

Running unit tests (maven-surefire-plugin):
    

```
#!java

mvn clean test
```



Application is configured to work on port 8082 and it doesn't use SSL.