eval $(minikube docker-env)
docker build . -t itinerary:0.0.16 -f src/main/docker/Dockerfile --no-cache=true
