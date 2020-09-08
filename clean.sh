#!/bin/sh

YELLOW='\e[33m'

find . -name *.class -delete
echo "${YELLOW}Cleanup complete"