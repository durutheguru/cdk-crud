

all: build


build:
	echo "Building CDK application"


version:
	echo "Versioning CDK application"
	./mvnw versions:set -DnewVersion=$(RELEASE_TAG) -s settings.xml
	./mvnw versions:commit
	echo $(RELEASE_TAG) > version.ver

