FROM container-registry.oracle.com/java/jdk:11.0.8

COPY ./target/insurance-premium-simulation-backend-spring-0.0.1-SNAPSHOT.jar /root/backend.jar

CMD [ "sh", "-c", "java $JAVA_OPTIONS -jar /root/backend.jar" ]

