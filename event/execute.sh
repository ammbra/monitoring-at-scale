eval $(minikube docker-env)
docker build . -t event:0.0.8 -f src/main/docker/Dockerfile --no-cache=true
