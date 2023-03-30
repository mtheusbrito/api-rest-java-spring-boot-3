#### Spring Boot 3: Desenvolvendo uma API Rest em Java



### Requisitos
	- Java version 17
	- maven
	- MySQL: 5.5 ~ 10.3.38-MariaDB
	
### Build 

```
cd project_folder && 
mvn package
```

### Ex: Run in prod

```
cd project_folder/target &&
java -Dspring.profiles.active=prod -DDATASOURCE_URL=jdbc:mysql://ip_prod/database_prod -DDATASOURCE_USERNAME=username -DDATASOURCE_PASSWORD=password -jar api-0.0.1-SNAPSHOT.jar
```