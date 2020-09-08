# Spring Cloud Kubernetes Example: Shop Service #

This project born with the aim to show how deploy a service using Spring Cloud Kubernetes. 

This is a simple application thas uses  a service discovery and a load balancer as a  part of kubernetes architecture to discover services
and balance requets between differents instances of a service. This services are uses to communicate with a service
called order-service.

## How can I run it?

First off is necesary a distribution of kubernetes such as minikube, google cloud kubernetes ... and have installed
docker, then with kubernetes running in our local machine we can start to deploy our services: 

### Step 1
`eval $(minikube docker-env)
`
### Step 2
`mvn clean install
`
### Step 2
`docker build -t shop-service .
`
### Step 3
`kubectl create -f role.yaml
`
### Step 4
`kubectl create -f config.yaml
`
###Step 5
`kubectl create -f deployment.yaml
`
And ready to play with the application.

If you are using minikube don't forget run the next command to open a browser with the application:
` minikube service shop-service
`

