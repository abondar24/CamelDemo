# Shopping cart

Camel + Spring Boot based shopping card react-app backend


## Build,test and run

### Build

- Build the app using gradle wrapper

```
./gradlew clean build
```

- Build docker image

```
./gradlew bootBuildImage
```

### Test

```
./gradlew clean test
```

### Run

- Run the app using gradlew

```
./gradlew bootRun
```

- Run the app via jar

```
java -jar build/libs/ShoppingCart-1.0-SNAPSHOT.jar
```

- Run a docker image

```
docker run -d --name todo  -p 8080:8080 abondar/shoppingCart
```
## Note

- Change create log directory as mentioned in logback.xml file
