

all: build


build:
	echo "Building CDK application"


version:
	echo "Versioning CDK application"
	./mvnw versions:set -DnewVersion=$(RELEASE_TAG) -s settings.xml
	./mvnw versions:commit
	echo $(RELEASE_TAG) > version.ver
	GIT_COMMIT=$(git rev-parse HEAD)
	echo $(GIT_COMMIT) >> version.ver


