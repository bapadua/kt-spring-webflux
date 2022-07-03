#!/bin/bash
echo "building webflux application"
chmod +x ./gradlew
./gradlew clean build -x test
