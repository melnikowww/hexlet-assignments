FROM gradle:7.4.0-jdk17

WORKDIR /app

COPY /app .

RUN gradle stage

CMD ./build/install/deploy/bin/deploy