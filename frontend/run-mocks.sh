#!/usr/bin/env bash

nc -z localhost 8081
CONNECT_TO_STUB_RUNNER=$?

if [ ! -s stub-runner.jar ]
then
    echo "Downloading stub-runner first..."
    wget -O stub-runner.jar 'https://search.maven.org/remote_content?g=org.springframework.cloud&a=spring-cloud-contract-stub-runner-boot&v=2.0.0.RELEASE'
fi

if [ ${CONNECT_TO_STUB_RUNNER} -eq 0 ]
then
    echo "Stub runner is started already"
else
    echo "Starting stub runner"
    java -DstubRunner.ids=com.example:cakefactory:+:8081 -DstubRunner.stubsMode=LOCAL -jar stub-runner.jar
fi