#!/bin/bash

java -cp ./lib/kotlinx-cli-0.2.1.jar:./out/artifacts/main.jar application.main.MainKt "$@"

exitcode=$?

printf "\nProgram finished with exit code: %s\n" "$exitcode"

exit $exitcode