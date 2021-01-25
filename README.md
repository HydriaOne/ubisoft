# Ubisoft Devops test
## Description

Is a simple REST API. 

This API code is in JAVA 11 and it runs in a Kubernetes cluster.

## Components:

 * Api-gradle API
 * Swagger-ui
 * MariaDB

### Currently Deployed services + monitoring with ssl termination:

HealthAPI
```
https://api-gradle.imina.cat/health
```
OpenAPI
```
https://api-gradle.imina.cat/openapi
```
Swagger UI
```
https://swagger.imina.cat
```
Grafana
```
https://grafana.imina.cat
```

# How to use it
### PreRequisits:

 * Kubernetes cluster v1.20
 * JAVA 11
 * Python 3.9, Pytest, Requests (This is to run functional tests on the api)
 * Create the new k8s namespace (kubectl create ns ubisoft)

### Commands:
```
- make build: : Build and push the image.
- make deploy : Deploy all the app + dependecies on your kubernetes cluster.
- make deploy_$service : For individual deployments. (gradle,swagger,mariadb)
- make destroy : Destroy all the app + dependeces on your kubernetes cluster.
- make destroy_$service : For individual deployments. (gradle,swagger,mariadb)
- make test : Run functional tests to check that the API is working.
- make all: Run all the pipline (build,deploy,test)
```

### Run localy

```
gradle microBundle
docker-compose up --build
```
Then access

HealthAPI
```
http://localhost:8080/health
```

OpenAPI
```
http://localhost:8080/openapi
```

Swagger UI
```
http://localhost:3000/?url=http://localhost:8080/openapi
```

# Pipeline
For the pipeline i will use the typical GitFlow:
* Normal Braches: A new push will launch a new build & deploy & test on testing k8s clusters.
* Master Branch: A new push will launch a new build & deploy & test on staging/preproduction k8s clusters.
* Tags: A new tag will launch a new build & deploy & test on production k8s clusters.
For CI/CD we can use Gitlab + Runners or Jenkins

# How to imporve the current app and move it to production
* Is always recomend to run the DBs outside the cluster so run mariadb on RDS.
* On the K8s Cluster, deploy multiple replicas with horitzontal and vertical autoscaling, based on cpu and memory.
* Use Route 53 + ALB + WAF in front of the application.
* Access logs: it should be get from the ALB and saved to S3/Glacier with intelligent tiering, and then analize it with Athena if are needed.
* Application logs: deploying the Cloudwatch agent on the cluster and then with fluentbit put all the data to cloudwatch is the best practice, is not the most cheap but then we are able to easily setup alarms, and query the logs with container insights, you can do the same with prometheus but is harder to manage, a study case is needed. Currently i use prometheus + grafana onprem for testing propuses only.
* Use Helm to install, manage and upgrade all the Kubernetes applications.

# API Specification
``` 
GET /api/api/current-user
GET /api/api/info
GET /api/javaee8
GET /api/profile - get all the profiles
GET /api/profile/info
GET /api/profile/{id} - get the profile
PUT /api/profile - update profile
POST /api/profile - Creates a new profile
DELETE /api/profile{id} - Remove the profile
``` 
