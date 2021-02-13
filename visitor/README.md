# How to run Visitor application

The application can be run with:

```
./mvnw spring-boot:run
```

And the Prometheus metrics are available at http://localhost:8080/actuator/prometheus

## Build the docker image

On local minikube please run `execute.sh` or simply run:

```
docker build . -t visitor:0.0.5 -f src/main/docker/Dockerfile --no-cache=true
```

If you are building your own image, please replace it in the `setup.yaml`.

## Install the application

```
kubectl apply -f setup.yaml
```

[Go to order setup instructions](../order/README.md) ||||||| [Go to main setup instructions](../README.md)
