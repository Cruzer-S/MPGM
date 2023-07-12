#!/bin/bash
if [[ ! -d build ]]
then
	if ! mkdir -p build 
	then
		echo "Cannot create output directory" > /dev/stderr
		exit 1
	fi
fi

cd build
make --file=../makefile "$@"
