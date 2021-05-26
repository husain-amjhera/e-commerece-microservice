FROM java:8
EXPOSE 8081
ADD /target/inventory-service.jar inventory-service.jar
ENTRYPOINT ["java","-jar","inventory-service.jar"]