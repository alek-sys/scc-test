#!/usr/bin/env bash

nc -z localhost 8081
CONNECT_TO_STUB_RUNNER=$?

if [ ${CONNECT_TO_STUB_RUNNER} -eq 0 ]
then
    echo "Stub runner is started already"
else
    echo "Starting stub runner"
    java -DstubRunner.ids=com.example:cakefactory:+:8081 -DstubRunner.stubsMode=LOCAL -jar stub-runner.jar
fi