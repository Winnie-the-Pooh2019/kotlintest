#!/bin/bash

expectedCodes=(0 0 2 3 4 0 5 6 6 1 7 7 7 1 1)

declare -a input
input[0]="-login Ivan -pass ivan1234"
input[1]="-pass ivan1234 -login Ivan"
input[2]="-login i)(an -pass ivan1234"
input[3]="-pass ivan1234 -login van"
input[4]="-login Ivan -pass ivan"

input[5]="-login Ivan -pass ivan1234 -role READ -res A.B.C"
input[6]="-login Ivan -pass ivan1234 -role ivan -res A.B.C"
input[7]="-login Ivan -pass ivan1234 -role EXECUTE -res A.B.C"
input[8]="-login Ivan -pass ivan1234 -role READ -res A.B.C.D"

input[9]="-login Ivan -ds 2021-11-09 -de 2021-11-11 -pass ivan1234 -vol 1024 -role READ -res A.B.C"
input[10]="-login Ivan -ds 20211109 -de 2021-11-11 -pass ivan1234 -vol 1024 -role READ -res A.B.C"
input[11]="-login Ivan -ds 2021-11-11 -de 2021-11-01 -pass ivan1234 -vol 1024 -role READ -res A.B.C"
input[12]="-login Ivan -ds 2021-11-09 -de 2021-11-11 -pass ivan1234 -vol 24.4 -role READ -res A.B.C"

input[13]=""
input[14]="-h"

count=0

for ((i=0; i<15; i++))
do
  printf "test %s running...\n" "$i"

  java -jar "main".jar "${input[$i]}"

  code=$?

  if [[ $code -gt expectedCodes[$i] ]]
  then
    printf "\ntest %d failed\n" "$i"
    ((count++))
  else
    printf "\ntest %d passed\n" "$i"
  fi
done

if [[ $count -gt 0 ]]
then
  printf "\nError occurred during test running\n"
  exit 1
else
  printf "Tests succeed\n"
  exit 0
fi