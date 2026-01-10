## Steps

1. `minikube start --driver docker`
2. apply
    ```shell
    kubectl apply -f otel-loki-configmap.yaml
    
    kubectl apply -f loki-deployment.yaml
    
    kubectl apply -f otel-collector-deployment.yaml
    
    kubectl apply -f grafana-deployment.yaml
    ```
3. verify pods svc and prepare url.
    ```shell
    kubectl get pods
    
    kubectl get svc
    
    minikube service otel-collector-service --url # get url for send curl request from external terminal
   
    minikube service grafana-service --url # get url for open grafana from external browser

    ```
4. send request
   
   ```shell
   curl -X POST <get-url-from-above-step>/v1/logs \
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

5. verify in grafana

   - `http://loki-service:3100` use this in grafana for datasource
   - check the request logs are displayed in grafana

![grafana-in-minikube](https://github.com/user-attachments/assets/94856d18-9b42-43b2-b690-ddf9b49d2356)
