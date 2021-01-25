all: build deploy test

build: build_image push_image
deploy: deploy_gradle deploy_swagger deploy_mariadb
destroy: destroy_gradle destroy_swagger destroy_mariadb

build_image:
	docker build --tag hydria/api-gradle:latest --tag hydria/api-gradle:1.0 --no-cache .
build_and_push_image_all_platforms:
	docker buildx build --platform linux/amd64,linux/arm64,linux/arm --push --tag hydria/api-gradle:latest --tag hydria/api-gradle:1.0 --no-cache .
deploy_gradle:
	kubectl apply -f ./k8s/api-gradle
deploy_swagger:
	kubectl apply -f ./k8s/swagger
deploy_mariadb:
	kubectl apply -f ./k8s/mariaDB
push_image:
	docker image push --all-tags hydria/api-gradle
destroy_gradle:
	kubectl delete -f ./k8s/api-gradle
destroy_swagger:
	kubectl delete -f ./k8s/swagger
destroy_mariadb:
	kubectl delete -f ./k8s/mariaDB
test:
	pytest ./test.py
