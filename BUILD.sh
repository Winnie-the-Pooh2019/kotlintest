#!/bin/bash

kotlinc $(find ./src/application/main/ -name "*.kt") -cp ./lib/kotlinx-cli-0.2.1.jar -include-runtime -d ./out/artifacts/main.jar