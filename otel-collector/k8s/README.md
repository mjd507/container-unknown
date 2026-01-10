## Steps

1. `minikube start --driver docker`
2. apply
    ```shell
    kubectl apply -f otel-loki-configmap.yaml
    
    kubectl apply -f loki-deployment.yaml
    
    kubectl apply -f otel-collector-deployment.yaml
    
    kubectl apply -f grafana-deployment.yaml
    ```
3. verify
    ```shell
    kubectl get pods
    
    kubectl get svc
    
    minikube service grafana-service --url
    ```
4. send request
   
   ```shell
   OTEL_HTTP_PORT=$(kubectl get svc otel-collector-service -o jsonpath='{.spec.ports[1].nodePort}')
   echo $OTEL_HTTP_PORT
   
   MINIKUBE_IP=$(minikube ip)
   echo $MINIKUBE_IP 
   
   curl -X POST http://$MINIKUBE_IP:$OTEL_HTTP_PORT/v1/logs \
     -H "Content-Type: application/json" \
     -d '{
       "resourceLogs": [
         {
           "resource": {
             "attributes": [
               {"key": "service.name", "value": {"stringValue": "k8s-test-app"}}
             ]
           },
           "scopeLogs": [
             {
               "scope": {},
               "logRecords": [
                 {
                   "timeUnixNano": '$(date +%s000000000)',
                   "severityText": "INFO",
                   "body": {"stringValue": "K8s 环境测试日志：OTel → Loki 连通成功！"},
                   "attributes": [
                     {"key": "log.level", "value": {"stringValue": "INFO"}}
                   ]
                 }
               ]
             }
           ]
         }
       ]
     }'
   
   ```