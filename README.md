### Gringo
Plain jane Spring that is programmatically configured and runs locally with embedded jetty/tomcat.

run app
```bash
./gradlew appRun 
```
http://localhost:8080/gringo

create war 
```bash
./gradlew clean war 
```

### Maven (Optional) 
Use maven by renaming maven.xml to pom.xml and deleting:
 - build.gradle
 - gradle.properties
 - gradlew
 - gradlew.bat
 - gradle directory

run app (Maven)
```bash
mvn jetty:run 
```
http://localhost:8080/gringo

create war (Maven)
```bash
mvn clean war 
```

# help
 - https://docs.spring.io/spring-framework/docs/current/reference/html/
 - https://gretty-gradle-plugin.github.io/gretty-doc/Getting-started.html

