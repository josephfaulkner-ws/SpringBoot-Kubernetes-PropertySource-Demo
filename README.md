
# SpringBoot Kubernetes Secret Property Source Demo

Here's a very simple web project that demonstrates how to:

- read a secret from the Kubernetes cluster directly and use it as an application property
- run a Kubernetes application locally on Docker Desktop without needing to push the apps' Docker image to a remote repository (such as docker hub)


Requirements:
- Java (I used version 21)
- Docker Desktop with Kubernetes Extension Enabled

Before building and deploying this app to Kubernetes, you may be curious to run it directly on your local machine.
```
# By default, this will be available on localhost:8080
./mvnw spring-boot:run
```

This will display a simple webpage like this: 

---
### Hello World from SpringBoot!
#### Message from application.yml
#### 'Default Secret Message from application.yml'
---


Deploying to Kubernetes

First, confirm that kubectl is setup to use docker-desktop

```
kubectl get nodes
NAME             STATUS   ROLES           AGE   VERSION
docker-desktop   Ready    control-plane   19h   v1.28.2

kubectl config view --minify
```

```
./mvnw clean package 
docker build --tag=springbootdemo:latest .
docker images             #(just to see that the new image was built)
kubectl apply -f ./k8s    #(run this command twice if it fails the first time)
kubectl get services
```

Take note of the PORT(S) column. For example, if you want to see 8080:31797/TCP for the springbootdemo service, you'll want to navigate to localhost:31797 on your local web browser

```
SpringBoot-Kubernetes-PropertySource-Demo % kubectl get services                    
NAME             TYPE        CLUSTER-IP      EXTERNAL-IP   PORT(S)          AGE
kubernetes       ClusterIP   10.96.0.1       <none>        443/TCP          10h
springbootdemo   NodePort    10.97.153.162   <none>        8080:31797/TCP   6m25s
```

Navigating to this url will display a webpage like this. Notice that the third paragraph now reads from the Kubernetes secret value.


---
### Hello World from SpringBoot!
#### Message from application.yml
#### Hello from Kubernetes Secret! This message is stored as a secret and read from the Kubernetes cluster.
---