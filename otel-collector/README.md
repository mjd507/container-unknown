## Flow

OpenTelemetry-Collector => Loki => Grafana

## Steps

1. `docker compose up`
2. using `curl` to send an http request to open-telemetry-collector
    ```curl
    curl -v -X POST http://localhost:4318/v1/logs \
    -H "Content-Type: application/json" \
    -d '{
      "resourceLogs": [
        {
          "resource": {
            "attributes": [{"key": "service.name", "value": {"stringValue": "test-app"}}]
          },
          "scopeLogs": [
            {
              "scope": {},
              "logRecords": [
                {
                  "timeUnixNano": '$(($(date +%s)*1000000000))',
                  "severityText": "INFO",
                  "body": {"stringValue": "hello world"},
                  "attributes": [{"key": "log.level", "value": {"stringValue": "INFO"}}]
                }
              ]
            }
          ]
        }
      ]
    }'
    ```
3. check in Grafana

    - http://localhost:3000（admin/admin）；
    - in top: choose Loki datasource；
    - in query put：{service.name="test-app"}，
    - run query, log will be displayed


