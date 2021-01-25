# Tasks
## Implement the Java unit tests in the same project.
* Sadly i dont know JAVA, and i didn't have enough time to learn JAVA and JUnit, my proficiency is in Python and PHP.
* The TDD should be a norm for the developer, so at least i've done the functional tests in python.
* To show more than i can code, i've done a very similar test for another company that i'm applying. https://github.com/HydriaOne/cmpapi here you can see all the code i've write myself the API REST in python and the tests.
## Generate the docker based development environment
### a. This new docker image will be launched in one of our shared integration machines
### b. It should also be launched with ease by any client programer who needs to launch a local development environment.
* I've upgraded eveything to run in K8s, like it should be, and i made eveything easy to use, more info on the README.
* For development the developers can use https://skaffold.dev/ , https://www.telepresence.io/ or the docker-compose still works fine.

3. Provide a "one-button" solution that
a. Compiles + generates the build (the microservice uber jar)
b. Launches the microservice
c. Runs optionally your tests
* I've build a Makefile with all the nescesary stuff, more info on the README

# Questionary
## Question 1
### Share your thoughts on deploying our API into Production:
### a. What solutions would you use to automate the whole process? Explain why with pros/cons
**Pros**
* I would use terraform or CDK for creating all the infrastructure, so we can handle all with a version control, and uniformly.
* I would use gitlab ci + runners to run all the pipelines of the code, the pipeline will run the tests and deployments, easily allowing to rollback and manage everything.

**Cons**
* Is a lot of time consuming, but in the end is a well invested time.
### b. What autoscaling solution would you implement knowing that the API can easily reach 50K daily users? Again explain why with pros/cons.
**Pros**
* Use Route 53 + ALB + WAF in front of the application.
* Use Kubernetes to deploy the application, easily allowing upscale & downscale the pods, and use as ingress controller nginx + alb or nlb, logging everything with prometheus,fluentbit and grafana.

* Deploy the DB on RDS, will allow us to upscale & downscale easily, and even maybe depends on the requirements we'll be able to run pure serverless.

**Cons**
* Is a lot of time consuming, but in the end is a well invested time.
* Maybe the price, but I think in a long/short term it will compensate quickly.
---
## Question 2
The REST API of the exercise is a piece of a complex client/server application which is
composed of several microservices. A microservice is composed of packages. A package
can depend on other packages. Packages use semantic versioning. Versions of client and
server packages of the same microservice need to be in sync. Client and server packages
are stored in GitLab. The client is composed of Unity packages (UPM) along with native
packages and it is built for several platforms (iOS, Android, Windows, MacOS). Given this
context describe:

## a. How you would model a robust and efficient CI/CD pipeline that:
### i. Guarantees packages integrity
I will check the integrity of the package with MD5 or sha256
### ii. Guarantees that client and server packages of the same microservice are in sync
### iii. Keeps packages up to date automatically
I don't know the exact requisits of the apis, but usually for me the optimal solution to both problems is:
* Deploy client and server on k8s, and mount the same Amazon EFS on both services, so when the efs is updated with a new artifact it will be synced in both sides.
* Build and upload the artifact on efs with gitlab, any new change in the artifact code will trigger the runners and upload the new artifact.
### iv. Lets anyone (QA testers, designers, producers, programmers) build the application for a given platform out of a given branch by hitting a button
* This is easy to configure with gitlab (i think the other CI/CD are very similar), create a gitlab-ci file than when the branch is named, for example android-, it will trigger the specific jobs for android builds is configured via regex /^android-. *$/, this is the ideal way, if it need to be 1 magic button, on gitlab you can configure and step with manual trigger, and the step have android, ios ... and just hit what do you want.
### v. Upon merging a package's feature branch into master automatically builds and validates the package along with all packages that depend on it as well as the application for all platforms
* Always launch as usual first the unit test of the application in the pipeline, if anything fails, just throw and error and cancel the pipe.
## b. What tests and analysis would you perform along the pipeline to guarantee the quality of the packages and the application?
* I will launch as usual first the unit test of the application in the pipeline, check the md5... then custom tests like if all the env vars are setup correctly... if anything fails just throw and error and cancel the pipe. If everything is ok build and deploy the app on the cluster, then launch functional testing to ensure everything is ok, if we have errors, launch and automatic rollback, on production if the billing enables we can set up a blue green environment.