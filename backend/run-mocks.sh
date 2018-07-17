#!/usr/bin/env bash

./generate-mocks.sh

nc -z localhost 8080
CONNECT_TO_WIREMOCK=$?

if [ ${CONNECT_TO_WIREMOCK} -eq 0 ]
then
    echo "Refreshing wiremock"
    curl -X POST http://localhost:8080/__admin/mappings/reset
else
    echo "Starting wiremock"
    version=$(./mvnw -q -Dexec.executable="echo" -Dexec.args='${project.groupId}/${project.artifactId}/${project.version}' --non-recursive exec:exec | tail -n 1)
    java -jar wiremock.jar --root-dir=./target/stubs/META-INF/${version} --no-request-journal &
fi