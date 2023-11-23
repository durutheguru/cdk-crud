

all: build


test:
	echo "Testing Crud application"
	./mvnw clean test


build:
	echo "Building Crud application"
	echo "Image Tag: $(REGISTRY)/$(REPOSITORY_ALIAS)/$(REPOSITORY):$(RELEASE_TAG)"
	aws ecr-public create-repository --repository-name $(REPOSITORY) --region $(REGION) || true
	docker buildx build --platform linux/arm64 -t "$(REPOSITORY):$(RELEASE_TAG)" -t "$(REGISTRY)/$(REPOSITORY_ALIAS)/$(REPOSITORY):$(RELEASE_TAG)" --load .
	docker push $(REGISTRY)/$(REPOSITORY_ALIAS)/$(REPOSITORY):$(RELEASE_TAG)


