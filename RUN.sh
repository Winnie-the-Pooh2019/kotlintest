#!/bin/bash

args=""

for arg in "$@"; do
  args="${args} ${arg}"
done

java -cp ./lib/kotlinx-cli-0.2.1.jar:./out/artifacts/main.jar application.main.MainKt $args