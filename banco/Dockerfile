FROM openjdk:17-jdk-alpine
ARG ACTIVE_PROFILE
ENV ACTIVE_PROFILE=${ACTIVE_PROFILE:-dev}
RUN echo "profile ativo: $ACTIVE_PROFILE"
COPY ./target/*SNAPSHOT.jar /app.jar
ENV PORT 8081
EXPOSE $PORT
#VOLUME [ "${PWD}/log:/log" ]
WORKDIR /
ENTRYPOINT ["java","-jar","-Xmx1024M","-Dspring.profiles.active=${ACTIVE_PROFILE}","-Dserver.port=${PORT}","app.jar"]