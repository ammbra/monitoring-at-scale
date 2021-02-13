# How to run Itinerary application

The application can be run with:

```
./mvnw spring-boot:run
```

And the Prometheus metrics are available at _http://localhost:8080/actuator/prometheus_

## Build the docker image

On local minikube please run `execute.sh` or simply run:

```
docker build . -t itinerary:0.0.16 -f src/main/docker/Dockerfile --no-cache=true
```

If you are building your own image, please replace it in the `setup.yaml`.

## Install the application

```
kubectl apply -f setup.yaml
```
[Go to landmark setup instructions](../landmark/README.md) ||||||| [Go to main setup instructions](../README.md)
