#!/bin/bash

# enter filename without extension as script argument

kotlinc "$1".kt -include-runtime -d "$1".jar