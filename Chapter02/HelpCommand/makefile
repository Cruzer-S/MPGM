# help - The default goal
PR = pr
GREP = grep
AWK = awk
SORT = sort

all:

.PHONY: help no-such-target
help:
	@$(MAKE) --print-data-base --question no-such-target	|	\
	$(GREP) -v -e '^no-such-target' -e '^makefile'		|	\
	$(AWK) '/^[^.%][-A-Za-z0-9_]*:/					\
		{ print substr($$1, 1, length($$1)-1) }'	|	\
	$(SORT)							|	\
	$(PR) --omit-pagination --width=80 --columns=4

no-such-target:
	exit(1)
