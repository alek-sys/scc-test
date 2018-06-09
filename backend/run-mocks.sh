#!/usr/bin/env bash

./mvnw spring-cloud-contract:convert
version=$(./mvnw -q -Dexec.executable="echo" -Dexec.args='${project.groupId}/${project.artifactId}/${project.version}' --non-recursive exec:exec | tail -n 1)
java -jar wiremock.jar --root-dir=./wiremock/META-INF/${version} --no-request-journal --verbose