FROM maven:3-openjdk-11 AS MVN_BUILD

LABEL version 1.0 \
      description "Image for order application"

COPY . /code
RUN echo 'building the application' > rm -Rf /code/target && \
	cd /code/ && mvn clean install

FROM openjdk:11-jre

COPY --from=MVN_BUILD /code/target/*.jar /app.jar
COPY --from=MVN_BUILD /code/src/main/docker/entrypoint.sh /entrypoint.sh
COPY --from=MVN_BUILD /code/target/classes/jmx-exporter-config.yml jmx-exporter-config.yml
COPY --from=MVN_BUILD /code/target/classes/jmx_prometheus_javaagent-0.10.jar jmx_prometheus_javaagent-0.10.jar


RUN groupadd -r appuser && useradd -r -g appuser appuser
RUN chmod +x /entrypoint.sh
USER appuser

CMD ["/entrypoint.sh"]

