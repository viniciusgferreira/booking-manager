FROM openjdk:17

WORKDIR /app

ADD ./target/booking-manager-0.0.1-SNAPSHOT.jar booking-manager-1.0.0.jar

ENTRYPOINT [ "java", "-jar", "booking-manager-1.0.0.jar" ]
