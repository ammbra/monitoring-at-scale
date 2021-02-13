eval $(minikube docker-env)
docker build . -t order:0.0.46 -f src/main/docker/Dockerfile --no-cache=true
