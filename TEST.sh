#!/bin/bash

expectedCodes=(0 0 3 2 4 0 0 5 6 6 0 0 7 7 7 7 0 0 0 0 2 3 4 0 0 0 5 6 6 6 0 7 7 2 2)

declare -a input
input[0]="-login Ivan -pass ivan1234"
input[1]="-pass ivan1234 -login Ivan"
input[2]="-login ivan -pass ivan1234"
input[3]="-login i^#n -pass ivan1234"
input[4]="-login Ivan -pass ivan"

input[5]="-login Ivan -pass ivan1234 -role READ -res A.B.C"
input[6]="-login Ivan -pass ivan1234 -res A.B.C.E.F -role READ"
input[7]="-login Ivan -pass ivan1234 -role TRANSLATE -res A.B.C"
input[8]="-login Ivan -pass ivan1234 -role EXECUTE -res A.B.C"
input[9]="-login Ivan -pass ivan1234 -role READ -res A0.123^BF"

input[10]="-login Ivan -pass ivan1234 -role READ -res A.B.C -ds 2000-11-09 -de 2002-11-09 -vol 109"
input[11]="-login Ivan -pass ivan1234 -role READ -res A.B.C -de 2002-11-09 -vol 109 -ds 2000-11-09"
input[12]="-login Ivan -pass ivan1234 -role READ -res A.B.C -ds 09-11-2000 -de 2002-11-09 -vol 109"
input[13]="-login Ivan -pass ivan1234 -role READ -res A.B.C -ds 2000-11-09 -de 2da0-11-90 -vol 109"
input[14]="-login Ivan -pass ivan1234 -role READ -res A.B.C -ds 2002-11-09 -de 2000-11-09 -vol 109"
input[15]="-login Ivan -pass ivan1234 -role READ -res A.B.C -ds 2000-11-09 -de 2002-11-09 -vol la7"

input[16]="-login Ivan -pass ivan1234 -role READ -res A.B.C -ds 2000-11-09 -de 2002-11-09 -vol 109 -h"
input[17]="-login Ivan -pass ivan1234 -h -role READ -res A.B.C -ds 2000-11-09 -de 2002-11-09 -vol 109"
input[18]="-h"
input[19]=""


#Konstantines
input[20]="-login X-X -pass XXX" #2
input[21]="-login XXX -pass XXX" #3
input[22]="-login jdoe -pass XXX" #4
input[23]="-login jdoe -pass sup3rpaZZ" #0
input[24]="-login jdoe -pass sup3rpaZZ -role READ -res a" #0
input[25]="-login jdoe -pass sup3rpaZZ -role READ -res a.b" #0
input[26]="aaa.jar -login jdoe -pass sup3rpaZZ -role XXX -res a.b" #5
input[27]="-login jdoe -pass sup3rpaZZ -role READ -res XXX" #6
input[28]="-login jdoe -pass sup3rpaZZ -role WRITE -res a" #6
input[29]="-login jdoe -pass sup3rpaZZ -role WRITE -res a.bc" #6
input[30]="-login jdoe -pass sup3rpaZZ -role READ -res a.b -ds 2015-01-01 -de 2015-12-31 -vol 100" #0
input[31]="-login jdoe -pass sup3rpaZZ -role READ -res a.b -ds 01-01-2015 -de 2015-12-31 -vol 100" #7
input[32]="-login jdoe -pass sup3rpaZZ -role READ -res a.b -ds 2015-01-01 -de 2015-12-31 -vol XXX" #7
input[33]="-login X -pass X -role READ -res X -ds 2015-01-01 -de 2015-12-31 -vol XXX" #2
input[34]="-login X -pass X -role READ -res X" #2





count=0

for ((i = 0; i < 35; i++)); do
  printf "test %s running...\n" "$i"

#  java -jar "main".jar "${input[$i]}"
  java -cp ./lib/kotlinx-cli-0.2.1.jar:./out/artifacts/main.jar application.main.MainKt ${input[$i]}

  code=$?

  echo $code
  echo "${expectedCodes[$i]}"

  if [[ "$code" == "${expectedCodes[$i]}" ]]; then
    printf "\ntest %d passed\n" "$i"
  else
    printf "\ntest %d failed\n" "$i"
    ((count++))
  fi
done

if [[ $count -gt 0 ]]; then
  printf "%s" "$count"
  printf "\nError occurred during test running\n"
  exit 1
else
  printf "Tests succeed\n"
  exit 0
fi
