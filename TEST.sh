#!/bin/bash

n=$'\n'

declare -a expected
expected[1]="the${n}quick${n}brown${n}fox${n}jumps${n}over${n}the${n}lazy${n}dog"
expected[2]="brown${n}dog${n}fox${n}jumps${n}lazy${n}over${n}quick${n}the${n}the"
expected[3]="brown${n}dog${n}fox${n}jumps${n}lazy${n}over${n}quick${n}the"
expected[4]="brown 1${n}dog 1${n}fox 1${n}jumps 1${n}lazy 1${n}over 1${n}quick 1${n}the 2"
expected[5]="the 2${n}brown 1${n}dog 1${n}fox 1${n}jumps 1${n}lazy 1${n}over 1${n}quick 1"
expected[6]="the 2${n}brown 1${n}dog 1${n}fox 1${n}jumps 1${n}lazy 1${n}over 1${n}quick 1"

orig="the quick brown fox jumps over the lazy dog"

count=0

for i in 1 2 3 4 5 6
do
  printf "task%s test running...\n" $i

  printf "input: %s\n" "$orig"

  out="$(java -jar "task$i".jar $orig)"

  if [[ "${expected[$i]}" != "$out" ]]; then
     printf "\ntest %d failed\n" $i
    ((count++))
  else
    printf "\ntest %d passed\n" $i
  fi

  printf "\nactual output:\n%s\n" "$out"

  echo -e "\nexpected output:"
  echo -e "${expected[$i]}"
  echo -e "\n"
done

if [[ $count -gt 0 ]]
then
  printf "\nError occurred during test running\n"
  exit 1
else
  printf "Tests succeed\n"
  exit 0
fi