#!/bin/bash

declare -a expected
expected[1]="the\nquick\nbrown\nfox\njumps\nover\nthe\nlazy\ndog"
expected[2]="brown\ndog\nfox\njumps\nlazy\nover\nquick\nthe\nthe"
expected[3]="brown\ndog\nfox\njumps\nlazy\nover\nquick\nthe"
expected[4]="brown 1\ndog 1\nfox 1\njumps 1\nlazy 1\nover 1\nquick 1\nthe 2"
expected[5]="the 2\nbrown 1\ndog 1\nfox 1\njumps 1\nlazy 1\nover 1\nquick 1"
expected[6]="the 2\nbrown 1\ndog 1\nfox 1\njumps 1\nlazy 1\nover 1\nquick 1"

orig="the quick brown fox jumps over the lazy dog"

for i in 1 2 3 4 5 6
do
  printf "task%s test running...\n" $i

  printf "input: %s\n" "$orig"

  out="$(java -jar "task$i".jar $orig)"

  printf "\nactual output:\n\n%s\n" "$out"

  echo -e "\nexpected output:\n"
  echo -e "${expected[$i]}"
  echo -e "\n"
done