# How to run Event application

The application can be run with:

```
./mvnw spring-boot:run
```

And the Prometheus metrics are available at http://localhost:8080/actuator/prometheus

This application uses:
1. a custom counter to track events named `event_counter_total`. This is increased every time a GET request is made to `/event`.
2. the GET requests made to `/event` are also monitored via `@Timed`

## Build the docker image

On local minikube please run `execute.sh` or simply run:

```
docker build . -t event:0.0.8 -f src/main/docker/Dockerfile --no-cache=true
```
If you are building your own image, please replace it in the `setup.yaml`.

## Install the application

```
kubectl apply -f setup.yaml
```

[Go to itinerary setup instructions](../itinerary/README.md) ||||||| [Go to main setup instructions](../README.md)
