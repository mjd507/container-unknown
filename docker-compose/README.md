
ensure docker is running in local environment when starting the spring-boot application.

it will pull mongodb and start it with configurations in compose.yaml.

test with below command, we will see the output with id (from mongodb) returned.

```shell
curl --location 'http://localhost:8080/item' \
--header 'Content-Type: application/json' \
--data '{
    "name" : "abc",
    "quantity" : 1,
    "category" : "a"
}'

{"id":"687c69ed06ca4cdf4d3372f3","name":"abc","quantity":1,"category":"a"}
```
