# How to run Order application

The application can be run with:

```
./mvnw spring-boot:run
```

And the Prometheus metrics are available at http://localhost:8080/actuator/prometheus

## Build the docker image

On local minikube please run `execute.sh` or simply run:

```
docker build . -t order:0.0.46 -f src/main/docker/Dockerfile --no-cache=true
```
If you are building your own image, please replace it in the `setup.yaml`.

## Install the application

```
kubectl apply -f setup.yaml
```

## Generate orders

In order to setup the URL for the order endpoint:

```
export ORDER=$(minikube service order-service --url)
```
**!!!Attention!!!**
If you deploy to a namespace different from default, please append the namespace name to the above command.
For example:

```
export ORDER=$(minikube service order-service --url -n <namespace-name>)
```

Generate some orders using `make-orders.sh`

```
sh make-orders.sh
```

[======>Go to main setup instructions<======](../README.md)
