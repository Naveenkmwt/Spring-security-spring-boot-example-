# Registration and Login Example with Spring Security, oauth2 Spring Boot,Ldap Spring Data JPA, MySQL, JSP

## Guide
https://projects.spring.io/spring-security-oauth/docs/oauth2.html
https://spring.io/guides/tutorials/spring-boot-oauth2/

## Prerequisites
- JDK 1.8
- Maven 3 or later

## Stack
- Spring Security
- Spring Boot
- Spring Data JPA
- oAuth2
- Maven
- JSP
- MySQL

## Run
```mvn clean install spring-boot:run```

1. Open Rest Client and insert following url 
http://localhost:8081/oauth/token?grant_type=password&username=username&password=userpassword  

2. Go to "header form" select 'Authorization'  after that enter  clientId and clientSecret as a username and password in authorization header value then click to send button now it will create access_token


