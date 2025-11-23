reference: [Kubernetes Crash Course for Absolute Beginners](https://www.youtube.com/watch?v=s_o8dwzRlu4)


## Local Deployment Steps:

minikube and docker are required in local machine.

minikube will create a k8s cluster, both master and worker processes are running in same node, in local docker env.
- `minikube start --driver docker`
- `kubectl apply -f Mongo-ConfigMap.yaml`
- `kubectl apply -f Mongo-Secrets.yaml`  
- `kubectl apply -f Mongo-Deployment.yaml`
- `kubectl apply -f Web-Deployment.yaml`

all images are deployed into containers, and we can verify by below:

- `kubectl get configmap`
- `kubectl get secret`
- `kubectl get svc`
- `kubectl get pods`

verify in the broswer:
- `minikube service webapp-service`

it will auto open the broswer and show us the page of the webapp.