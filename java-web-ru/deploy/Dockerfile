FROM gradle:7.4.0-jdk17

WORKDIR /deploy

COPY /deploy .

RUN ./gradlew installDist

CMD ./build/install/deploy/bin/deploy