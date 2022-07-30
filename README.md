# Spring-auth-server-example
##### Written on java 17
<hr>
The simplest example of registration/authorization on JWT.

###### Current functionality:
- User registration - **/api/security/register**. Data validation, saving to **postgres**
- Getting jwt user token - **/api/security/authenticate**
- Hello for users with «USER»‎ role - **/api/hello/user**
- Hello for users with «ADMIN»‎ role - **/api/hello/admin**

###### Frameworks/Libraries:
    spring-boot 2.7
    jjwt 0.9.1
    postgresql 42.4.0
    jakarta.validation-api 2.0.2

###### Build tools:
	Gradle 7.5
