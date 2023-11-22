
test:
	echo "Testing Crud application"
	./mvnw clean test


build:
	echo "Building Crud application"
	docker buildx build --platform linux/amd64,linux/arm64 -t $REPOSITORY:$RELEASE_TAG -t $REGISTRY/$REPOSITORY:$RELEASE_TAG --push .
	#docker buildx build --platform linux/amd64,linux/arm64 -t $REGISTRY/${{ secrets.DOCKERHUB_USERNAME }}/crud:latest -t $REGISTRY/${{ secrets.DOCKERHUB_USERNAME }}/crud:$RELEASE_TAG --push .


all: build


