FROM java:8
EXPOSE 8082
ADD /target/user-service.jar user-service.jar
ENTRYPOINT ["java","-jar","user-service.jar"]