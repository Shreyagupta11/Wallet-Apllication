# Getting Started

**About**
Wallet app is designed using Spring Boot Security using JWT Authentication.

Java Version : 8
Spring Boot Version : 2.7.13

### Reference Documentation
For further reference, please consider the following sections:

    Spring Boot Security is referenced by the project - (https://medium.com/@xoor/jwt-authentication-service-44658409e12c)
    Git Url is - (https://github.com/murraco/spring-boot-jwt)

Configuration -
    
    CustomAuthenticationEntryPoint.java - This class is created for handling the exception coming before controller loading.

     Global exception handler wouldn't handle Filter exceptions. For more reference read this article
     https://iamvickyav.medium.com/remember-springboot-global-exceptionhandler-wont-work-for-filters-5fabb4a2662a
     https://www.baeldung.com/spring-security-exceptionhandler

    GlobalExceptionHandlerConfig.java - In this file we handle all the exception throws anywhere in the project. All Exception class has to be implemented here for exception catch.

Security - 

    JwtTokenFilter.java - The work of this file is to handle the jwtToken. Here token is validated using validateToken() method. If token is expired then a custom exception is thrown else request goes to controller.

    JwtTokenFilterConfigurer.java - In this file a custom token provider is passed in jwtTokenFilter() instance. This file is required because default Spring boot security provider is throwing exception of stackoverflow. So, to overcome that problem a custom token provider file is created.

    JwtTokenProvider.java - This file has method for token, e.g. createToken(), resolveToken(), validateToken() etc.

    MyUserDetails.java - This file extends security UserDetailService class and override the method loadUserByUsername().

    WebSecurityConfig.java - This file is configuration file of spring boot security. Here we authorize which url is bypass token, cors policy setup, before controller exception handling etc. Http and security methods are handled in this file. 