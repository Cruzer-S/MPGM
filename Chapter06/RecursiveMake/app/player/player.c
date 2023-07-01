#include <stdio.h>

#include "codec/codec.h"
#include "db/scanner.h"
#include "ui/ui.h"

int main(int argc, char *argv[])
{
	ui(codec(yylex()));

	return 0;
}
