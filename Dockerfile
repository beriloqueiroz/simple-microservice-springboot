FROM openjdk:17-slim

ENV TZ=America/Sao_Paulo

COPY infrastructure/target/app.jar /app.jar

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
EXPOSE 8091
WORKDIR /
ENV JAVA_OPTS="--add-opens=java.base/jdk.internal.misc=ALL-UNNAMED"
ENTRYPOINT ["sh", "-c",  "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar"]
