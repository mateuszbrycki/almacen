## Start & test

Before first start all dependencies should be downloaded:

```
    mvn clean install
```

Build the project and launch the web app with tomcat on [http://localhost:8082](http://localhost:8082):

```
    cd almacen
    mvn clean spring-boot:run  -Djavax.net.ssl.trustStore=.keystore -Djavax.net.ssl.trustStorePassword=mateusz3
```
or run:
```
    cd almacen
    ./boot.sh
```

Running unit tests (maven-surefire-plugin):
    

```
    mvn clean test
```

Application is configured to work on port 8082 and it doesn't use SSL.
