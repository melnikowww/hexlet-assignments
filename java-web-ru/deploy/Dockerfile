FROM gradle:7.4.0-jdk17

WORKDIR java-web-ru/deploy

COPY java-web-ru/deploy .

RUN gradle stage

CMD ./build/install/deploy/bin/deploy