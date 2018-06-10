#!/usr/bin/env bash

./generate-mocks.sh

version=$(./mvnw -q -Dexec.executable="echo" -Dexec.args='${project.groupId}/${project.artifactId}/${project.version}' --non-recursive exec:exec | tail -n 1)
java -jar wiremock.jar --root-dir=./target/stubs/META-INF/${version} --no-request-journal