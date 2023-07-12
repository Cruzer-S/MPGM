# SHELL := /bin/sh -x

RM := rm -rf
MV := mv
LNDIR := lndir -ignorelinks
MKDIR := mkdir -p
EGREP := egrep
SED := sed
TOUCH := touch
CHMOD := chmod

EXAMPLES_DIR := examples
OUTPUT_DIR := output
TMP_DIR := /tmp

EXAMPLES := $(patsubst $(EXAMPLES_DIR)/%/,%,$(dir $(shell find -L $(EXAMPLES_DIR) -name "[Mm]akefile")))

define run-script-example
(cd $(dir $1);									\
./$(notdir $1) 2>&1 |							\
if $(EGREP) --silent '\$$\(MAKE\)' [Mm]akefile;	\
then											\
	$(SED) -e 's/^++*/$$/';						\
else											\
	$(SED) -e 's/^++*/$$/'						\
		   -e '/ing directory /d'				\
		   -e 's/\[[0-9]\]//';					\
fi )											\
> $(TMP_DIR)/out.$$$$ &&						\
$(MV) $(TMP_DIR)/out.$$$$ $2
endef

# $(call generic-program-example,example-directory)
define generic-program-example
$(eval $1_dir := $(OUTPUT_DIR)/$1)
$(eval $1_make_out := $($1_dir)/make.out)
$(eval $1_run_out := $($1_dir)/run.out)
$(eval $1_clean := $($1_dir)/clean)
$(eval $1_run_make := $($1_dir)/run-make.sh)
$(eval $1_run_run := $($1_dir)/run-run.sh)
$(eval $1_sources := $(wildcard $1/*))

$($1_run_out): $($1_make_out) $($1_run_run)
	$$(call run-script-example, $($1_run_run), $$@)

$($1_make_out): $($1_clean) $($1_run_make)
	$$(call run-script-example, $($1_run_make), $$@)

$($1_clean): $($1_sources) makefile
	$(RM) -r $($1_dir)
	$(MKDIR) $($1_dir)
	$(LNDIR) -silent $(abspath $(EXAMPLES_DIR)/$1) $($1_dir)
	$(TOUCH) $$@

$($1_run_make):
	printf "#!/bin/bash -x\nmake\n" > $$@
	$(CHMOD) +x $$@

$($1_run_run):
	printf "#!/bin/bash -x\necho do nothing" > $$@
	$(CHMOD) +x $$@

endef

.PHONY: all
all: $(addsuffix /make.out,$(addprefix $(OUTPUT_DIR)/,$(EXAMPLES))) \
	 $(addsuffix /run.out,$(addprefix $(OUTPUT_DIR)/,$(EXAMPLES)))

$(foreach e,$(EXAMPLES),$(eval $(call generic-program-example,$e)))

.PHONY: clean
clean:
	$(RM) $(OUTPUT_DIR)

ifneq "$(MAKECMDGOALS)" "clean"
_CREATE_OUTPUT_DIR := $(shell							\
	if ! [[ -d $(OUTPUT_DIR) ]];						\
	then												\
		$(foreach d,$(EXAMPLES),						\
			$(MKDIR) $(OUTPUT_DIR)/$d;					\
			$(LNDIR) $(abspath $(EXAMPLES_DIR)/$d) 		\
			$(OUTPUT_DIR)/$d;							\
		)												\
	fi													\
)
endif
