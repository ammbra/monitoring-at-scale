eval $(minikube docker-env)
docker build . -t visitor:0.0.5 -f src/main/docker/Dockerfile  --no-cache=true
