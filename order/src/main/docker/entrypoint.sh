#!/bin/bash
set -e

echo "Running on Production"
exec java -Djava.security.egd=file:/dev/./urandom -Djavaagent:/jmx_prometheus_javaagent-0.10.jar=9099:/jmx-exporter-config.yml -jar app.jar
