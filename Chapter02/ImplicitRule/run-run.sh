#/bin/bash
touch foo.y
echo -e ".\n" | ci foo.y
make -n foo
