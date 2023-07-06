#/bin/bash
# Assume we are in the source directory
cur=$PWD
export SOURCE_DIR=$cur

shopt -s nullglob

while [[ $SOURCE_DIR ]]
do
	MAKEFILE=$(echo $SOURCE_DIR/[Mm]akefile)
	if  [[ -e $MAKEFILE ]]
	then
		break
	fi

	SOURCE_DIR=${SOURCE_DIR%/*}
done

# Print an error if we haven't found a makefile.
if [[ ! $MAKEFILE ]]
then
	printf "$0: Cannot find a makefile\n" > /dev/stderr
	exit 1
fi

# Set the output directory to a default, if not set.
if [[ ! $BINARY_DIR ]]
then
	BINARY_DIR=${SOURCE_DIR}/build/$(uname -r)
fi

# Create the output directory
mkdir --parents $BINARY_DIR

# Run the make.
make --directory="$BINARY_DIR" --file="$MAKEFILE" "$@"
